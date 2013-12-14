package br.ucb.simpleblog.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucb.simpleblog.model.beans.RoleUser;

public class RoleUserDAO {
	private Connection con;
	
	public RoleUserDAO() throws SQLException{
		this.con = ConnectionFactory.getConnection();
	}
	public List<RoleUser> listar() throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM roles_users");
		ResultSet rs = stmt.executeQuery();
		List<RoleUser> roles_users = new ArrayList<RoleUser>();
		while(rs.next()){
			RoleUser role_user = new RoleUser();
			role_user.setId(rs.getInt("role_user_id"));
			role_user.setNome(rs.getString("role_nome"));
			role_user.setAccess(rs.getInt("role_access"));
			roles_users.add(role_user);
		}
		rs.close();
		stmt.close();
		return roles_users;
	}
	public RoleUser consultar(int id) throws SQLException{
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM roles_users WHERE role_id = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		RoleUser role_user = null;
		if(rs.next()){
			role_user = new RoleUser();
			role_user.setId(rs.getInt("role_id"));
			role_user.setNome(rs.getString("role_nome"));
			role_user.setAccess(rs.getInt("role_access"));
		}
		rs.close();
		stmt.close();
		return role_user;
	}
	

}
