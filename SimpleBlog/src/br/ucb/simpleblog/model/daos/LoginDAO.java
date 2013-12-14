package br.ucb.simpleblog.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucb.simpleblog.model.beans.User;

public class LoginDAO {
	private Connection con;
	
	public LoginDAO() throws SQLException{
		this.con = ConnectionFactory.getConnection();
	}
	
	public boolean autenticar(User user) throws SQLException{
		
		boolean result = false;
		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT user_login, user_pass FROM users WHERE user_login = ? AND user_pass = ?;");
		stmt.setString(1, user.getLogin());
		stmt.setString(2, user.getPass());
			
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			if(rs.getString("user_login").equals(user.getLogin().toString()) 
					&& rs.getString("user_pass").equals(user.getPass().toString())){
				result = true;
			}
			else{
				result = false;
			}
		}
		rs.close();
		stmt.close();
		return result;
	}

}
