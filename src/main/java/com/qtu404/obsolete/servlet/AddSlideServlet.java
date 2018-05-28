package com.qtu404.obsolete.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qtu404.present.dao.impl.SlideDaoImpl;

/**
 * Servlet implementation class AddSlideServlet
 */
@WebServlet(
        urlPatterns = {
                "/AddSlideServlet",
                "/addSlide.do"
        },
        initParams = {
                @WebInitParam(name = "AddSlideServlet", value = "")
        })
public class AddSlideServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSlideServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userId_String = (String) session.getAttribute("usrname");
        String slideId_String = request.getParameter("slideId");
        SlideDaoImpl.delSlideById(slideId_String);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
        String userId_String = (String) session.getAttribute("usrname");
        SlideDaoImpl.addNewSlide(userId_String);
//		FileManager.writeInto("", userId_String+"/"+slideId_String);
//		FileManager.writeInto("", userId_String+"/"+slideId_String+"_play");
    }

}
