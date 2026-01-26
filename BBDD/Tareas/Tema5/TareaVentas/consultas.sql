1. select idEmpleado, nombre , apellido from Empleados where cargo = 'Sales Rep';
2. select idEmpleado, nombre, apellido from Empleados where idOficina = '4' and cargo = 'Sales Rep';
3. select idEmpleado, nombre, apellido from Empleados where idOficina = '4' OR '2' ORDER BY idEmpleado ASC;
--Mostrar el nombre y la cantidad de 'Motorcycles' cuya cantidad en stock es de 5000 a 7000 en orden ascendente por cantidad.
4. SELECT nombreProducto, cantidadEnStock 
   FROM Productos 
   WHERE lineasProducto = 'Motorcycles' 
     AND cantidadEnStock BETWEEN 5000 AND 7000 
   ORDER BY cantidadEnStock ASC;
5.




