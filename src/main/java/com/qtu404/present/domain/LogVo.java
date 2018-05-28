package com.qtu404.present.domain;

import com.sun.javafx.sg.prism.web.NGWebView;

import java.util.Date;

public class LogVo {
    private Integer id = null;
    private String userId = null;
    private String ipAddress = null;
    private Date date = new Date();
    private String operator = null;
    private String parameter = null;


    public LogVo(String userId, String ipAddress, Date date, String operator, String parameter) {
        this.userId = userId;
        this.ipAddress = ipAddress;
        this.date = date;
        this.operator = operator;
        this.parameter = parameter;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
