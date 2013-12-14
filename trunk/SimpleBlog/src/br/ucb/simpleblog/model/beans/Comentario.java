package br.ucb.simpleblog.model.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Delmo
 * @version 1.0
 */
public class Comentario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int coment_id;
	private Date coment_date;
	private String coment_content;
	private String coment_author;
	private String coment_author_email;
	private String coment_ip;
	private boolean coment_aprovado;
	private Post post_id;

	public Comentario() {
		// TODO constructor stub
	}

	public int getId() {
		return coment_id;
	}

	public void setId(int coment_id) {
		this.coment_id = coment_id;
	}

	public Date getDate() {
		return coment_date;
	}

	public void setDate(Date coment_date) {
		this.coment_date = coment_date;
	}

	public String getContent() {
		return coment_content;
	}

	public void setContent(String coment_content) {
		this.coment_content = coment_content;
	}

	public String getAuthor() {
		return coment_author;
	}

	public void setAuthor(String coment_author) {
		this.coment_author = coment_author;
	}

	public String getEmail() {
		return coment_author_email;
	}

	public void setEmail(String coment_author_email) {
		this.coment_author_email = coment_author_email;
	}

	public String getIp() {
		return coment_ip;
	}

	public void setIp(String coment_ip) {
		this.coment_ip = coment_ip;
	}

	public boolean isAprovado() {
		return coment_aprovado;
	}

	public void setAprovado(boolean coment_aprovado) {
		this.coment_aprovado = coment_aprovado;
	}

	public Post getPost() {
		return post_id;
	}

	public void setPost(Post post_id) {
		this.post_id = post_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
