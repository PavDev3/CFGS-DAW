CREATE DATABASE IF NOT EXISTS ventas; 

USE ventas;

CREATE TABLE cliente ( 
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25), 
    primer_apellido VARCHAR(15) NOT NULL, 
    ciudad VARCHAR(100), 
    categoria INT);

CREATE TABLE comercial ( id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
                        nombre VARCHAR(100) NOT NULL, 
                        apellido1 VARCHAR(100) NOT NULL, 
                        apellido2 VARCHAR(100), 
                        ciudad VARCHAR(100), 
                        comision FLOAT);

ALTER TABLE CLIENTE MODIFY COLUMN nombre VARCHAR(100) NOT NULL;
ALTER TABLE CLIENTE
CHANGE primer_apellido apellido1 VARCHAR(100);
ALTER TABLE CLIENTE ALTER COLUMN apellido1 SET NOT NULL;
ALTER TABLE CLIENTE ADD COLUMN apellido2 VARCHAR(100) null after apellido1;
alter table cliente drop column categoria;
ALTER TABLE COMERCIAL MODIFY COLUMN comision int DEFAULT 10;