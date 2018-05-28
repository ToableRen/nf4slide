package com.qtu404.obsolete.servlet;

import com.qtu404.present.dao.impl.SlideDaoImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class EditServlet
 */
@WebServlet(
        urlPatterns = {
                "/EditServlet",
                "/edit.do"
        },
        initParams = {
                @WebInitParam(name = "EditServlet", value = "")
        })
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession(false);
        String userId_String = (String) session.getAttribute("usrname");
        String slideId_String = request.getParameter("slideId");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String msg = SlideDaoImpl.fetchSlideById(slideId_String).getContent();
        response.getWriter().write(msg);
    }

}
