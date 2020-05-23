drop database if exists reproductor;
create database reproductor;
use reproductor;

create table USUARIO
	
	(
		IDUsuario			int(4)			unsigned auto_increment,
		Nombre				varchar(20)		not null,
		Password			varchar(20)		not null,
		Pais				varchar(15)		not null,		
		estiloFav			varchar(30),
		artistaFav			varchar(30),
					primary key (IDUsuario)

	) Engine = innoDB
	
	auto_increment = 1;

create table CANCION
    
    (
    	IDCancion			int(6)			unsigned auto_increment,
    	Titulo				varchar(20)		not null,
    	Estilo 				varchar(30),
    	nombreProductor		varchar(30),
    				primary key (IDCancion)
	) Engine = innoDB
    
    auto_increment = 1;
		
create table consume

	(
		ID_Usuario			int(4) unsigned, 
		ID_Cancion			int(6) unsigned,

				Foreign key (ID_Usuario)
							References USUARIO (IDUsuario)
								On Delete Cascade On Update Cascade,

						Foreign key (ID_Cancion)
							References CANCION (IDCancion)
								On Delete Cascade On Update Cascade
	
	) Engine = innoDB;

insert into USUARIO (Nombre, Password, Pais, estiloFav, artistaFav) values ("Antonio","prueba","España","EDM","Martin Garrix");




insert into CANCION (Titulo, Estilo, nombreProductor) values ("Higher Ground","Progressive House","Martin Garrix");
insert into CANCION (Titulo, Estilo, nombreProductor) values ("Diosa","Trap","Myke Towers");
insert into CANCION (Titulo, Estilo, nombreProductor) values ("Forever Alone","Trap","Paulo Londra");
insert into CANCION (Titulo, Estilo, nombreProductor) values ("Cicatrices","Rap","Natos y Waor");
insert into CANCION (Titulo, Estilo, nombreProductor) values ("Without You","Future House","Brooks & Julian Jordan");
insert into CANCION (Titulo, Estilo, nombreProductor) values ("<3","Trap","Bad Bunny");
insert into CANCION (Titulo, Estilo, nombreProductor) values ("La Dificil","Reggaeton","Bad Bunny");
insert into CANCION (Titulo, Estilo, nombreProductor) values ("Soy Peor","Trap","Bad Bunny");


	