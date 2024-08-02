CREATE TABLE medicos(
	id bigint NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(128) NOT NULL,
	apellido_paterno VARCHAR(128) NOT NULL,
	apellido_materno VARCHAR(128) NOT NULL,
	cedula VARCHAR(128) NOT NULL,
	especialidad VARCHAR(128) NOT NULL,
	
	primary key(id)
);