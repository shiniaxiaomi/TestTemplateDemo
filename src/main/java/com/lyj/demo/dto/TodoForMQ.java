/**
  * Copyright 2019 bejson.com 
  */
package com.lyj.demo.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * 用于RabbitMQ消息传输到个人门户，使用工具生成（首字母没有小写，用于）
 * @author yingjie.lu
 * @date 2019/11/27 10:46 上午
 * @version 1.0
 **/

@Data
public class TodoForMQ implements Serializable {

    private String AppTaskID="";
    private String TaskName="";
    private String UserID="";
    private String UserNameCN="";
    private String UserNameEN="";
    private String url="";
    private String DeleteFlag="0";
    private String AppID="";
    private String CreateUserID="";
    private String CreateUserNameCN="";
    private String CreateUserNameEN="";
    private String StartTime="";
    private String ExpireTime="";
    private String MessageMode="0";
    private String CanOperator="0";
    private String Permissions="";
    private String Desc="";


    //效率较高
    public String toString(){
        return "{\n" +
                "  \"AppTaskID\": \""+this.getAppTaskID()+"\",\n" +
                "  \"TaskName\": \""+this.getTaskName()+"\",\n" +
                "  \"UserID\": \""+this.getUserID()+"\",\n" +
                "  \"UserNameCN\": \""+this.getUserNameCN()+"\",\n" +
                "  \"UserNameEN\": \""+this.getUserNameEN()+"\",\n" +
                "  \"url\": \""+this.getUrl()+"\",\n" +
                "  \"DeleteFlag\": "+this.getDeleteFlag()+"\n" +
                "  \"AppID\": \""+this.getAppID()+"\",\n" +
                "  \"CreateUserID\": \""+this.getCreateUserID()+"\",\n" +
                "  \"CreateUserNameCN\": \""+this.getCreateUserNameCN()+"\",\n" +
                "  \"CreateUserNameEN\": \""+this.getCreateUserNameEN()+"\",\n" +
                "  \"StartTime\": \""+this.getStartTime()+"\",\n" +
                "  \"ExpireTime\": \""+this.getExpireTime()+"\",\n" +
                "  \"MessageMode\": \""+this.getMessageMode()+"\",\n" +
                "  \"CanOperator\": \""+this.getCanOperator()+"\",\n" +
                "  \"Permissions\": \""+this.getPermissions()+"\",\n" +
                "  \"Desc\": \""+this.getDesc()+"\"\n" +
                "}";
    }

    public static void main(String[] args) {
        TodoForMQ todoForMQ = new TodoForMQ();
        todoForMQ.setAppID("1");
        System.out.println(todoForMQ.toString());
    }

}