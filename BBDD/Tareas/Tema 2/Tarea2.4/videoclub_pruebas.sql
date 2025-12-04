-- ######################################################################
-- PRUEBAS DE ELIMINACIÓN Y ACTUALIZACIÓN DE REGISTROS
-- Este script demuestra el comportamiento de las restricciones de integridad
-- ######################################################################

USE videoclub;

-- ######################################################################
-- ELIMINACIÓN DE REGISTROS (DELETE)
-- ######################################################################

-- ======================================================================
-- ELIMINACIONES QUE FUNCIONAN (con CASCADE)
-- ======================================================================

-- 1. Eliminar un cliente (CASCADE elimina automáticamente sus teléfonos y alquileres)
-- RESTRICCIÓN: telefono_cliente tiene ON DELETE CASCADE con cliente
-- RESTRICCIÓN: alquila tiene ON DELETE CASCADE con cliente
-- RESULTADO: Se eliminará el cliente y automáticamente sus teléfonos y alquileres asociados
DELETE FROM cliente WHERE id_cliente = 10;
-- Verificación: Los teléfonos del cliente 10 y sus alquileres se eliminaron automáticamente
-- SELECT * FROM telefono_cliente WHERE id_cliente = 10; -- Debe devolver 0 filas
-- SELECT * FROM alquila WHERE id_cliente = 10; -- Debe devolver 0 filas

-- 2. Eliminar un director (CASCADE elimina automáticamente las relaciones en dirige)
-- RESTRICCIÓN: dirige tiene ON DELETE CASCADE con director
-- RESULTADO: Se eliminará el director y automáticamente sus relaciones en dirige
DELETE FROM director WHERE id_director = 10;
-- Verificación: Las relaciones del director 10 en dirige se eliminaron automáticamente
-- SELECT * FROM dirige WHERE id_director = 10; -- Debe devolver 0 filas

-- 3. Eliminar una categoría (CASCADE elimina automáticamente las relaciones en pertenece)
-- RESTRICCIÓN: pertenece tiene ON DELETE CASCADE con categoria
-- RESULTADO: Se eliminará la categoría y automáticamente sus relaciones en pertenece
DELETE FROM categoria WHERE id_categoria = 10;
-- Verificación: Las relaciones de la categoría 10 en pertenece se eliminaron automáticamente
-- SELECT * FROM pertenece WHERE id_categoria = 10; -- Debe devolver 0 filas

-- 4. Eliminar una película (CASCADE elimina automáticamente relaciones en dirige y pertenece)
-- RESTRICCIÓN: dirige tiene ON DELETE CASCADE con pelicula
-- RESTRICCIÓN: pertenece tiene ON DELETE CASCADE con pelicula
-- NOTA: Pero NO puede eliminarse si tiene ejemplares (RESTRICT)
-- RESULTADO ESPERADO: Esta sentencia FALLARÁ porque hay ejemplares asociados
-- (Ver ejemplo de fallo más abajo)

-- ======================================================================
-- ELIMINACIONES QUE FALLAN (con RESTRICT)
-- ======================================================================

-- 5. Intentar eliminar un proveedor que tiene películas asociadas
-- RESTRICCIÓN: pelicula tiene ON DELETE RESTRICT con proveedor
-- RESULTADO ESPERADO: ERROR - No se puede eliminar porque hay películas que dependen de él
-- ERROR ESPERADO: Cannot delete or update a parent row: a foreign key constraint fails
DELETE FROM proveedor WHERE id_proveedor = 1;
-- COMENTARIO: Esta sentencia DEBE FALLAR como comprobación de la restricción RESTRICT
-- Para eliminar el proveedor, primero hay que eliminar o cambiar las películas que lo referencian

