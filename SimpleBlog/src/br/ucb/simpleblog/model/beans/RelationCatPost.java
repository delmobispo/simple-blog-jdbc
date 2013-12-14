package br.ucb.simpleblog.model.beans;

import java.io.Serializable;

/**
 * @author Delmo
 * @version 1.0
 */
public class RelationCatPost implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int relation_id;
	private Categoria cat_id;
	private Post post_id;

	public RelationCatPost() {
		// TODO constructor stub
	}

	public int getId() {
		return relation_id;
	}

	public void setId(int relation_id) {
		this.relation_id = relation_id;
	}

	public Categoria getIdCategoria() {
		return cat_id;
	}

	public void setIdCategoria(Categoria cat_id) {
		this.cat_id = cat_id;
	}

	public Post getIdPost() {
		return post_id;
	}

	public void setIdPost(Post post_id) {
		this.post_id = post_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
