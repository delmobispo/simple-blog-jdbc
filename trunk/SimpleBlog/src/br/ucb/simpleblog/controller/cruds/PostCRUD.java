package br.ucb.simpleblog.controller.cruds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.simpleblog.model.beans.Categoria;
import br.ucb.simpleblog.model.beans.Post;
import br.ucb.simpleblog.model.beans.User;
import br.ucb.simpleblog.model.daos.CategoriaDAO;
import br.ucb.simpleblog.model.daos.PostDAO;
import br.ucb.simpleblog.model.daos.UserDAO;
import br.ucb.simpleblog.model.enums.PostStatus;

/**
 * Servlet implementation class PostCRUD
 */
@WebServlet("/post.do")
public class PostCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "sb-admin/posts.jsp", title = null, msg = null;
		RequestDispatcher dispatcher;
		String acao = request.getParameter("post");
		Post post_add = new Post(); //Criado para adição
		
		List<Post> posts;
		List<User> users = null;
		List<Categoria> cats = null, cats_all, set_cats = null,not_duplicate_cats = null,categorias_setadas = null;

		
		try{
			users = new UserDAO().listar();//Listagem de todos os usuários
			cats = new CategoriaDAO().listar();//Listagem de categorias
			//Listar Posts
			if(acao.equals("listar")){
				String user_id = request.getParameter("user_id");
				User user = new UserDAO().consultar(Integer.parseInt(user_id));
				if(user.getRole().getId() == 1){
					posts = new PostDAO().listar();
				}
				else{
					posts = new PostDAO().listar(user);
				}
				request.setAttribute("posts", posts);
				title = "Todos as postagens";
			}
			if(acao.equals("editar")){
				Post post_edit = new PostDAO().consultar(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("post_edit", post_edit);
				title  = "Editando post: " + post_edit.getNome();
				request.setAttribute("users", users);
				cats_all = new ArrayList<Categoria>();
				set_cats = new ArrayList<Categoria>();
				not_duplicate_cats = new ArrayList<Categoria>();
				categorias_setadas = new ArrayList<Categoria>();
				if(post_edit.getCategorias().size() > cats.size()){
					cats_all = post_edit.getCategorias();
					set_cats = cats;
				}else{
					cats_all = cats;
					set_cats = post_edit.getCategorias();
				}
				for(Categoria set_cat : set_cats){
					if(!cats_all.contains(set_cat)){
						not_duplicate_cats.add(set_cat);
					}else{
						categorias_setadas.add(set_cat);
					}
				}
				request.setAttribute("categorias_setadas", categorias_setadas);
				request.setAttribute("not_duplicate_cats", not_duplicate_cats);
			}
			if(acao.equals("adicionar")){
				request.setAttribute("post_add", "1"); //Objeto criado pronto para adição
				title = "Adicionar Post";
				request.setAttribute("cats", cats);
			}			
			if(acao.equals("salvar")){//Salvando o cara no banco
				String post_id = request.getParameter("post_id");
				String post_nome = request.getParameter("post_nome");
				String post_content = request.getParameter("post_content");
				String post_excerpt = request.getParameter("post_excerpt");
				String post_cat[] = request.getParameterValues("post_cat");
				User user = new UserDAO().consultar(Integer.parseInt(request.getParameter("post_user")));
				PostStatus post_status = PostStatus.valueOf(request.getParameter("post_status"));
				if(post_id.isEmpty()){
					post_add.setNome(post_nome);
					post_add.setContent(post_content);
					post_add.setExcerpt(post_excerpt);
					post_add.setUser(user);
					post_add.setStatus(post_status);
					for(String cat : post_cat){
						if(!cat.equals(null))
							post_add.setCategorias(new CategoriaDAO().consultar(Integer.parseInt(cat)));
					}
					int status = new PostDAO().inserir(post_add);
				
					if(status != 0){
						msg = "Post cadastrado com sucesso!";
					}
					else{
						msg = "Erro ao cadastrar post";
					}
				}
				else{
					Post post_up = new Post();
					post_up.setId(Integer.parseInt(post_id));
					post_up.setNome(post_nome);
					post_up.setContent(post_content);
					post_up.setExcerpt(post_excerpt);
					post_up.setUser(user);
					post_up.setStatus(post_status);
				
					int status = new PostDAO().alterar(post_up);
				
					if(status != 0){
						msg = "Post atualizado com sucesso!";
					}
					else{
						msg = "Erro ao atualizar post";
					}
				}
			}
			String id = request.getParameter("id");//id do post para exlusão
			if(acao.equals("excluir") && !id.isEmpty()){
				Post post_del = new PostDAO().consultar(Integer.parseInt(id));
				int status = new PostDAO().deletar(post_del);
				if(status != 0){
					msg = "Post <strong>" + post_del.getNome() + "</strong> excluido com sucesso!";
				}
				else{
					msg = "Erro ao excluir post <strong>" + post_del.getNome() + "</strong>";
				}
			}//if interação CRUD
		}
		catch(SQLException e){
			request.setAttribute("erroCatch", "<strong>Error :(</strong> " + e);
		}
		
		request.setAttribute("posts_status", PostStatus.PUBLICADO);
		request.setAttribute("status", msg);
		request.setAttribute("title", title);
		dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
