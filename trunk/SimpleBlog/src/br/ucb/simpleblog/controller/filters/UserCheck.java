package br.ucb.simpleblog.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ucb.simpleblog.model.beans.User;

/**
 * Servlet Filter implementation class UserCheck
 */
@WebFilter(
		urlPatterns = { 
				"/UserCheck", 
				"/sb-admin/*"
		}, 
		servletNames = { "UserCRUD","CatCRUD" ,"PostCRUD"})
public class UserCheck implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		User user_geral = (User) session.getAttribute("user_geral");
		if(user_geral == null){
			session.setAttribute("erro_session", "Você não está logado");
			((HttpServletResponse)response).sendRedirect("../sb-login.jsp");
		}
		else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
