package br.ucb.simpleblog.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ucb.simpleblog.model.enums.PostStatus;

/**
 * @author Delmo
 * @version 1.0
 */
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int post_id;
	private String post_nome;
	private String post_content;
	private String post_excerpt;
	private User user;
	private Date post_date;
	private Date post_modification_date;
	private PostStatus post_status;
	private List<Categoria> cats = new ArrayList<Categoria>();

	public Post() {
		// TODO constructor stub
	}

	public int getId() {
		return post_id;
	}

	public void setId(int post_id) {
		this.post_id = post_id;
	}

	public String getNome() {
		return post_nome;
	}

	public void setNome(String post_nome) {
		this.post_nome = post_nome;
	}

	public String getContent() {
		return post_content;
	}

	public void setContent(String post_content) {
		this.post_content = post_content;
	}

	public String getExcerpt() {
		return post_excerpt;
	}

	public void setExcerpt(String post_excerpt) {
		this.post_excerpt = post_excerpt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return post_date;
	}

	public void setDate(Date post_date) {
		this.post_date = post_date;
	}

	public Date getDateModification() {
		return post_modification_date;
	}

	public void setDateModification(Date post_modification_date) {
		this.post_modification_date = post_modification_date;
	}

	public PostStatus getStatus() {
		return post_status;
	}

	public void setStatus(PostStatus post_status) {
		this.post_status = post_status;
	}
	
	public void setCategorias(List<Categoria> cat){
		this.cats = cat;
	}
	public void setCategorias(Categoria cat){
		this.cats.add(cat);
	}
	public List<Categoria> getCategorias(){
		return this.cats;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
