package br.ucb.simpleblog.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.simpleblog.model.beans.Categoria;

public class CategoriaDAO {
	private Connection con;
	
	public CategoriaDAO() throws SQLException{
		this.con = ConnectionFactory.getConnection();
	}
	public int incluir(Categoria cat) throws SQLException{
		if(cat.equals(null)){
			return 0;
		}
		PreparedStatement stmt = this.con.prepareStatement(
				"INSERT INTO categorias(cat_nome,cat_describe,cat_parent) VALUES( ?, ?, ?);"
		);
		stmt.setString(1, cat.getNome());
		stmt.setString(2, cat.getDescribe());
		if(cat.getParent() == null){
			stmt.setInt(3, 1);
		}
		else{
			stmt.setInt(3, cat.getParent().getId());
		}
		int row_afects = stmt.executeUpdate();
		stmt.close();
		return row_afects;
	}
	public List<Categoria> listar() throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM categorias WHERE cat_id != cat_parent;"
		);
		ResultSet rs = stmt.executeQuery();
		List<Categoria> categorias = new ArrayList<Categoria>();
		while(rs.next()){
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("cat_id"));
			categoria.setNome(rs.getString("cat_nome"));
			categoria.setDescribe(rs.getString("cat_describe"));
			categoria.setParent(this.consultar((rs.getInt("cat_parent"))));
			categorias.add(categoria);
		}
		rs.close();
		stmt.close();
		return categorias;
	}
	public List<Categoria> listar(int post_id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT c.cat_id, c.cat_nome, c.cat_describe, c.cat_parent, r.post_id FROM categorias AS c INNER JOIN relations_cat_post AS r ON r.cat_id = c.cat_id WHERE r.post_id = ?;"
		);
		stmt.setInt(1, post_id);
		ResultSet rs = stmt.executeQuery();
		List<Categoria> categorias = new ArrayList<Categoria>();
		while(rs.next()){
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("cat_id"));
			categoria.setNome(rs.getString("cat_nome"));
			categoria.setDescribe(rs.getString("cat_describe"));
			categoria.setParent(this.consultar((rs.getInt("cat_parent"))));
			categorias.add(categoria);
		}
		rs.close();
		stmt.close();
		return categorias;
	}
	
	public Categoria consultar(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM categorias WHERE cat_id = ? AND cat_id > ?"
		);
		stmt.setInt(1, id);
		stmt.setInt(2, 1);
		ResultSet rs = stmt.executeQuery();
		Categoria categoria = null;
		while(rs.next()){
			categoria = new Categoria();
			categoria.setId(rs.getInt("cat_id"));
			categoria.setNome(rs.getString("cat_nome"));
			categoria.setDescribe(rs.getString("cat_describe"));
			categoria.setParent(this.consultar(rs.getInt("cat_parent")));
		}
		rs.close();
		stmt.close();
		return categoria;
	}
	public List<Categoria> consultarRelationsCatPost(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT c.cat_id, c.cat_nome,c.cat_describe,c.cat_parent FROM relations_cat_post AS r INNER JOIN categorias AS c ON c.cat_id = r.cat_id WHERE r.post_id = ?"
		);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		List<Categoria> categorias = new ArrayList<Categoria>();
		while(rs.next()){
			Categoria categoria = new Categoria();
			categoria.setId(rs.getInt("cat_id"));
			categoria.setNome(rs.getString("cat_nome"));
			categoria.setDescribe(rs.getString("cat_describe"));
			categoria.setParent(this.consultar((rs.getInt("cat_parent"))));
			categorias.add(categoria);
		}
		rs.close();
		stmt.close();
		return categorias;
	}
	public int alterar(Categoria categoria) throws SQLException{
		if(categoria.equals(null)){
			return 0;
		}
		PreparedStatement stmt = this.con.prepareStatement(
				"UPDATE categorias SET cat_nome = ?, cat_describe = ?, cat_parent = ? WHERE cat_id = ?;"
		);
		stmt.setString(1, categoria.getNome());
		stmt.setString(2, categoria.getDescribe());
		if(categoria.getParent() == null){
			stmt.setInt(3, 1);
		}
		else{
			stmt.setInt(3, categoria.getParent().getId());
		}
		stmt.setInt(4, categoria.getId());
		int row_afects = stmt.executeUpdate();
		stmt.close();
		return row_afects;
	}
	public int deletar(Categoria categoria) throws SQLException{
		if(categoria.equals(null)){
			return 0;
		}
		PreparedStatement stmt = this.con.prepareStatement(
				"DELETE FROM categorias WHERE cat_id = ?;"
		);
		stmt.setInt(1, categoria.getId());
		int row_afects = stmt.executeUpdate();
		
		if(row_afects > 0){
			stmt = this.con.prepareStatement(
				"DELETE FROM relations_cat_post WHERE cat_id = ?"
			);
			stmt.setInt(1, categoria.getId());
			row_afects = stmt.executeUpdate();
		}/*
		if(row_afects > 0){
			stmt = this.con.prepareStatement(
				"DELETE FROM posts WHERE cat_id NOT IN(SELECT r.post_id FROM posts as p INNER JOIN relations_cat_post AS r ON r.post_id = p.post_id WHERE r.cat_id = ?)"
			);
			stmt.setInt(1,categoria.getId());
			row_afects = stmt.executeUpdate();
		}*/
		stmt.close(); 
		return row_afects;
	}
	public List<Categoria> pesquisar(String termo) throws SQLException{
		termo = "%" + termo + "%";
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM categorias WHERE cat_nome LIKE ?;"
		);
		stmt.setString(1, termo);
		ResultSet rs = stmt.executeQuery();
		List<Categoria> cats = new ArrayList<Categoria>();
		while(rs.next()){
			Categoria cat = new Categoria();
			cat.setId(rs.getInt("cat_id"));
			cat.setNome(rs.getString("cat_nome"));
			cat.setDescribe(rs.getString("cat_describe"));
			cat.setParent(new CategoriaDAO().consultar(rs.getInt("cat_parent")));
			cats.add(cat);
		}
		rs.close();
		stmt.close();
		return cats;
	}

}
