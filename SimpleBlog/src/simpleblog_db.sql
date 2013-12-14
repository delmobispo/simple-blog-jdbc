DROP DATABASE simpleblog;

CREATE DATABASE simpleblog;

USE simpleblog;

CREATE TABLE roles_users(
	role_id INT(9) UNSIGNED NOT NULL AUTO_INCREMENT,
	role_nome VARCHAR(255) NOT NULL,
	role_access INT(1) UNSIGNED NOT NULL DEFAULT '2',/* niveis de privilegios de a 1 a 2 sendo 2 o mais baixo */
	
	UNIQUE KEY(role_nome),
	PRIMARY KEY(role_id)
);

CREATE TABLE users(
	user_id INT(9) UNSIGNED NOT NULL AUTO_INCREMENT,
	user_nome VARCHAR(255) NOT NULL,
	user_email VARCHAR(255) NOT NULL,
	user_perfil TEXT,
	user_login VARCHAR(255) NOT NULL,
	user_pass VARCHAR(255) NOT NULL,
	role_id INT(9) UNSIGNED NOT NULL,
	user_register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	user_modification_date TIMESTAMP,
	
	KEY(user_nome),
	UNIQUE KEY(user_email,user_login),
	PRIMARY KEY(user_id),
	CONSTRAINT fk_role_users_id
	FOREIGN KEY(role_id)
	REFERENCES roles_users(role_id)
);

CREATE TABLE categorias(
	cat_id INT(9) UNSIGNED NOT NULL AUTO_INCREMENT,
	cat_nome VARCHAR(255) NOT NULL,
	cat_describe TEXT,
	cat_parent INT(9) UNSIGNED NOT NULL DEFAULT '1',
	
	UNIQUE KEY(cat_nome),
	PRIMARY KEY(cat_id),
	CONSTRAINT fk_cat_id_auto
	FOREIGN KEY(cat_parent)
	REFERENCES categorias(cat_id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE posts(
	post_id INT(9) UNSIGNED NOT NULL AUTO_INCREMENT,
	post_nome VARCHAR(255) NOT NULL,
	post_content TEXT,
	post_excerpt TEXT,
	user_id INT(9) UNSIGNED NOT NULL,
	post_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	post_modification_date TIMESTAMP,
	post_status ENUM('PUBLICADO','RASCUNHO','LIXEIRA') DEFAULT 'PUBLICADO',
	
	PRIMARY KEY(post_id),
	KEY(post_nome),
	CONSTRAINT fk_user_id
	FOREIGN KEY(user_id)
	REFERENCES users(user_id)
);

CREATE TABLE relations_cat_post(
	relation_id INT(9) UNSIGNED NOT NULL AUTO_INCREMENT,
	post_id INT(9) UNSIGNED NOT NULL,
	cat_id INT(9) UNSIGNED NOT NULL,
	
	UNIQUE KEY(relation_id),
	PRIMARY KEY(post_id, cat_id),
	CONSTRAINT fk_post
	FOREIGN KEY(post_id)
	REFERENCES posts(post_id)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	CONSTRAINT fk_cat
	FOREIGN KEY(cat_id)
	REFERENCES categorias(cat_id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE comentarios(
	coment_id INT(9) UNSIGNED NOT NULL AUTO_INCREMENT,
	coment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
	coment_content TEXT NOT NULL,
	coment_author VARCHAR(255) NOT NULL,
	coment_author_email VARCHAR(255) NOT NULL,
	coment_ip VARCHAR(15) NOT NULL,
	coment_aprovado BOOLEAN NOT NULL DEFAULT '0', /* 0 comentario nao esta aprova 1 esta aprovado*/
	post_id INT(9) UNSIGNED NOT NULL,
	
	KEY(coment_author,coment_date),
	PRIMARY KEY(coment_id),
	CONSTRAINT fk_post_id
	FOREIGN KEY(post_id)
	REFERENCES posts(post_id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
);


INSERT INTO roles_users(role_nome,role_access) VALUES ('Administrador',1);
INSERT INTO roles_users(role_nome) VALUES('Editor');

INSERT INTO users(user_nome,user_email,user_perfil,user_login,user_pass,role_id)
VALUES (
	'Administrator',
	'admin@simpleblog.com.br',
	'Usuário padrão, feita na criação do banco de dados',
	'admin',
	'21232f297a57a5a743894a0e4a801fc3', /* admin */
	1
);
INSERT INTO users(user_nome,user_email,user_perfil,user_login,user_pass,role_id)
VALUES (
	'Editor',
	'editor@simpleblog.com.br',
	'Usuário padrão, feita na criação do banco de dados',
	'editor',
	'5aee9dbd2a188839105073571bee1b1f', /* editor */
	2
);

INSERT INTO categorias (cat_nome,cat_describe, cat_parent) VALUES('Pai de todos','Todos herdam de mim',1);
INSERT INTO categorias (cat_nome, cat_describe, cat_parent) VALUES('Sem categoria','Categoria default não pode ser deletado',1);
INSERT INTO categorias (cat_nome, cat_describe, cat_parent) VALUES('Exemplo','categoria filho de filho',2);

INSERT INTO posts(post_nome,post_content,post_excerpt,user_id,post_status) VALUES('Olá Mundo', 'Olá Mundo, este é um post de exemplo, você pode excluí-lo se quiser ou editá-lo se achar melhor','Um simples olá',1,'PUBLICADO');
INSERT INTO posts(post_nome,post_content,post_excerpt,user_id,post_status) VALUES('Olá Mundo 2', 'Olá MUndo 2, este é um post de exemplo PARA EDITOR, você pode excluí-lo se quiser ou editá-lo se achar melhor','Um simples olá2',2,'PUBLICADO');

INSERT INTO relations_cat_post(post_id,cat_id) VALUES(1,2);
INSERT INTO relations_cat_post(post_id,cat_id) VALUES(2,3);

INSERT INTO comentarios(coment_content,coment_author,coment_author_email,coment_ip,post_id) VALUES('Ola sou um simples comentario', 'Fulano','fulano@teste.com','127.0.0.1',1);
INSERT INTO comentarios(coment_content,coment_author,coment_author_email,coment_ip,post_id) VALUES('Ola sou um simples comentario segunda versão', 'Fulano2','fulano2@teste.com','127.0.0.1',2);

SHOW TABLES;