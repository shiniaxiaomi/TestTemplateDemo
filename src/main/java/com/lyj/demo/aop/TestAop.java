package com.lyj.demo.aop;

import com.lyj.demo.domain.User;
import com.lyj.demo.dto.TodoForMQ;
import com.lyj.demo.listener.Bus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 切面类，关于增删待办切面和事务切面
 * @author yingjie.lu
 * @date 2019/11/28 9:55 上午
 * @version 1.0
 **/
@Aspect
@Component
public class TestAop {

    ThreadLocal<List<String>> todoDeletes = new ThreadLocal();
    ThreadLocal<List<TodoForMQ>> todoInserts = new ThreadLocal();

    //插入切入点
    @Pointcut("execution(* com.lyj.demo.mapper.UserMapper.insert*(..))")
    public void todoInsertPointCut() {
    }

    //删除切入点
    @Pointcut("execution(* com.lyj.demo.mapper.UserMapper.delete*(..))")
    public void todoDeletePointCut() {
    }

    //指定方法的事务切入点
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)) && within(com.lyj.demo.service.TestService)")
    public void transactionPointCut() {
    }

    //事务切入点的环绕通知
    @Around("transactionPointCut()")
    public Object transactionHandler(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("transactionBeforeLog");
        Object proceed = null;

        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            //如果抛出异常，则回滚，清空threadLocal
            this.todoInserts.remove();
            this.todoDeletes.remove();
            throw throwable;
        }

        //如果正常运行，则将threadLocal的数据保存到队列
        System.out.println("transactionAfterLog");
        List<TodoForMQ> todoForMQS = (List)this.todoInserts.get();
        List<String> strings = (List)this.todoDeletes.get();
        Iterator iterator;
        if (todoForMQS != null) {
            iterator = todoForMQS.iterator();
            while(iterator.hasNext()) {
                TodoForMQ todoForMQ = (TodoForMQ)iterator.next();
                Bus.todoBus.post(todoForMQ);
            }
        }

        if (strings != null) {
            iterator = strings.iterator();
            while(iterator.hasNext()) {
                String id = (String)iterator.next();
                Bus.todoBus.post(id);
            }
        }

        return proceed;
    }

    //新增待办的环绕通知
    @Around("todoInsertPointCut()")
    public Object insertHandler(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        //如果参数不为1，则抛出异常
        if (args.length != 1) {
            throw new RuntimeException("参数不唯一，待验证");
        }

        Object arg = args[0];
        //如果参数为User
        if (arg instanceof User) {
            User user = (User)arg;
            TodoForMQ todoForMQ = new TodoForMQ();
            todoForMQ.setAppID(user.getName());
            this.todoInserts.set(Arrays.asList(todoForMQ));
        } else {
            if (!(arg instanceof List)) {
                throw new RuntimeException("参数类型异常，待验证");
            }

            //如果参数为List
            List list = (List)arg;
            if (list.size() == 0) {
                throw new RuntimeException("新增操作list个数不能为0");
            }

            if (!(list.get(0) instanceof TodoForMQ)) {
                throw new RuntimeException("list中类型不能识别");
            }

            List<TodoForMQ> listBuff = new ArrayList();

            for(int i = 0; i < list.size(); ++i) {
                User user = (User)list.get(i);
                TodoForMQ todoForMQ = new TodoForMQ();
                todoForMQ.setAppID(user.getName());
                listBuff.add(todoForMQ);
            }

            this.todoInserts.set(listBuff);
        }

        System.out.println("InsertBeforeLog");
        Object proceed = pjp.proceed();
        System.out.println("InsertAfterLog");
        return proceed;
    }

    //删除待办的环绕通知
    @Around("todoDeletePointCut()")
    public Object deleteHandler(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        //如果参数不为1，则抛出异常
        if (args.length != 1) {
            throw new RuntimeException("参数不唯一，待验证");
        }

        Object arg = args[0];
        //如果参数为String
        if (arg instanceof String) {
            String id = (String)arg;
            this.todoDeletes.set(Arrays.asList(id));
        } else {
            if (!(arg instanceof List)) {
                throw new RuntimeException("参数类型异常，待验证");
            }

            //如果参数为List
            List list = (List)arg;
            if (list.size() == 0) {
                throw new RuntimeException("删除操作list个数不能为0");
            }

            if (!(list.get(0) instanceof TodoForMQ)) {
                throw new RuntimeException("list中类型不能识别");
            }

            List<String> listBuff = new ArrayList();

            for(int i = 0; i < list.size(); ++i) {
                User user = (User)list.get(i);
                listBuff.add(String.valueOf(user.getId()));
            }

            this.todoDeletes.set(listBuff);
        }

        System.out.println("DeleteBeforeLog");
        Object proceed = pjp.proceed();
        System.out.println("DeleteAfterLog");
        return proceed;
    }

}
