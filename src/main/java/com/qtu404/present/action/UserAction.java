package com.qtu404.present.action;

import com.aliyuncs.exceptions.ClientException;
import com.opensymphony.xwork2.ActionSupport;
import com.qtu404.present.dao.impl.UserDaoImpl;
import com.qtu404.present.domain.UserVo;
import com.qtu404.unit.sms.SMSsender;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.xml.ws.Action;
import java.util.Random;

@Controller("UserAction")
@Scope("prototype")
public class UserAction extends ActionSupport {

    String phone;
    String password;
    String username;

    public String verifyPhone() throws Exception {

        UserVo userVo = UserDaoImpl.fetchUserByPhone(phone);
        String msg_String = "true";
        if (userVo != null) {//不通过
            msg_String = "false";
        }
        return SUCCESS;
    }

    @Action
    public String register() {
        UserVo userVo = new UserVo();
        userVo.setPhoneNum(phone);
        userVo.setUsername(username);
        userVo.setPassword(password);

        UserDaoImpl.addNewUser(userVo);

        //发送验证码
        Integer verifyCode = new Random().nextInt(10000);
        try {
            new SMSsender().sendSmsMessage(userVo.getPhoneNum(), String.valueOf(verifyCode));
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }
}
