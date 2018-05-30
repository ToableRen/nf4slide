package com.qtu404.present.unit.email.demo;

import com.qtu404.present.unit.Parameter;
import com.qtu404.present.unit.email.EmailSender;

public class Demo {
    //实例
    public static void main(String[] args) {
        Parameter emailParameter = new Parameter();
        emailParameter.setAttribute("receiver", "wildhunt_geralt@foxmail.com");
        emailParameter.setAttribute("verification", "45123");

        EmailSender sender = new EmailSender(new SigninMessage(), new AdminStrategy());
        sender.SendEmail(emailParameter);
    }
}
