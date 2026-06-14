-- Crear el SCHEMA
CREATE USER VETERINARIO IDENTIFIED BY veterinaria
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA UNLIMITED ON users;

ALTER SESSION SET CURRENT_SCHEMA = VETERINARIO;

-- Dar privilegios básicos para conectarse y trabajar con sus tablas
GRANT CREATE SESSION TO VETERINARIO; -- Permiso para hacer login
GRANT CREATE TABLE TO VETERINARIO; --Permiso para crear tablas
GRANT CREATE SEQUENCE TO VETERINARIO; -- Permiso para crear secuencias
GRANT CREATE VIEW TO VETERINARIO; -- Permiso para crear vistas
GRANT CREATE TRIGGER TO VETERINARIO; -- Permiso para crear triggers
GRANT CREATE PROCEDURE TO VETERINARIO; -- Permiso para crear funciones y procedimientos almacenados
GRANT CREATE MATERIALIZED VIEW TO VETERINARIO; -- Permiso para crear vistas materializadas
GRANT CREATE DATABASE LINK TO VETERINARIO; -- Permiso para crear database link

-- Rol resource
GRANT RESOURCE TO VETERINARIO;

-- Preparar entorno
BEGIN
 EXECUTE IMMEDIATE 'DROP TABLE adoptantes CASCADE CONSTRAINTS';
 EXECUTE IMMEDIATE 'DROP TABLE domicilios CASCADE CONSTRAINTS';
 EXECUTE IMMEDIATE 'DROP TABLE mascotas CASCADE CONSTRAINTS';
 EXECUTE IMMEDIATE 'DROP TABLE dispositivos CASCADE CONSTRAINTS';
 EXECUTE IMMEDIATE 'DROP TABLE vacunas CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
 EXECUTE IMMEDIATE 'DROP SEQUENCE seq_adoptantes';
 EXECUTE IMMEDIATE 'DROP SEQUENCE seq_domicilios';
 EXECUTE IMMEDIATE 'DROP SEQUENCE seq_mascotas';
 EXECUTE IMMEDIATE 'DROP SEQUENCE seq_dispositivos';
 EXECUTE IMMEDIATE 'DROP SEQUENCE seq_vacunas';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

--- Secuencias para los ID
CREATE SEQUENCE seq_adoptantes START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_domicilios START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_mascotas START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_dispositivos START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_vacunas START WITH 1 INCREMENT BY 1;

--Tablas
CREATE TABLE adoptantes(
    id_adoptante NUMBER PRIMARY KEY,
    nombre VARCHAR2(70),
    apellido_paterno VARCHAR2(70),
    apellido_materno VARCHAR2(70),
    telefono VARCHAR2(20),
    correo VARCHAR2(150) UNIQUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado NUMBER DEFAULT 1
);

CREATE TABLE domicilios(
    id_domicilio NUMBER PRIMARY KEY,
    id_adoptante NUMBER,
    calle VARCHAR2(100),
    numero_exterior NUMBER,
    colonia VARCHAR2(100),
    ciudad VARCHAR2(100),
    estado_geografico VARCHAR2(100),
    codigo_postal VARCHAR2(10),
    estado NUMBER DEFAULT 1,

    CONSTRAINT fk_domicilios_adoptante
    FOREIGN KEY (id_adoptante)
    REFERENCES adoptantes(id_adoptante)
);

CREATE TABLE mascotas(
    id_mascota NUMBER PRIMARY KEY,
    id_adoptante NUMBER,
    nombre VARCHAR2(70),
    especie VARCHAR2(70),
    raza VARCHAR2(70),
    sexo VARCHAR2(1),
    fecha_nacimiento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    peso NUMBER(10, 2),
    color VARCHAR2(70),
    estado NUMBER DEFAULT 1,

    CONSTRAINT fk_mascotas_adoptante
    FOREIGN KEY (id_adoptante)
    REFERENCES adoptantes(id_adoptante)
);

CREATE TABLE dispositivos(
    id_dispositivo NUMBER PRIMARY KEY,
    id_mascota NUMBER,
    numero_chip VARCHAR2(20),
    tipo_dispositivo VARCHAR2(70),
    fecha_instalacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado NUMBER DEFAULT 1,

    CONSTRAINT fk_dispositivos_mascota
    FOREIGN KEY (id_mascota)
    REFERENCES mascotas(id_mascota)
);

CREATE TABLE vacunas(
    id_vacuna NUMBER PRIMARY KEY,
    id_mascota NUMBER,
    nombre_vacuna VARCHAR2(100),
    fecha_aplicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_proxima_dosis TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
    veterinario_responsable VARCHAR2(100),
    estado NUMBER DEFAULT 1,

    CONSTRAINT fk_vacunas_mascota
    FOREIGN KEY (id_mascota)
    REFERENCES mascotas(id_mascota)
);

-- Cargar con registros
INSERT INTO adoptantes(
                       id_adoptante,
                       nombre,
                       apellido_paterno,
                       apellido_materno,
                       telefono,
                       correo,
                       estado
)
VALUES (seq_adoptantes.NEXTVAL,
        'Ángel',
        'Lira',
        'Hernández',
        '2227959646',
        'angel@gmail.com',
        1
);

