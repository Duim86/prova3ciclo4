create table contato
(
    id   bigint      not null auto_increment,
    nome varchar(150) not null,
    telefone varchar(15) not null,
    email varchar(150) not null,
    primary key (id)
);