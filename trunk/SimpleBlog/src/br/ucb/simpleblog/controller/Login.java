package br.ucb.simpleblog.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import br.ucb.simpleblog.model.beans.User;
import br.ucb.simpleblog.model.daos.LoginDAO;
import br.ucb.simpleblog.model.daos.UserDAO;

@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String page = null;
		if(request.getParameter("logout").equals("true")){
			HttpSession session = request.getSession();
			session.invalidate();
			page = "sb-login.jsp";
			request.setAttribute("erro_session", "site.login.logout");
		}
		dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_login = request.getParameter("user_login");
		String user_pass = request.getParameter("user_pass");
		String page = null;
		String title = null;
		HttpSession session = request.getSession(true);
		RequestDispatcher dispatcher;
		//PrintWriter out = response.getWriter();
		
		User user_form = new User();
		//User user = (User) session.getAttribute("user");
		user_form.setLogin(user_login);
		user_form.setPass(user_pass, true);//Com true gera o hash da senha
		
		try{
			boolean valido = new LoginDAO().autenticar(user_form);
			if(valido){
				User user = new UserDAO().consultar(user_form);
				session.setAttribute("user_geral", user);
				title = "backend.title.init";
				String language = request.getParameter("idioma");
				Locale locale = new Locale(language);
				Config.set(request.getSession(), Config.FMT_LOCALE, locale);
				Config.set(request.getSession(), Config.FMT_FALLBACK_LOCALE, locale);
				request.setAttribute("idioma", language);
				//response.sendRedirect("index.jsp");
				page = "sb-admin/index.jsp";	
			}
			else{
				request.setAttribute("erro_login", "login.error");
				page = "sb-login.jsp";
			}
		}
		catch(SQLException e){
			request.setAttribute("erroCatch", e);
		}
		request.setAttribute("title", title);
		dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
