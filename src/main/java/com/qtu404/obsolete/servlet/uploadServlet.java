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
 * Servlet implementation class uploadServlet
 */
@WebServlet(
		urlPatterns = { 
				"/uploadServlet", 
				"/upload.do"
		}, 
		initParams = { 
				@WebInitParam(name = "uploadServlet", value = "")
		})
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public uploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String slide_String = request.getParameter("slideId");
		HttpSession session=request.getSession(false);
		String userId_String = (String) session.getAttribute("usrname");
		String data_String = request.getParameter("data");
		String play_String = request.getParameter("play");
		SlideDaoImpl.modifyContent(data_String,play_String,slide_String);
	}

}
