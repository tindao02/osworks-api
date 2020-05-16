CREATE TABLE comentario 
( 
	id bigint NOT NULL AUTO_INCREMENT, 
	ordem_servico_id bigint not null, 
	descricao text not null, 
	data_envio datetime not null, 
	
	PRIMARY KEY (id), 
	
	CONSTRAINT fk_comentarios_ordem_servico FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico (id)
);