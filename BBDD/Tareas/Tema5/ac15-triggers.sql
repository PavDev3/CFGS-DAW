-- Este triger llama  al afuncion crear email del ejercicio 11 de la tabla alumnado
-- Si el email es nulo o vacio se le asigna el valor que devuelve la funcion crearEmail


delimiter //
create or replace trigger trg_alumnado_before_insert
before insert on alumnado
for each row
begin
    if new.email is null or new.email = '' then
        set new.email = crearEmail(new.nombre, new.apellidos, new.curso);
    end if;
end //
delimiter ;

-- create table for log of email changes
create table logCambiosEmail (
    id int auto_increment primary key,
    idAlumno int,
    fechaHora datetime,
    oldEmail varchar(255),
    newEmail varchar(255)
);

delimiter //
create or replace trigger trg_alumnado_after_update
after update on alumnado
for each row
begin
    if old.email != new.email then
        insert into logCambiosEmail (idAlumno, fechaHora, oldEmail, newEmail)
        values (new.id, now(), old.email, new.email);
    end if;
end //
delimiter ; 