-- 6. Intentar eliminar una película que tiene ejemplares asociados
-- RESTRICCIÓN: ejemplar tiene ON DELETE RESTRICT con pelicula
-- RESULTADO ESPERADO: ERROR - No se puede eliminar porque hay ejemplares que dependen de ella
-- ERROR ESPERADO: Cannot delete or update a parent row: a foreign key constraint fails
DELETE FROM pelicula WHERE id_pelicula = 1;
-- COMENTARIO: Esta sentencia DEBE FALLAR como comprobación de la restricción RESTRICT
-- Para eliminar la película, primero hay que eliminar todos sus ejemplares

-- 7. Intentar eliminar un ejemplar que está alquilado
-- RESTRICCIÓN: alquila tiene ON DELETE RESTRICT con ejemplar
-- RESULTADO ESPERADO: ERROR - No se puede eliminar porque hay alquileres que dependen de él
-- ERROR ESPERADO: Cannot delete or update a parent row: a foreign key constraint fails
DELETE FROM ejemplar WHERE id_ejemplar = 1;
-- COMENTARIO: Esta sentencia DEBE FALLAR si el ejemplar tiene alquileres activos
-- Para eliminar el ejemplar, primero hay que eliminar o finalizar sus alquileres

-- 8. Intentar eliminar un trabajador que tiene alquileres asociados
-- RESTRICCIÓN: alquila tiene ON DELETE RESTRICT con trabajador
-- RESULTADO ESPERADO: ERROR - No se puede eliminar porque hay alquileres que dependen de él
-- ERROR ESPERADO: Cannot delete or update a parent row: a foreign key constraint fails
DELETE FROM trabajador WHERE id_trabajador = 1;
-- COMENTARIO: Esta sentencia DEBE FALLAR como comprobación de la restricción RESTRICT
-- Para eliminar el trabajador, primero hay que eliminar o cambiar los alquileres que lo referencian

-- ======================================================================
-- ELIMINACIONES CONDICIONALES (eliminar después de limpiar dependencias)
-- ======================================================================

-- 9. Eliminar un ejemplar que NO tiene alquileres (debe funcionar)
-- Primero verificamos que no tenga alquileres, luego lo eliminamos
-- RESULTADO: Funcionará si el ejemplar no tiene alquileres asociados
DELETE FROM ejemplar WHERE id_ejemplar = 20 AND id_ejemplar NOT IN (
    SELECT DISTINCT id_ejemplar FROM alquila
);

-- 10. Eliminar una película después de eliminar todos sus ejemplares
-- Paso 1: Eliminar todos los ejemplares de la película 9
DELETE FROM ejemplar WHERE id_pelicula = 9;
-- Paso 2: Ahora sí podemos eliminar la película (ya no tiene ejemplares)
DELETE FROM pelicula WHERE id_pelicula = 9;
-- COMENTARIO: Esta secuencia funciona porque primero eliminamos las dependencias RESTRICT

-- ######################################################################
-- ACTUALIZACIÓN DE REGISTROS (UPDATE)
-- ######################################################################

-- ======================================================================
-- ACTUALIZACIONES QUE FUNCIONAN (con CASCADE)
-- ======================================================================

-- 11. Actualizar el ID de un cliente (CASCADE actualiza automáticamente las referencias)
-- RESTRICCIÓN: telefono_cliente tiene ON UPDATE CASCADE con cliente
-- RESTRICCIÓN: alquila tiene ON UPDATE CASCADE con cliente
-- RESULTADO: Se actualizará el ID del cliente y automáticamente las referencias en otras tablas
UPDATE cliente SET id_cliente = 100 WHERE id_cliente = 9;
-- Verificación: Los teléfonos y alquileres ahora referencian id_cliente = 100
-- SELECT * FROM telefono_cliente WHERE id_cliente = 100; -- Debe mostrar los teléfonos
-- SELECT * FROM alquila WHERE id_cliente = 100; -- Debe mostrar los alquileres

-- 12. Actualizar el ID de un proveedor (CASCADE actualiza automáticamente las películas)
-- RESTRICCIÓN: pelicula tiene ON UPDATE CASCADE con proveedor
-- RESULTADO: Se actualizará el ID del proveedor y automáticamente las referencias en pelicula
UPDATE proveedor SET id_proveedor = 100 WHERE id_proveedor = 8;
-- Verificación: Las películas ahora referencian id_proveedor = 100
-- SELECT * FROM pelicula WHERE id_proveedor = 100; -- Debe mostrar las películas

