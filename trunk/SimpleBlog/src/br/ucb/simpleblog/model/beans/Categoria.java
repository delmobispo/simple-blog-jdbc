package br.ucb.simpleblog.model.beans;

import java.io.Serializable;

/**
 * @author Delmo
 * @version 1.0
 */
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int cat_id;
	private String cat_nome;
	private String cat_describe;
	private Categoria cat_parent;

	public Categoria() {
	}

	public int getId() {
		return cat_id;
	}

	public void setId(int cat_id) {
		this.cat_id = cat_id;
	}

	public String getNome() {
		return cat_nome;
	}

	public void setNome(String cat_nome) {
		this.cat_nome = cat_nome;
	}

	public String getDescribe() {
		return cat_describe;
	}

	public void setDescribe(String cat_describe) {
		this.cat_describe = cat_describe;
	}

	public Categoria getParent() {
		return cat_parent;
	}

	public void setParent(Categoria cat_parent) {
		this.cat_parent = cat_parent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
