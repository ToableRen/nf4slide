package com.qtu404.present.action;

import com.aliyuncs.exceptions.ClientException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qtu404.present.dao.impl.LogDaoImpl;
import com.qtu404.present.dao.impl.UserDaoImpl;
import com.qtu404.present.domain.LogVo;
import com.qtu404.present.domain.UserVo;
import com.qtu404.unit.sms.SMSsender;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@ParentPackage("json-default")
public class UserAction extends ActionSupport {

    private String phone;
    private String password;
    private String username;
    private String result;


    /**
     * 得到当前用户信息
     */
    @Action(value = "fetchLoginInfo", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String fetchUserLoginInfo() throws Exception {
        Map session = ActionContext.getContext().getSession();
        UserVo userVo = (UserVo) session.get("loginUser");
        result = new ObjectMapper().writeValueAsString(userVo);
        return SUCCESS;
    }


    /**
     * 验证电话号码是否已被使用
     */
    @Action(value = "verifyPhone", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String verifyPhone() throws Exception {

        UserVo userVo = UserDaoImpl.fetchUserByPhone(phone);
        result = "true";
        if (userVo != null) {//不通过
            result = "false";
        }
        return SUCCESS;
    }

    /**
     * 用户登陆
     */
    @Action(value = "login", results = {
            @Result(type = "json", params = {"root", "result"})
    })
    public String login() {
        ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();

        UserVo userVo = UserDaoImpl.fetchUserByLogin(phone, password);

        if (userVo != null) {
            session.put("usrname", userVo.getUserId());
            session.put("loginUser", userVo);
            result = "loginSuccess";
        } else {
            session.put("usrname", "");
            result = "loginFail";
        }
        return SUCCESS;
    }

    /**
     * 用户注册
     */
    @Action(value = "doRegister", results = {
            @Result(location = "/home.html")
    })
    public String register() {

        UserVo userVo = new UserVo();
        userVo.setPhoneNum(phone);
        userVo.setUsername(username);
        userVo.setPassword(password);
        String userId = UserDaoImpl.addNewUser(userVo);

        //记录日志
        //得到用户IP地址
        String ipAddress = ServletActionContext.getRequest().getRemoteAddr();
        LogVo logVo = new LogVo(userId, ipAddress, new Date(), "doRegister", phone + "," + username + "," + password);
        new LogDaoImpl().addNewLog(logVo);

        //发送验证码
        Integer verifyCode = new Random().nextInt(10000);
        try {
            new SMSsender().sendSmsMessage(userVo.getPhoneNum(), String.valueOf(verifyCode));
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
