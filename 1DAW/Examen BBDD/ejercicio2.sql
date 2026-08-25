
-- 2.1
ALTER TABLE empleado ADD COLUMN fecha_alta DATE NOT NULL DEFAULT CURRENT_DATE;

-- 2.2
ALTER TABLE supermercado MODIFY COLUMN domicilio VARCHAR(100);

-- 2.3 
ALTER TABLE pedido ADD CONSTRAINT chk_totalPositivo CHECK (total >= 0);

-- 2.4
ALTER TABLE supermercado DROP COLUMN superficie;

-- 2.5
UPDATE empleado SET fecha_alta = CURRENT_DATE;

-- 2.6 
-- Para eliminar el empleado, primero debemos eliminar los pedidos asociados a él.
DELETE FROM pedido WHERE nifEmpleado = '11111111A';
DELETE FROM empleado WHERE nif = '11111111A';
