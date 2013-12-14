package br.ucb.simpleblog.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.simpleblog.model.beans.Categoria;
import br.ucb.simpleblog.model.beans.Post;
import br.ucb.simpleblog.model.beans.User;
import br.ucb.simpleblog.model.enums.PostStatus;

public class PostDAO {
	private Connection con;
	
	public PostDAO() throws SQLException{
		this.con = ConnectionFactory.getConnection();
	}
	public List<Post> listar() throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"SELECT p.post_id,p.post_nome,p.post_content,p.post_excerpt,p.user_id,p.post_date,p.post_modification_date,p.post_status,c.cat_id FROM posts AS p INNER JOIN relations_cat_post AS r ON p.post_id = r.post_id INNER JOIN categorias AS c ON c.cat_id = r.cat_id GROUP BY p.post_id ORDER BY p.post_date DESC;"
		);
		stmt.executeQuery();
		ResultSet rs = stmt.executeQuery();
		List<Post> posts = new ArrayList<Post>();
		while(rs.next()){
			Post post = new Post();
			post.setId(rs.getInt("post_id"));
			post.setNome(rs.getString("post_nome"));
			post.setUser(new UserDAO().consultar(rs.getInt("user_id")));
			post.setContent(rs.getString("post_content"));
			post.setExcerpt(rs.getString("post_excerpt"));
			post.setDate(rs.getDate("post_date"));
			post.setDateModification(rs.getDate("post_modification_date"));
			post.setStatus(PostStatus.valueOf(rs.getString("post_status")));
			post.setCategorias(new CategoriaDAO().listar((rs.getInt("post_id"))));
			posts.add(post);
		}
		rs.close();
		stmt.close();
		return posts;
	}
	public int inserir(Post post) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"INSERT INTO posts (post_nome,post_content,post_excerpt,user_id, post_status) VALUES(?,?,?,?,?);"
		);
		stmt.setString(1, post.getNome());
		stmt.setString(2, post.getContent());
		stmt.setString(3, post.getExcerpt());
		stmt.setInt(4, post.getUser().getId());
		stmt.setString(5, post.getStatus().toString());
		stmt.executeUpdate();
		int row_afects = 0;
		stmt = this.con.prepareStatement("SELECT post_id FROM posts WHERE post_id = LAST_INSERT_ID();");
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			for(Categoria cat : post.getCategorias()){
				stmt = this.con.prepareStatement(
					"INSERT INTO relations_cat_post (post_id, cat_id) VALUES(?,?);"
				);
				stmt.setInt(1, rs.getInt("post_id"));
				stmt.setInt(2,cat.getId());
				row_afects = stmt.executeUpdate();
			}
		}
		rs.close();
		stmt.close();
		return row_afects;
		
	}
	public List<Post> listar(User user) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"SELECT p.post_id,p.post_nome,p.post_content,p.post_excerpt,p.user_id,p.post_date,p.post_modification_date,p.post_status,c.cat_id FROM posts AS p INNER JOIN relations_cat_post AS r ON p.post_id = r.post_id INNER JOIN categorias AS c ON c.cat_id = r.cat_id WHERE p.user_id = ? ORDER BY p.post_date DESC;"
		);
		stmt.setInt(1, user.getId());
		ResultSet rs = stmt.executeQuery();
		List<Post> posts = new ArrayList<Post>();
		while(rs.next()){
			Post post = new Post();
			post.setId(rs.getInt("post_id"));
			post.setNome(rs.getString("post_nome"));
			post.setUser(new UserDAO().consultar(rs.getInt("user_id")));
			post.setContent(rs.getString("post_content"));
			post.setExcerpt(rs.getString("post_excerpt"));
			post.setDate(rs.getDate("post_date"));
			post.setDateModification(rs.getDate("post_modification_date"));
			post.setStatus(PostStatus.valueOf(rs.getString("post_status")));
			post.setCategorias(new CategoriaDAO().listar(rs.getInt("post_id")));
			posts.add(post);
		}
		rs.close();
		stmt.close();
		return posts;
	}
	public List<Post> listar(int cat) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"SELECT p.post_id,p.post_nome,p.post_content,p.post_excerpt,p.user_id,p.post_date,p.post_modification_date,p.post_status FROM posts AS p INNER JOIN relations_cat_post AS r	ON p.post_id = r.post_id INNER JOIN categorias AS c ON c.cat_id = r.cat_id WHERE r.cat_id = ? AND p.post_status = ?"
		);
		stmt.setInt(1, cat);
		stmt.setString(2, PostStatus.PUBLICADO.toString());
		ResultSet rs = stmt.executeQuery();
		List<Post> posts = new ArrayList<Post>();
		while(rs.next()){
			Post post = new Post();
			post.setId(rs.getInt("post_id"));
			post.setNome(rs.getString("post_nome"));
			post.setUser(new UserDAO().consultar(rs.getInt("user_id")));
			post.setContent(rs.getString("post_content"));
			post.setExcerpt(rs.getString("post_excerpt"));
			post.setDate(rs.getDate("post_date"));
			post.setDateModification(rs.getDate("post_modification_date"));
			post.setStatus(PostStatus.valueOf(rs.getString("post_status")));
			post.setCategorias(new CategoriaDAO().listar(rs.getInt("post_id")));
			posts.add(post);
		}
		rs.close();
		stmt.close();
		return posts;
	}
	public List<Post> listar(PostStatus post_status) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"SELECT p.post_id,p.post_nome,p.post_content,p.post_excerpt,p.user_id,p.post_date,p.post_modification_date,p.post_status,c.cat_id FROM posts AS p INNER JOIN relations_cat_post AS r ON p.post_id = r.post_id INNER JOIN categorias AS c ON c.cat_id = r.cat_id WHERE p.post_status = ? GROUP BY p.post_id ORDER BY p.post_date DESC;"
		);
		stmt.setString(1, post_status.toString());
		ResultSet rs = stmt.executeQuery();
		List<Post> posts = new ArrayList<Post>();
		while(rs.next()){
			Post post = new Post();
			post.setId(rs.getInt("post_id"));
			post.setNome(rs.getString("post_nome"));
			post.setUser(new UserDAO().consultar(rs.getInt("user_id")));
			post.setContent(rs.getString("post_content"));
			post.setExcerpt(rs.getString("post_excerpt"));
			post.setDate(rs.getDate("post_date"));
			post.setDateModification(rs.getDate("post_modification_date"));
			post.setStatus(PostStatus.valueOf(rs.getString("post_status")));
			post.setCategorias(new CategoriaDAO().listar(rs.getInt("post_id")));
			posts.add(post);
		}
		rs.close();
		stmt.close();
		return posts;
	}
	public Post consultar(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"SELECT p.post_id,p.post_nome,p.post_content,p.post_excerpt,p.user_id,p.post_date,p.post_modification_date,p.post_status,c.cat_id FROM posts AS p INNER JOIN relations_cat_post AS r ON p.post_id = r.post_id INNER JOIN categorias AS c ON c.cat_id = r.cat_id WHERE p.post_id = ?;"
		);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		Post post = null;
		while(rs.next()){
			post = new Post();
			post.setId(rs.getInt("post_id"));
			post.setNome(rs.getString("post_nome"));
			post.setContent(rs.getString("post_content"));
			post.setExcerpt(rs.getString("post_excerpt"));
			post.setUser(new UserDAO().consultar(rs.getInt("user_id")));
			post.setDate(rs.getDate("post_date"));
			post.setDateModification(rs.getDate("post_modification_date"));
			post.setStatus(PostStatus.valueOf(rs.getString("post_status")));
			post.setCategorias(new CategoriaDAO().listar(rs.getInt("post_id")));
		}
		rs.close();
		stmt.close();
		return post;
	}
	public int alterar(Post post) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
			"UPDATE posts SET post_nome = ?, post_content = ?, post_excerpt = ?, post_date = ?, post_mofication_date = ?, post_status = ? WHERE post_id = ?;"
		);
		stmt.setString(1, post.getNome());
		stmt.setString(2, post.getContent());
		stmt.setString(3, post.getExcerpt());
		stmt.setDate(4, new java.sql.Date(post.getDate().getTime()));
		stmt.setDate(5, new java.sql.Date(post.getDateModification().getTime()));
		stmt.setString(6, post.getStatus().toString());
		stmt.setInt(7, post.getId());
		int row_afects = stmt.executeUpdate();
		if(row_afects > 0){
			stmt = this.con.prepareStatement(
				"SELECT relation_id FROM relations_cat_post WHERE post_id = ?"
			);
			stmt.setInt(1, post.getId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				for(Categoria cat : post.getCategorias()){
					stmt = this.con.prepareStatement(
						"UPDATE relations_post_cat SET cat_id = ? WHERE relation_id = ?"
					);
					stmt.setInt(1, cat.getId());
					stmt.setInt(2, rs.getInt("relation_id"));
					row_afects += stmt.executeUpdate();
				}
			}
		}
		stmt.close();
		return row_afects;
	}
	public int deletar(Post post) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"DELETE FROM posts WHERE post_id = ?;"
		);
		stmt.setInt(1, post.getId());
		int row_afects = stmt.executeUpdate();
		int row_afects_del = 0;
		
		if(row_afects > 0){
			stmt = this.con.prepareStatement(
				"DELETE FROM relations_cat_post WHERE post_id = ?;"
			);
			stmt.setInt(1, post.getId());
			row_afects_del = stmt.executeUpdate();
		}
		stmt.close();
		return row_afects + row_afects_del;
	}
	
	public List<Post> pesquisar(String termo) throws SQLException{
		List<Post> posts = new ArrayList<Post>();
		if(termo.equals(null)){
			return posts;
		}
		PreparedStatement stmt = this.con.prepareStatement(
			"SELECT p.post_id,p.post_nome,p.post_content,p.post_excerpt,p.user_id,p.post_date,p.post_modification_date,p.post_status,c.cat_id FROM posts AS p INNER JOIN relations_cat_post AS r ON p.post_id = r.post_id INNER JOIN categorias AS c ON c.cat_id = r.cat_id WHERE post_nome LIKE ? ORDER BY p.post_date DESC;"
		);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			Post post = new Post();
			post.setId(rs.getInt("post_id"));
			post.setNome(rs.getString("post_nome"));
			post.setContent(rs.getString("post_content"));
			post.setExcerpt(rs.getString("post_excerpt"));
			post.setUser(new UserDAO().consultar(rs.getInt("user_id")));
			post.setDate(rs.getDate("post_date"));
			post.setDateModification(rs.getDate("post_modification_date"));
			post.setStatus(PostStatus.valueOf(rs.getString("post_status")));
			post.setCategorias(new CategoriaDAO().listar(rs.getInt("post_id")));
			posts.add(post);
		}
		rs.close();
		stmt.close();
		return posts;
	}
}