-- 13. Actualizar el ID de una película (CASCADE actualiza ejemplares, dirige y pertenece)
-- RESTRICCIÓN: ejemplar tiene ON UPDATE CASCADE con pelicula
-- RESTRICCIÓN: dirige tiene ON UPDATE CASCADE con pelicula
-- RESTRICCIÓN: pertenece tiene ON UPDATE CASCADE con pelicula
-- RESTRICCIÓN: alquila tiene ON UPDATE CASCADE con ejemplar (indirectamente)
-- RESULTADO: Se actualizará el ID de la película y todas las referencias se actualizarán
UPDATE pelicula SET id_pelicula = 100 WHERE id_pelicula = 8;
-- Verificación: Los ejemplares, relaciones en dirige y pertenece ahora referencian id_pelicula = 100
-- SELECT * FROM ejemplar WHERE id_pelicula = 100; -- Debe mostrar los ejemplares
-- SELECT * FROM dirige WHERE id_pelicula = 100; -- Debe mostrar las relaciones
-- SELECT * FROM pertenece WHERE id_pelicula = 100; -- Debe mostrar las relaciones

-- 14. Actualizar el ID de un director (CASCADE actualiza automáticamente las relaciones en dirige)
-- RESTRICCIÓN: dirige tiene ON UPDATE CASCADE con director
-- RESULTADO: Se actualizará el ID del director y automáticamente las referencias en dirige
UPDATE director SET id_director = 100 WHERE id_director = 7;
-- Verificación: Las relaciones en dirige ahora referencian id_director = 100
-- SELECT * FROM dirige WHERE id_director = 100; -- Debe mostrar las relaciones

-- 15. Actualizar el ID de una categoría (CASCADE actualiza automáticamente las relaciones en pertenece)
-- RESTRICCIÓN: pertenece tiene ON UPDATE CASCADE con categoria
-- RESULTADO: Se actualizará el ID de la categoría y automáticamente las referencias en pertenece
UPDATE categoria SET id_categoria = 100 WHERE id_categoria = 7;
-- Verificación: Las relaciones en pertenece ahora referencian id_categoria = 100
-- SELECT * FROM pertenece WHERE id_categoria = 100; -- Debe mostrar las relaciones

-- 16. Actualizar el ID de un trabajador (CASCADE actualiza automáticamente los alquileres)
-- RESTRICCIÓN: alquila tiene ON UPDATE CASCADE con trabajador
-- RESULTADO: Se actualizará el ID del trabajador y automáticamente las referencias en alquila
UPDATE trabajador SET id_trabajador = 100 WHERE id_trabajador = 6;
-- Verificación: Los alquileres ahora referencian id_trabajador = 100
-- SELECT * FROM alquila WHERE id_trabajador = 100; -- Debe mostrar los alquileres

-- 17. Actualizar el ID de un ejemplar (CASCADE actualiza automáticamente los alquileres)
-- RESTRICCIÓN: alquila tiene ON UPDATE CASCADE con ejemplar
-- RESULTADO: Se actualizará el ID del ejemplar y automáticamente las referencias en alquila
UPDATE ejemplar SET id_ejemplar = 100 WHERE id_ejemplar = 7;
-- Verificación: Los alquileres ahora referencian id_ejemplar = 100
-- SELECT * FROM alquila WHERE id_ejemplar = 100; -- Debe mostrar los alquileres

-- ======================================================================
-- ACTUALIZACIONES DE DATOS (sin afectar claves primarias)
-- ======================================================================

-- 18. Actualizar el nombre de un cliente (sin restricciones, siempre funciona)
UPDATE cliente SET nombre = 'María García López Actualizada' WHERE id_cliente = 1;

