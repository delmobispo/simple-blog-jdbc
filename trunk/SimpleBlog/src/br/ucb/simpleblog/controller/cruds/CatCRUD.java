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
import br.ucb.simpleblog.model.daos.CategoriaDAO;

@WebServlet("/cat.do")
public class CatCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "sb-admin/cats.jsp", title = null, msg = null;
		RequestDispatcher dispatcher;
		String acao_cat = request.getParameter("cat");
		Categoria cat_add = new Categoria(); //Criado para adição
		List<Categoria> cats, cats_op, cats_busca;
		
		try{		
			String termo = request.getParameter("termo");
			if(acao_cat.equals("pesquisar") 
					&& !termo.isEmpty()){
				cats_busca = new CategoriaDAO().pesquisar(termo);
				request.setAttribute("cats_busca", cats_busca);
				title = "label.resultado " + termo;
			}
			//Listar Usuários
			if(acao_cat.equals("listar")){
				cats = new CategoriaDAO().listar();
				request.setAttribute("cats", cats);
				title = "label.title.categoria";
			}
			if(acao_cat.equals("editar")){
				Categoria cat_edit = new CategoriaDAO().consultar(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("cat_edit", cat_edit);
				title  = "label.editar.categoria: " + cat_edit.getNome();
				cats_op = new CategoriaDAO().listar();
				request.setAttribute("cats_op", cats_op);
			}
			String id = request.getParameter("id");//id da categoria para exlusão
			if(acao_cat.equals("excluir") && !id.isEmpty()){
				Categoria cat_del = new CategoriaDAO().consultar(Integer.parseInt(id));
				int status = new CategoriaDAO().deletar(cat_del);
				if(status != 0){
					msg = cat_del.getNome() + "label.sucesso.excluir";
				}
				else{
					msg = "label.error.excluir" + cat_del.getNome();
				}
			}
			if(acao_cat.equals("adicionar")){
				request.setAttribute("cat_add", "1"); //Objeto criado pronto para adição
				title = "label.adicionar.categoria";
				cats_op = new CategoriaDAO().listar();
				request.setAttribute("cats_op", cats_op);
			}
			//Salvando o cara no banco
			String cat_id = request.getParameter("cat_id");
			String cat_nome = request.getParameter("cat_nome");
			String cat_describe = request.getParameter("cat_describe");
			String cat_parent = request.getParameter("cat_parent");
			
			if(acao_cat.equals("salvar")){
				if(cat_id.isEmpty()){
					cat_add.setNome(cat_nome);
					cat_add.setDescribe(cat_describe);
					cat_add.setParent(new CategoriaDAO().consultar(Integer.parseInt(cat_parent)));
					int status = new CategoriaDAO().incluir(cat_add);
				
					if(status != 0){
						msg = "label.categoria.cadastrada";
					}
					else{
						msg = "label.categoria.cadastro.error";
					}
				
				}
				else{
					Categoria cat_up = new Categoria();
					cat_up.setId(Integer.parseInt(cat_id));
					cat_up.setNome(cat_nome);
					cat_up.setDescribe(cat_describe);
					cat_up.setParent(new CategoriaDAO().consultar((Integer.parseInt(cat_parent))));
				
					int status = new CategoriaDAO().alterar(cat_up);
				
					if(status != 0){
						msg = "label.categoria.atualizada";
					}
					else{
						msg = "label.categoria.atualiza.error";
					}
				}
			}//if interação CRUD
		}
		catch(SQLException e){
			request.setAttribute("erroCatch", e);
		}
			request.setAttribute("status", msg);
			request.setAttribute("title", title);
			dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
	}
}
