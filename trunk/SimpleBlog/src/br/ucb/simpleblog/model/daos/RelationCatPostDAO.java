package br.ucb.simpleblog.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ucb.simpleblog.model.beans.Post;
import br.ucb.simpleblog.model.beans.RelationCatPost;

public class RelationCatPostDAO {
	
	private Connection con;
	
	public RelationCatPostDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	public RelationCatPost consultar(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM relations_cat_post WHERE relation_id = ?;"
		);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		RelationCatPost relation = null;
		while(rs.next()){
			relation = new RelationCatPost();
			relation.setId(rs.getInt("relation_id"));
			relation.setIdCategoria(new CategoriaDAO().consultar(rs.getInt("cat_id")));
			relation.setIdPost(new PostDAO().consultar(rs.getInt("post_id")));
		}
		rs.close();
		stmt.close();
		return relation;
	}
	public ArrayList<RelationCatPost> consultarPorPost(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM relations_cat_post WHERE post_id = ?"
		);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		ArrayList<RelationCatPost> relations = new ArrayList<RelationCatPost>();
		while(rs.next()){
			RelationCatPost relation = new RelationCatPost();
			relation.setId(rs.getInt("relation_id"));
			relation.setIdCategoria(new CategoriaDAO().consultar(rs.getInt("cat_id")));
			relation.setIdPost(new Post());
			relations.add(relation);
		}
		rs.close();
		stmt.close();
		return relations;
	}
	public int deletar(RelationCatPost relation) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"DELETE FROM relations_cat_post WHERE relation_id = ?;"
		);
		stmt.setInt(1, relation.getId());
		int row_afects = stmt.executeUpdate();
		stmt.close();
		return row_afects;
	}
}