-- 19. Actualizar el email de un cliente
UPDATE cliente SET email = 'nuevo.email@email.com' WHERE id_cliente = 2;

-- 20. Actualizar el salario de un trabajador
UPDATE trabajador SET salario = 3000.00 WHERE id_trabajador = 2;

-- 21. Actualizar el estado de un ejemplar
UPDATE ejemplar SET estado = 'En reparación' WHERE id_ejemplar = 3;

-- 22. Actualizar la fecha de entrega de un alquiler (finalizar alquiler)
UPDATE alquila SET fecha_entrega = CURDATE() 
WHERE id_cliente = 6 AND id_ejemplar = 18 AND fecha_entrega IS NULL;

-- 23. Actualizar el stock de un ejemplar
UPDATE ejemplar SET stock = stock + 1 WHERE id_ejemplar = 1;

-- 24. Actualizar el género de una película
UPDATE pelicula SET genero = 'Drama/Thriller' WHERE id_pelicula = 3;

-- 25. Actualizar la descripción de una categoría
UPDATE categoria SET descripcion = 'Películas de acción con elementos de ciencia ficción' 
WHERE id_categoria = 1;

-- ######################################################################
-- RESUMEN DE RESTRICCIONES Y COMPORTAMIENTOS
-- ######################################################################

-- RESTRICCIONES CASCADE (eliminación y actualización se propagan):
-- - telefono_cliente -> cliente: DELETE CASCADE, UPDATE CASCADE
-- - dirige -> director: DELETE CASCADE, UPDATE CASCADE
-- - dirige -> pelicula: DELETE CASCADE, UPDATE CASCADE
-- - alquila -> cliente: DELETE CASCADE, UPDATE CASCADE
-- - pertenece -> pelicula: DELETE CASCADE, UPDATE CASCADE
-- - pertenece -> categoria: DELETE CASCADE, UPDATE CASCADE
--
-- RESTRICCIONES RESTRICT (no permite eliminar si hay registros relacionados):
-- - pelicula -> proveedor: DELETE RESTRICT, UPDATE CASCADE
-- - ejemplar -> pelicula: DELETE RESTRICT, UPDATE CASCADE
-- - alquila -> ejemplar: DELETE RESTRICT, UPDATE CASCADE
-- - alquila -> trabajador: DELETE RESTRICT, UPDATE CASCADE
--
-- COMPORTAMIENTO ESPERADO:
-- 1. Las eliminaciones con CASCADE funcionan y eliminan registros relacionados automáticamente
-- 2. Las eliminaciones con RESTRICT fallan si hay registros relacionados
-- 3. Las actualizaciones de claves primarias con CASCADE actualizan automáticamente las referencias
-- 4. Las actualizaciones de datos normales (no claves primarias) siempre funcionan

-- ######################################################################
-- VERIFICACIONES POST-OPERACIÓN
-- ######################################################################

-- Descomentar estas consultas para verificar los cambios realizados:

-- Verificar eliminaciones CASCADE:
-- SELECT COUNT(*) FROM telefono_cliente; -- Debe ser menor que antes
-- SELECT COUNT(*) FROM alquila; -- Debe ser menor que antes
-- SELECT COUNT(*) FROM dirige; -- Debe ser menor que antes
-- SELECT COUNT(*) FROM pertenece; -- Debe ser menor que antes

-- Verificar actualizaciones CASCADE:
-- SELECT * FROM pelicula WHERE id_proveedor = 100; -- Debe mostrar películas actualizadas
-- SELECT * FROM ejemplar WHERE id_pelicula = 100; -- Debe mostrar ejemplares actualizados
-- SELECT * FROM alquila WHERE id_cliente = 100; -- Debe mostrar alquileres actualizados

-- Verificar que las restricciones RESTRICT funcionan:
-- SELECT COUNT(*) FROM pelicula WHERE id_proveedor = 1; -- Debe ser > 0 (no se eliminó)
-- SELECT COUNT(*) FROM ejemplar WHERE id_pelicula = 1; -- Debe ser > 0 (no se eliminó)

