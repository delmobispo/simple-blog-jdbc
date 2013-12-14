package br.ucb.simpleblog.model.beans;

import java.io.Serializable;

/**
 * @author Delmo
 * @version 1.0
 */
public class RoleUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int role_id;
	private String role_nome;
	private int role_access;
	
	public RoleUser(){
		// TODO constructor stub
	}

	public int getId() {
		return role_id;
	}

	public void setId(int role_id) {
		this.role_id = role_id;
	}

	public String getNome() {
		return role_nome;
	}

	public void setNome(String role_nome) {
		this.role_nome = role_nome;
	}

	public int getAccess() {
		return role_access;
	}

	public void setAccess(int role_access) {
		this.role_access = role_access;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
