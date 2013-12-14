package br.ucb.simpleblog.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.simpleblog.model.beans.Categoria;
import br.ucb.simpleblog.model.beans.Comentario;
import br.ucb.simpleblog.model.beans.Post;
import br.ucb.simpleblog.model.daos.CategoriaDAO;
import br.ucb.simpleblog.model.daos.ComentarioDAO;
import br.ucb.simpleblog.model.daos.PostDAO;
import br.ucb.simpleblog.model.enums.PostStatus;

/**
 * Servlet implementation class Views
 */
@WebServlet("/viewer.do")
public class Views extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String acao = request.getParameter("page"), title = null, page = null;
		List<Post> posts;
		List<Comentario> coments;
		Post post = new Post();
		try{
			List<Categoria> cats = new CategoriaDAO().listar();
			request.setAttribute("cats", cats);
			//Listagem de Posts e categorias
			if(acao.equals("home")){
				posts = new PostDAO().listar(PostStatus.PUBLICADO);
				request.setAttribute("posts", posts);
				title = "site.home";
				page = "index.jsp";
			}
			if(acao.equals("visualizar")){
				String post_id = request.getParameter("post");
				String cat_id = request.getParameter("cat");
					if(post_id != null){
						post = new PostDAO().consultar(Integer.parseInt(post_id));
						coments = new ComentarioDAO().listar(Integer.parseInt(post_id));
						title = post.getNome();
						page = "post.jsp";
						request.setAttribute("coments", coments);
						request.setAttribute("post", post);
					}
					if(cat_id != null){
						Categoria cat_nome = new CategoriaDAO().consultar(Integer.parseInt(cat_id));
						posts = new PostDAO().listar(Integer.parseInt(cat_id));
						request.setAttribute("posts", posts);
						title= "site.categoria" + cat_nome.getNome();
						page = "posts_cat.jsp";
					}
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
