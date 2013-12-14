package br.ucb.simpleblog.model.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author Delmo
 * @version 1.0
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int user_id;
	private String user_nome;
	private String user_email;
	private String user_perfil;
	private String user_login;
	private String user_pass;
	private Date user_register_date;
	private Date user_modification_date = new Date();
	private RoleUser role;

	public User() {
		// TODO constructor stub
	}

	public int getId() {
		return user_id;
	}

	public void setId(int user_id) {
		this.user_id = user_id;
	}

	public String getNome() {
		return user_nome;
	}

	public void setNome(String user_nome) {
		this.user_nome = user_nome;
	}

	public String getEmail() {
		return user_email;
	}

	public void setEmail(String user_email) {
		this.user_email = user_email;
	}

	public String getPerfil() {
		return user_perfil;
	}

	public void setPerfil(String user_perfil) {
		this.user_perfil = user_perfil;
	}

	public String getLogin() {
		return user_login;
	}

	public void setLogin(String user_login) {
		this.user_login = user_login;
	}

	public String getPass() {
		return user_pass;
	}
	public void setPass(String user_pass){
		this.user_pass = user_pass;
	}
	public void setPass(String user_pass, boolean gerar) {
		if(gerar){
			MessageDigest md = null;  
			try {  
				md = MessageDigest.getInstance("MD5");  
			} 
			catch (NoSuchAlgorithmException e) {  
				e.printStackTrace();  
			}  
			BigInteger hash = new BigInteger(1, md.digest(user_pass.getBytes()));  
			this.user_pass = hash.toString(16);
		}
		else{
			this.user_pass = user_pass;
		}
	}

	public Date getDate() {
		return user_register_date;
	}

	public void setDate(Date user_register_date) {
		this.user_register_date = user_register_date;
	}

	public Date getDateModification() {
		return this.user_modification_date;
	}

	public void setDateModification(Date user_modification_date) {
		this.user_modification_date = user_modification_date;
	}
	
	public void setRole(RoleUser role){
		this.role = role;
	}
	public RoleUser getRole() {
		return role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
