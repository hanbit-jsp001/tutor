package com.hanbit.jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.jsp.model.Info;
import com.hanbit.jsp.model.UserInfo;

public class WelcomeServlet extends HttpServlet {
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String name = uri.substring("/welcome/".length());
		
		HttpSession session = req.getSession();
		
		if (session.getAttribute("session") == null) {
			session.setAttribute("session", new UserInfo(name));
		}
		
		Info info = new Info();
		info.setName(name);
		info.setCountOfButtons(name.length());
		
		String json = mapper.writeValueAsString(info);
		
		resp.setContentType("application/json");
		resp.getOutputStream().print(json);
		resp.flushBuffer();
	}

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		String name = uri.substring("/welcome/".length());
		
		String path = "/WEB-INF/hello.jsp";
		
		Info info = new Info();
		info.setName(name);
		info.setCountOfButtons(name.length());
		
		req.setAttribute("model", info);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(path);
		dispatcher.forward(req, resp);
	}
	
}
