CREATE TABLE evento (
	id BIGINT NOT NULL AUTO_INCREMENT,
  	entrega_id BIGINT NOT NULL,
  	descricao TEXT NOT NULL,
  	data_registro DATETIME NOT NULL,

  	PRIMARY KEY (id)
);

ALTER TABLE evento
ADD CONSTRAINT fk_evento_entrega
FOREIGN KEY (entrega_id) REFERENCES entrega (id);