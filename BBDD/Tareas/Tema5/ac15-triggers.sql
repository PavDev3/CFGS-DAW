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

