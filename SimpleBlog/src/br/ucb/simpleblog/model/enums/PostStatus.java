package br.ucb.simpleblog.model.enums;

/**
 * @author Delmo
 * @version 1.0
 */
public enum PostStatus {
	PUBLICADO, RASCUNHO, LIXEIRA;
	
	public PostStatus[] getValues(){
		return PostStatus.values();
	}
}
