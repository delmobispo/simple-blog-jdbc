package br.ucb.simpleblog.controller.cruds;

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
import br.ucb.simpleblog.model.beans.User;
import br.ucb.simpleblog.model.daos.CategoriaDAO;
import br.ucb.simpleblog.model.daos.ComentarioDAO;
import br.ucb.simpleblog.model.daos.PostDAO;
import br.ucb.simpleblog.model.daos.UserDAO;

@WebServlet("/coment.do")
public class ComentCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null, title = null;
		int status = 0;
		RequestDispatcher dispatcher;
		String acao_coment = request.getParameter("coment");
		List<Comentario> coments;
		Post post;
		List<Categoria> cats;

		try{
			//Listar Comentarios
			if(acao_coment.equals("listar")){
				String user_id = request.getParameter("user_id");
				User user = new UserDAO().consultar(Integer.parseInt(user_id));
				if(user.getRole().getAccess() == 1){
					coments = new ComentarioDAO().listar();
				}else{
					coments = new ComentarioDAO().listar(user);
				}
				request.setAttribute("coments", coments);
				page = "sb-admin/coments.jsp";
				title = "Todos comentários";
			}
			String coment_id = request.getParameter("id");
			if(acao_coment.equals("aprovar")){
				Comentario c_ap = new ComentarioDAO().consultar(Integer.parseInt(coment_id));
				if(c_ap.isAprovado() == false){
					c_ap.setAprovado(true);
				}else{
					c_ap.setAprovado(false);
				}
				status = new ComentarioDAO().aprovar(c_ap);
				page = "sb-admin/coments.jsp";
				title = "Todos os comentários";
			}
			if(acao_coment.equals("editar")){
				Comentario c_edit = new ComentarioDAO().consultar(Integer.parseInt(coment_id));
				request.setAttribute("c_edit", c_edit);
				title  = "Editando comentario: " + c_edit.getId();
			}
			if(acao_coment.equals("excluir")){
				Comentario c_del = new ComentarioDAO().consultar(Integer.parseInt(coment_id));
				status = new ComentarioDAO().deletar(c_del);
				page = "sb-admin/coments.jsp";

			}
			if(acao_coment.equals("adicionar")){
				String post_id = request.getParameter("post_id");
				String coment_ip = request.getParameter("ip");
				String coment_author = request.getParameter("nome");
				String coment_author_email = request.getParameter("email");
				String coment_content = request.getParameter("comentario");
				Comentario c_add = new Comentario();
				c_add.setPost(new PostDAO().consultar(Integer.parseInt(post_id)));
				c_add.setIp(coment_ip);
				c_add.setAuthor(coment_author);
				c_add.setEmail(coment_author_email);
				c_add.setContent(coment_content);
				status = new ComentarioDAO().inserir(c_add);
				post = new PostDAO().consultar(Integer.parseInt(post_id));
				cats = new CategoriaDAO().listar();
				request.setAttribute("post", post);
				request.setAttribute("cats", cats);
				page = "post.jsp";
			}
			//Salvando o cara no banco
			if(acao_coment.equals("salvar")){
				String post_id = request.getParameter("post_id");
				String coment_ip = request.getParameter("ip");
				String coment_author = request.getParameter("nome");
				String coment_author_email = request.getParameter("email");
				String coment_content = request.getParameter("comentario");
				Comentario c_up = new Comentario();
				c_up.setId(Integer.parseInt(coment_id));
				c_up.setAuthor(coment_author);
				c_up.setEmail(coment_author_email);
				c_up.setIp(coment_ip);
				c_up.setPost(new PostDAO().consultar(Integer.parseInt(post_id)));
				c_up.setContent(coment_content);
				status = new ComentarioDAO().alterar(c_up);
			}
		}
		catch(SQLException e){
			request.setAttribute("erroCatch", "<strong>Error :(</strong> " + e);
		}
			request.setAttribute("status", status);
			request.setAttribute("title", title);
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
	}

}
