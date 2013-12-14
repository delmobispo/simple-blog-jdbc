package br.ucb.simpleblog.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.simpleblog.model.beans.Comentario;
import br.ucb.simpleblog.model.beans.User;

public class ComentarioDAO {
	private Connection con;
	
	public ComentarioDAO() throws SQLException{
		this.con = ConnectionFactory.getConnection();
	}
	public int inserir(Comentario comment)throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"INSERT INTO comentarios(coment_content,coment_author,coment_author_email,coment_ip,post_id) VALUES(?, ?, ?, ?, ?);"
		);
		stmt.setString(1, comment.getContent());
		stmt.setString(2, comment.getAuthor());
		stmt.setString(3, comment.getEmail());
		stmt.setString(4, comment.getIp());
		stmt.setInt(5, comment.getPost().getId());
		int rows_affects = stmt.executeUpdate();
		stmt.close();
		return rows_affects;
	}
	public List<Comentario> listar(int post_id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM comentarios WHERE post_id = ? AND coment_aprovado = 1;"
		);
		stmt.setInt(1, post_id);
		ResultSet rs = stmt.executeQuery();
		List<Comentario> comentarios = new ArrayList<Comentario>();
		while(rs.next()){
			Comentario comentario = new Comentario();
			comentario.setId(rs.getInt("coment_id"));
			comentario.setPost(new PostDAO().consultar(rs.getInt("post_id")));
			comentario.setAuthor(rs.getString("coment_author"));
			comentario.setEmail(rs.getString("coment_author_email"));
			comentario.setContent(rs.getString("coment_content"));
			comentario.setDate(rs.getDate("coment_date"));
			comentario.setIp(rs.getString("coment_ip"));
			comentarios.add(comentario);
		}
		rs.close();
		stmt.close();
		return comentarios;
	}
	public List<Comentario> listar() throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM comentarios;"
		);
		ResultSet rs = stmt.executeQuery();
		List<Comentario> comentarios = new ArrayList<Comentario>();
		while(rs.next()){
			Comentario comentario = new Comentario();
			comentario.setId(rs.getInt("coment_id"));
			comentario.setPost(new PostDAO().consultar(rs.getInt("post_id")));
			comentario.setAuthor(rs.getString("coment_author"));
			comentario.setEmail(rs.getString("coment_author_email"));
			comentario.setContent(rs.getString("coment_content"));
			comentario.setDate(rs.getDate("coment_date"));
			comentario.setIp(rs.getString("coment_ip"));
			comentarios.add(comentario);
		}
		rs.close();
		stmt.close();
		return comentarios;
	}
	public List<Comentario> listar(User user) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT c.coment_id,c.coment_ip,c.coment_content,c.coment_author,c.coment_author_email,c.coment_aprovado,c.coment_date,c.post_id,c.coment_aprovado FROM comentarios  AS c INNER JOIN posts AS p ON c.post_id = p.post_id INNER JOIN users AS u ON u.user_id = p.user_id WHERE u.role_id = ? AND p.user_id = ?;"
		);
		stmt.setInt(1, user.getRole().getId());
		stmt.setInt(2, user.getId());
		ResultSet rs = stmt.executeQuery();
		List<Comentario> comentarios = new ArrayList<Comentario>();
		while(rs.next()){
			Comentario comentario = new Comentario();
			comentario.setId(rs.getInt("coment_id"));
			comentario.setPost(new PostDAO().consultar(rs.getInt("post_id")));
			comentario.setAuthor(rs.getString("coment_author"));
			comentario.setEmail(rs.getString("coment_author_email"));
			comentario.setContent(rs.getString("coment_content"));
			comentario.setDate(rs.getDate("coment_date"));
			comentario.setIp(rs.getString("coment_ip"));
			comentario.setAprovado(rs.getBoolean("coment_aprovado"));
			comentarios.add(comentario);
		}
		rs.close();
		stmt.close();
		return comentarios;
	}
	public Comentario consultar(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM comentarios WHERE coment_id = ?"
		);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Comentario comentario = null;
		while(rs.next()){
			comentario = new Comentario();
			comentario.setId(rs.getInt("coment_id"));
			comentario.setContent(rs.getString("coment_content"));
			comentario.setAuthor(rs.getString("coment_author"));
			comentario.setAprovado(rs.getBoolean("coment_aprovado"));
			comentario.setDate(rs.getDate("coment_date"));
			comentario.setEmail(rs.getString("coment_author_email"));
			comentario.setPost(new PostDAO().consultar(rs.getInt("post_id")));
			comentario.setIp(rs.getString("coment_ip"));
		}
		rs.close();
		stmt.close();
		return comentario;
	}
	public int alterar(Comentario comentario) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"UPDATE comentarios SET coment_content = ?, coment_aprovado = ? WHERE coment_id = ?;"
		);
		stmt.setString(1, comentario.getContent());
		stmt.setBoolean(2, comentario.isAprovado());
		stmt.setInt(3, comentario.getId());
		int rows_affects = stmt.executeUpdate();
		stmt.close();
		return rows_affects;
	}
	public int aprovar(Comentario comentario) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"UPDATE comentarios SET coment_aprovado = ? WHERE coment_id = ?;"
		);
		stmt.setBoolean(1, comentario.isAprovado());
		stmt.setInt(2, comentario.getId());
		int rows_affects = stmt.executeUpdate();
		stmt.close();
		return rows_affects;
	}
	public int deletar(Comentario comentario) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"DELETE FROM comentarios WHERE coment_id = ?;"
		);
		stmt.setInt(1, comentario.getId());
		int rows_affects = stmt.executeUpdate();
		stmt.close();
		return rows_affects;
	}
}
