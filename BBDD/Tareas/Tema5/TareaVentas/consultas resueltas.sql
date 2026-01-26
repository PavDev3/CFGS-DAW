-- 1. Mostrar los empleados (idEmpleado, nombre, apellido) cuyo trabajo es 'Sales Rep'.
SELECT idEmpleado, nombre, apellido 
FROM Empleados 
WHERE cargo = 'Sales Rep';

-- 2. Mostrar los empleados (idEmpleado, nombre, apellidos) que trabajan en la oficina con código 4 y cuyo trabajo es 'Sales Rep'.
SELECT idEmpleado, nombre, apellido 
FROM Empleados 
WHERE idOficina = '4' AND cargo = 'Sales Rep';

-- 3. Mostrar los empleados (idEmpleado, nombre, apellidos) que trabajan en la oficina 2 o 4 en orden descendente por idEmpleado.
SELECT idEmpleado, nombre, apellido 
FROM Empleados 
WHERE idOficina IN ('2', '4') 
ORDER BY idEmpleado DESC;

-- 4. Mostrar el nombre y la cantidad de 'Motorcycles' cuya cantidad en stock es de 5000 a 7000 en orden ascendente por cantidad.
SELECT p.nombreProducto, p.cantidadEnStock 
FROM Productos p
INNER JOIN lineasProductos lp ON p.lineasProducto = lp.lineasProducto
WHERE lp.lineasProducto = 'Motorcycles' 
  AND p.cantidadEnStock BETWEEN 5000 AND 7000
ORDER BY p.cantidadEnStock ASC;

-- 5. Mostrar el nombre de los automóviles ('Classic Cars', 'Trucks and Buses', 'Vintage Cars') cuya cantidad en stock es mayor a 6000 y con precio de compra es menor o igual a 90 dólares.
SELECT p.nombreProducto, p.cantidadEnStock, p.precioDeCompra 
FROM Productos p
INNER JOIN lineasProductos lp ON p.lineasProducto = lp.lineasProducto
WHERE lp.lineasProducto IN ('Classic Cars', 'Trucks and Buses', 'Vintage Cars') 
  AND p.cantidadEnStock > 6000 
  AND p.precioDeCompra <= 90.00;

-- 6. Mostrar los 3 primeros productos (nombre, escala y precio de compra) con escala 1:10 o 1:18 en orden ascendente por precio.
SELECT p.nombreProducto, p.escalaProducto, p.precioDeCompra 
FROM Productos p
WHERE p.escalaProducto IN ('1:10', '1:18')
ORDER BY p.precioDeCompra ASC
LIMIT 3;

-- 7. Mostrar el código de oficina y la ciudad de aquellas oficinas con un provincia desconocido (NULL).
SELECT idOficina, ciudad 
FROM Oficinas 
WHERE provincia IS NULL;

-- 8. Mostrar el id de oficina, ciudad y provincia de aquellas oficinas que no están en 'USA' ni en 'Australia'.
SELECT idOficina, ciudad, provincia 
FROM Oficinas 
WHERE pais NOT IN ('USA', 'Australia');

-- 9. Mostrar el campo 'idPedido' de los pedidos entregados en abril de 2005.
SELECT idPedido 
FROM Pedidos 
WHERE MONTH(fechaEntrega) = 4 AND YEAR(fechaEntrega) = 2005 AND estado = 'shipped';

-- 10. Mostrar todos los datos de los clientes cuyo país sea uno de estos: 'USA', 'France', 'Poland' o 'Spain' y cuyos códigos postales comiencen con '4'.
SELECT * 
FROM Clientes 
WHERE pais IN ('USA', 'France', 'Poland', 'Spain') 
  AND codPostal LIKE '4%';

-- 11. Mostrar todos los datos de los clientes cuyo país no sea uno de estos: 'USA', 'France' o 'Poland'.
SELECT * 
FROM Clientes 
WHERE pais NOT IN ('USA', 'France', 'Poland');

-- 12. Mostrar el código y el nombre de los productos cuyos nombres contengan la palabra "Ford" en orden ascendente por cantidad en stock.
SELECT idProducto, nombreProducto 
FROM Productos 
WHERE nombreProducto LIKE '%Ford%' 
ORDER BY cantidadEnStock ASC;

-- 13. Mostrar el número y el nombre de los clientes cuyos nombres terminen en "co."
SELECT telefono, nombreCliente 
FROM Clientes 
WHERE nombreCliente LIKE '%co.';

-- 14. Mostrar el apellido, el nombre y la ciudad de los empleados de las oficinas en 'USA' donde trabajan.
SELECT e.apellido, e.nombre, o.ciudad 
FROM Empleados e
INNER JOIN Oficinas o ON e.idOficina = o.idOficina 
WHERE o.pais = 'USA';

-- 15. Mostrar la dirección de correo electrónico de los empleados y el número de teléfono de la oficina donde trabajan.
SELECT e.email, o.telefono 
FROM Empleados e
INNER JOIN Oficinas o ON e.idOficina = o.idOficina;

-- 16. Mostrar el nombre y el apellido de los clientes y el nombre y el apellido del empleado de ventas relacionado con ellos.
SELECT c.nombreCliente, c.apellidoContacto, e.nombre, e.apellido 
FROM Clientes c
LEFT JOIN Empleados e ON c.idEmpleadoResponsable = e.idEmpleado;

-- 17. Mostrar todos los pagos (número de cheque, importe y nombre del cliente) en orden descendente por importe.
SELECT pa.checkNumber, pa.cantidad, c.nombreCliente 
FROM Pagos pa
INNER JOIN Clientes c ON pa.idCliente = c.idCliente 
ORDER BY pa.cantidad DESC;

-- 18. Mostrar el id y nombre de los productos así como la descripción textual de su línea de productos; solo de los productos con precios de venta menores a '80' dólares.
SELECT p.idProducto, p.nombreProducto, lp.textoDescripcion 
FROM Productos p
INNER JOIN lineasProductos lp ON p.lineasProducto = lp.lineasProducto
WHERE p.precioDeVenta < 80.00;

-- 19. Mostrar el nombre y la cantidad de productos que incluye el pedido '10100'.
SELECT p.nombreProducto, dp.cantidadPedida 
FROM DetallesPedidos dp
INNER JOIN Productos p ON dp.idProducto = p.idProducto 
WHERE dp.idPedido = 10100;

-- 20. Incluir la fecha y el estado de los pedidos en la consulta anterior.
SELECT p.idPedido, p.fechaPedido, p.fechaEntrega, p.estado, pr.nombreProducto, dp.cantidadPedida 
FROM Pedidos p
INNER JOIN DetallesPedidos dp ON p.idPedido = dp.idPedido
INNER JOIN Productos pr ON dp.idProducto = pr.idProducto 
WHERE p.idPedido = 10100;