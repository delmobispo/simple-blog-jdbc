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

import br.ucb.simpleblog.model.beans.User;
import br.ucb.simpleblog.model.daos.RoleUserDAO;
import br.ucb.simpleblog.model.daos.UserDAO;
//import java.io.PrintWriter;

@WebServlet("/user.do")
public class UserCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "sb-admin/users.jsp", title = null, msg = null;
		RequestDispatcher dispatcher;
		String acao_user = request.getParameter("user");
		User user_add = new User(); //Criado para adição
		List<User> users, users_busca;
		
		try{
			//Pesquisa 
			String termo = request.getParameter("termo");
			if(acao_user.equals("pesquisar") 
					&& !termo.isEmpty()){
				users_busca = new UserDAO().pesquisar(termo);
				request.setAttribute("users_busca", users_busca);
				title = "label.resultado " + termo;
			}
			//Listar Usuários
			if(acao_user.equals("listar")){
				users = new UserDAO().listar();
				request.setAttribute("users", users);
				title = "label.user.all";
			}
			if(acao_user.equals("editar")){
				User user_edit = new UserDAO().consultar(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("user_edit", user_edit);
				title  = "label.user.edit " + user_edit.getLogin();
			}
			String id = request.getParameter("id");//id do usuário para exlusão
			if(acao_user.equals("excluir") && !id.isEmpty()){
				User user_del = new User();
				user_del.setId(Integer.parseInt(id));
				int status = new UserDAO().deletar(user_del);
				if(status != 0){
					msg = user_del.getNome() + "label.sucesso.excluir";
				}
				else{
					msg = "label.error.excluir.user" + user_del.getNome();
				}
				page = "sb-admin/users.jsp";
			}
			//Abre a página de adição
			if(acao_user.equals("adicionar")){
				request.setAttribute("user_add", "1"); //Objeto criado pronto para adicção
				title = "label.user.add";
			}
			//Salvando o cara no banco
			String user_id = request.getParameter("user_id");
			String user_nome = request.getParameter("user_nome");
			String user_email = request.getParameter("user_email");
			String user_perfil = request.getParameter("user_perfil");
			String user_login = request.getParameter("user_login");
			String user_pass = request.getParameter("user_pass");
			String user_pass_bd = request.getParameter("user_pass_bd");
			String user_role = request.getParameter("user_role");
			
			if(acao_user.equals("salvar")){
				if(user_id.isEmpty()){
					user_add.setNome(user_nome);
					user_add.setEmail(user_email);
					user_add.setPerfil(user_perfil);
					user_add.setLogin(user_login);
					if(!user_pass.isEmpty())
						user_add.setPass(user_pass, true);
					user_add.setRole(new RoleUserDAO().consultar(Integer.parseInt(user_role)));
					int status = new UserDAO().incluir(user_add);
				
					if(status != 0){
						msg = "label.user.add.sucess";
					}
					else{
						msg = "label.user.add.error";
					}
				
				}
				else{
					User user_up = new User();
					user_up.setId(Integer.parseInt(user_id));
					user_up.setNome(user_nome);
					user_up.setPerfil(user_perfil);
					if(user_pass.isEmpty()){
						user_up.setPass(user_pass_bd);
					}else{
						user_up.setPass(user_pass,true);
					}
					user_up.setRole(new RoleUserDAO().consultar(Integer.parseInt(user_role)));
				
					int status = new UserDAO().alterar(user_up);
				
					if(status != 0){
						msg = "label.user.atualizado";
					}
					else{
						msg = "label.user.error.atualizar";
					}
				}
				page = "sb-admin/users.jsp";
			}
		}
		catch(SQLException e){
			request.setAttribute("erroCatch", "<strong>Error :(</strong> " + e);
		}
			request.setAttribute("status", msg);
			request.setAttribute("title", title);
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
	}
}
