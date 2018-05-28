package com.qtu404.obsolete.servlet;

import com.aliyuncs.exceptions.ClientException;
import com.qtu404.present.dao.impl.UserDaoImpl;
import com.qtu404.present.domain.UserVo;
import com.qtu404.unit.sms.SMSsender;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(
        urlPatterns = {
                "/RegisterServlet",
                "/register.do"
        },
        initParams = {
                @WebInitParam(name = "RegisterServlet", value = "")
        })
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String phone_String = request.getParameter("inputPhone");
        UserVo userVo = UserDaoImpl.fetchUserByPhone(phone_String);
        String msg_String = "true";
        if (userVo != null) {//不通过
            msg_String = "false";
        }
        response.getWriter().write(msg_String);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        String phoneNum_String = request.getParameter("phone");
        String password_String = request.getParameter("password");
        String username_String = request.getParameter("username");

        UserVo userVo = new UserVo();
        userVo.setUsername(username_String);
        userVo.setPhoneNum(phoneNum_String);
        userVo.setPassword(password_String);
        UserDaoImpl.addNewUser(userVo);

        //验证码
        Integer verifyCode = new Random().nextInt(10000);
        try {
            new SMSsender().sendSmsMessage(phoneNum_String, String.valueOf(verifyCode));
        } catch (ClientException e) {
            e.printStackTrace();
        }

        response.sendRedirect("home.jsp");
    }

}
