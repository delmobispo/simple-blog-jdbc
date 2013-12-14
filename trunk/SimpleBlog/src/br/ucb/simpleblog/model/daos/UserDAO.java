package br.ucb.simpleblog.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.simpleblog.model.beans.User;

public class UserDAO {
	private Connection con;
	
	public UserDAO() throws SQLException{
		this.con = ConnectionFactory.getConnection();
	}
	public int incluir(User user) throws SQLException{
		if(user.equals(null)){
			return 0;
		}
		PreparedStatement stmt = this.con.prepareStatement(
				"INSERT INTO users(user_nome, user_email, user_perfil, user_login, user_pass, role_id) VALUES( ?, ?, ?, ?, ?, ?);"
		);
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getPerfil());
		stmt.setString(4, user.getLogin());
		stmt.setString(5, user.getPass());
		stmt.setInt(6, user.getRole().getId());
		int row_afects = stmt.executeUpdate();
		stmt.close();
		return row_afects;
	}
	public int alterar(User user) throws SQLException{
		if(user.equals(null)){
			return 0;
		}
		PreparedStatement stmt = this.con.prepareStatement(
				"UPDATE users SET user_nome = ?, user_perfil = ?, user_pass = ?, user_modification_date = ?, role_id = ? WHERE user_id = ?;"
		);
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getPerfil());
		stmt.setString(3, user.getPass());
		stmt.setDate(4, new java.sql.Date(user.getDateModification().getTime()));
		stmt.setInt(5, user.getRole().getId());
		stmt.setInt(6, user.getId());
		int row_afects = stmt.executeUpdate();
		stmt.close();
		return row_afects;
	}
	public int deletar(User user) throws SQLException{
		if(user.equals(null)){
			return 0;
		}
		PreparedStatement stmt = this.con.prepareStatement(
				"DELETE FROM users WHERE user_id = ?;"
		);
		stmt.setInt(1, user.getId());
		int row_afects = stmt.executeUpdate();
		stmt.close();
		return row_afects;
	}
	public User consultar(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM users WHERE user_id = ?;"
		);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		User user = null;
		while(rs.next()){
			user = new User();
			user.setId(rs.getInt("user_id"));
			user.setNome(rs.getString("user_nome"));
			user.setEmail(rs.getString("user_email"));
			user.setPerfil(rs.getString("user_perfil"));
			user.setDate(rs.getDate("user_register_date"));
			user.setDateModification(rs.getDate("user_modification_date"));
			user.setLogin(rs.getString("user_login"));
			user.setRole(new RoleUserDAO().consultar((rs.getInt("role_id"))));
		}
		rs.close();
		stmt.close();
		return user;
	}
	public User consultar(User user_form) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM users WHERE user_login = ?;"
		);
		stmt.setString(1, user_form.getLogin());
		ResultSet rs = stmt.executeQuery();
		User user = new User();
		while(rs.next()){
			user.setId(rs.getInt("user_id"));
			user.setNome(rs.getString("user_nome"));
			user.setEmail(rs.getString("user_email"));
			user.setPerfil(rs.getString("user_perfil"));
			user.setDate(rs.getDate("user_register_date"));
			user.setPass(rs.getString("user_pass"));
			user.setDateModification(rs.getDate("user_modification_date"));
			user.setLogin(rs.getString("user_login"));
			user.setRole(new RoleUserDAO().consultar((rs.getInt("role_id"))));
		}
		rs.close();
		stmt.close();
		return user;
	}
	public List<User> listar() throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM users ORDER BY user_nome;"
		);
		ResultSet rs = stmt.executeQuery();
		List<User> users = new ArrayList<User>();
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setNome(rs.getString("user_nome"));
			user.setEmail(rs.getString("user_email"));
			user.setLogin(rs.getString("user_login"));
			user.setPerfil(rs.getString("user_perfil"));
			user.setDate(rs.getDate("user_register_date"));
			user.setDateModification(rs.getDate("user_modification_date"));
			user.setRole(new RoleUserDAO().consultar((rs.getInt("role_id"))));
			users.add(user);
		}
		rs.close();
		stmt.close();
		return users;
	}
	public List<User> pesquisar(String termo) throws SQLException{
		termo = "%" + termo + "%";
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT * FROM users WHERE user_nome LIKE ? OR user_email LIKE ? OR user_login LIKE ?;"
		);
		stmt.setString(1, termo);
		stmt.setString(2, termo);
		stmt.setString(3, termo);
		ResultSet rs = stmt.executeQuery();
		List<User> users = new ArrayList<User>();
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setNome(rs.getString("user_nome"));
			user.setEmail(rs.getString("user_email"));
			user.setLogin(rs.getString("user_login"));
			user.setPerfil(rs.getString("user_perfil"));
			user.setDate(rs.getDate("user_register_date"));
			user.setDateModification(rs.getDate("user_modification_date"));
			user.setRole(new RoleUserDAO().consultar((rs.getInt("role_id"))));
			users.add(user);
		}
		rs.close();
		stmt.close();
		return users;
	}
}
