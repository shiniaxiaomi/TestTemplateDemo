/**
  * Copyright 2019 bejson.com 
  */
package com.lyj.demo.dto;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于RabbitMQ消息传输到个人门户，使用工具生成（首字母没有小写）
 * @author yingjie.lu
 * @date 2019/11/27 10:46 上午
 * @version 1.0
 **/

@Data
public class TodoForMQ implements Serializable {

    @JSONField(name = "AppTaskID")
    private String AppTaskID = "";
    @JSONField(name = "TaskName")
    private String TaskName = "";
    @JSONField(name = "UserID")
    private String UserID = "";
    @JSONField(name = "UserNameCN")
    private String UserNameCN = "";
    @JSONField(name = "UserNameEN")
    private String UserNameEN = "";
    @JSONField(name = "url")
    private String url = "";
    @JSONField(name = "DeleteFlag")
    private String DeleteFlag = "";
    @JSONField(name = "AppID")
    private String AppID = "";
    @JSONField(name = "CreateUserID")
    private String CreateUserID = "";
    @JSONField(name = "CreateUserNameCN")
    private String CreateUserNameCN = "";
    @JSONField(name = "CreateUserNameEN")
    private String CreateUserNameEN;
    @JSONField(name = "StartTime")
    private String StartTime = "";
    @JSONField(name = "ExpireTime")
    private String ExpireTime = "";
    @JSONField(name = "MessageMode")
    private String MessageMode = "0";
    @JSONField(name = "CanOperator")
    private String CanOperator = "0";
    @JSONField(name = "Permissions")
    private String Permissions = "";
    @JSONField(name = "Desc")
    private String Desc = "";
}