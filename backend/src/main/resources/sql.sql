CREATE SCHEMA seguridad AUTHORIZATION postgres;

CREATE TABLE seguridad.persona ( id bigserial NOT NULL, apellidos varchar(255) NULL, fecha_nacimiento date NULL, identificacion varchar(255) NOT NULL, nombres varchar(255) NULL, CONSTRAINT persona_pkey PRIMARY KEY (id), CONSTRAINT ukr5vsms84ih2viwd6tatk9o5pq);
CREATE TABLE seguridad.rol ( id bigserial NOT NULL, codigo varchar(255) NOT NULL, descripcion varchar(255) NULL, CONSTRAINT rol_pkey PRIMARY KEY (id), CONSTRAINT ukfb7ghyrqk7vsxaqf6bx7m0pjd UNIQUE (codigo));
CREATE TABLE seguridad.rol_opciones ( id bigserial NOT NULL, codigo varchar(255) NULL, descripcion varchar(255) NULL, CONSTRAINT rol_opciones_pkey PRIMARY KEY (id));
CREATE TABLE seguridad.menus ( id bigserial NOT NULL, activo bool NOT NULL, descripcion varchar(255) NOT NULL, icono varchar(255) NULL, nombre varchar(255) NOT NULL, url varchar(255) NULL, padre_id int8 NULL, CONSTRAINT menus_pkey PRIMARY KEY (id), CONSTRAINT uk88yk7x05ylq4jmo6vn6yxsslv UNIQUE (nombre), CONSTRAINT fkh1s5pwpb6uyflaw9myldyxrpe FOREIGN KEY (padre_id) REFERENCES seguridad.menus(id));
CREATE TABLE seguridad.rol_rol_opciones ( rol_id int8 NOT NULL, rol_opcion_id int8 NOT NULL, CONSTRAINT fk1y60syjfj8ktbu31gq7woq4w5 FOREIGN KEY (rol_id) REFERENCES seguridad.rol(id), CONSTRAINT fkr6omlmekwv8dpl5otfqa81ngo FOREIGN KEY (rol_opcion_id) REFERENCES seguridad.rol_opciones(id));
CREATE TABLE seguridad.usuario ( id bigserial NOT NULL, email varchar(255) NULL, estado varchar(255) NULL, intentos_fallidos int4 NULL, "password" varchar(255) NULL, username varchar(255) NULL, persona_id int8 NULL, CONSTRAINT usuario_pkey PRIMARY KEY (id), CONSTRAINT fklse7lqghmt3r1sp298ss9s5bc FOREIGN KEY (persona_id) REFERENCES seguridad.persona(id));
CREATE TABLE seguridad.usuario_rol ( usuario_id int8 NOT NULL, rol_id int8 NOT NULL, CONSTRAINT fk610kvhkwcqk2pxeewur4l7bd1 FOREIGN KEY (rol_id) REFERENCES seguridad.rol(id), CONSTRAINT fkbyfgloj439r9wr9smrms9u33r FOREIGN KEY (usuario_id) REFERENCES seguridad.usuario(id));
CREATE TABLE seguridad."session" ( id bigserial NOT NULL, fecha_login timestamp(6) NULL, fecha_logout timestamp(6) NULL, usuario_id int8 NULL, CONSTRAINT session_pkey PRIMARY KEY (id), CONSTRAINT fkag1g6sbu5pk4pr06c3et905v9 FOREIGN KEY (usuario_id) REFERENCES seguridad.usuario(id));

CREATE TABLE seguridad.login_intentos
(
    id bigserial NOT NULL,
    usuario_id bigint,
    fecha timestamp without time zone,
    intento integer,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS seguridad.login_intentos
    OWNER to postgres;

INSERT INTO seguridad.rol (id, codigo, descripcion) VALUES(1, 'USER', 'Usuario');
INSERT INTO seguridad.rol (id, codigo, descripcion) VALUES(2, 'ADMIN', 'Administrador');

INSERT INTO seguridad.menus (id, activo, descripcion, icono, nombre, url, padre_id) VALUES(1, true, 'Home', NULL, 'Home', NULL, NULL);
INSERT INTO seguridad.menus (id, activo, descripcion, icono, nombre, url, padre_id) VALUES(2, true, 'Registro', NULL, 'Registro', NULL, NULL);
INSERT INTO seguridad.menus (id, activo, descripcion, icono, nombre, url, padre_id) VALUES(3, true, 'Listado', NULL, 'Listado', NULL, NULL);

-- DROP PROCEDURE contabilidad.acontabilidad_documento(int8, text);

CREATE OR REPLACE PROCEDURE seguridad.registrar_intento_login(p_usuario bigint)
 LANGUAGE plpgsql
AS $procedure$
DECLARE
	intento integer;
BEGIN
  ---
  	intento = (SELECT intento + 1 FROM seguridad.login_intentos where usuario_id = p_usuario);

	INSERT INTO seguridad.login_intentos (usuario_id, fecha, intento) VALUES(p_usuario, now(), intento);
  ---
END;
$procedure$
;

