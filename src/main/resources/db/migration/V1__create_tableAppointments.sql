CREATE TABLE appointments(
	id bigint not null auto_increment,
	consultorio varchar(128) not null,
	medico varchar(128) not null,
	fechaDeConsulta datetime not null,
	paciente varchar(128) not null,
	
	primary key(id)
);