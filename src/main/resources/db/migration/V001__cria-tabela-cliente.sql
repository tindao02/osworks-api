CREATE TABLE cliente
(
    id bigint not null AUTO_INCREMENT,
    nome varchar(60) not null,
    email varchar (255) not null,
    fone varchar(20) not null,
    
    PRIMARY KEY (id)
);