INSERT INTO adoptantes(
                       id_adoptante,
                       nombre,
                       apellido_paterno,
                       apellido_materno,
                       telefono,
                       correo,
                       estado
)
VALUES (seq_adoptantes.NEXTVAL,
        'Juan',
        'Flores',
        'García',
        '5567870001',
        'juan@gmail.com',
        1
);

INSERT INTO domicilios(
                       id_domicilio,
                       id_adoptante,
                       calle,
                       numero_exterior,
                       colonia,
                       ciudad,
                       estado_geografico,
                       codigo_postal,
                       estado
)
VALUES (seq_domicilios.NEXTVAL,
        1,
        '5 de mayo',
        6,
        'centro',
        'Calpulalpan',
        'Tlaxcala',
        '90200',
        1
);

INSERT INTO domicilios(
                       id_domicilio,
                       id_adoptante,
                       calle,
                       numero_exterior,
                       colonia,
                       ciudad,
                       estado_geografico,
                       codigo_postal,
                       estado
)
VALUES (seq_domicilios.NEXTVAL,
        2,
        'Matamoros',
        20,
        'centro',
        'Apan',
        'Hidalgo',
        '90210',
        1
);

INSERT INTO domicilios(
                       id_domicilio,
                       id_adoptante,
                       calle,
                       numero_exterior,
                       colonia,
                       ciudad,
                       estado_geografico,
                       codigo_postal,
                       estado
)
VALUES (seq_domicilios.NEXTVAL,
        1,
        'Zitlalpopocatl',
        25,
        'centro',
        'Tlaxcala',
        'Tlaxcala',
        '90290',
        1
);

INSERT INTO mascotas(
                       id_mascota,
                       id_adoptante,
                       nombre,
                       especie,
                       raza,
                       sexo,
                       peso,
                       color,
                       estado
)
VALUES (seq_mascotas.NEXTVAL,
        1,
        'Nick',
        'Gato',
        'Amarillo',
        'M',
        3.5,
        'Amarillo',
        1
);

INSERT INTO mascotas(
                       id_mascota,
                       id_adoptante,
                       nombre,
                       especie,
                       raza,
                       sexo,
                       peso,
                       color,
                       estado
)
VALUES (seq_mascotas.NEXTVAL,
        2,
        'Max',
        'Perro',
        'Pitbull',
        'M',
        6.5,
        'Rojo',
        1
);

INSERT INTO mascotas(
                       id_mascota,
                       id_adoptante,
                       nombre,
                       especie,
                       raza,
                       sexo,
                       peso,
                       color,
                       estado
)
VALUES (seq_mascotas.NEXTVAL,
        1,
        'Chabacano',
        'Perro',
        'Pastor alemán',
        'M',
        7.5,
        'Negro',
        1
);

INSERT INTO dispositivos(
                       id_dispositivo,
                       id_mascota,
                       numero_chip,
                       tipo_dispositivo,
                       estado
)
VALUES (seq_dispositivos.NEXTVAL,
        1,
        '001',
        'GPS',
        1
);

INSERT INTO dispositivos(
                       id_dispositivo,
                       id_mascota,
                       numero_chip,
                       tipo_dispositivo,
                       estado
)
VALUES (seq_dispositivos.NEXTVAL,
        2,
        '050',
        'Microchip',
        1
);

INSERT INTO vacunas(
                       id_vacuna,
                       id_mascota,
                       nombre_vacuna,
                       veterinario_responsable,
                       estado
)
VALUES (seq_vacunas.NEXTVAL,
        1,
        'Vacuna anti rabia',
        'Dr. Mauricio',
        1
);

INSERT INTO vacunas(
                       id_vacuna,
                       id_mascota,
                       nombre_vacuna,
                       veterinario_responsable,
                       estado
)
VALUES (seq_vacunas.NEXTVAL,
        2,
        'Vacuna anti rabia',
        'Dr. Dante',
        1
);

INSERT INTO vacunas(
                       id_vacuna,
                       id_mascota,
                       nombre_vacuna,
                       veterinario_responsable,
                       estado
)
VALUES (seq_vacunas.NEXTVAL,
        3,
        'Vacuna moquillo',
        'Dr. Ernesto',
        1
);

INSERT INTO vacunas(
                       id_vacuna,
                       id_mascota,
                       nombre_vacuna,
                       veterinario_responsable,
                       estado
)
VALUES (seq_vacunas.NEXTVAL,
        3,
        'Vacuna parvovirus',
        'Dr. Ernesto',
        1
);

INSERT INTO mascotas(
                       id_mascota,
                       id_adoptante,
                       nombre,
                       especie,
                       raza,
                       sexo,
                       peso,
                       color,
                       estado
)
VALUES (seq_mascotas.NEXTVAL,
        1,
        'Charlie',
        'Perro',
        'Pastor alemán',
        'M',
        7.5,
        'Amarillo y negro',
        0
);

commit;

