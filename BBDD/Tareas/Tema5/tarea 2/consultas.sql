-- 1. 
SELECT category_name, description
FROM categories
ORDER BY category_name;

-- 2. 
SELECT contact_name, address, city
FROM customers
WHERE country NOT IN ('Germany', 'Mexico', 'Spain');

-- 3. 
SELECT order_date, shipped_date, customer_id, freight
FROM orders
WHERE order_date = '2018-02-26';

-- 4. 
SELECT employee_id, order_id, customer_id, required_date, shipped_date
FROM orders
WHERE shipped_date > required_date;

-- 5. 
SELECT order_id
FROM orders
WHERE order_id % 2 = 0;

-- 6. 
SELECT city, company_name, contact_name
FROM customers
WHERE city LIKE '%L%'
ORDER BY contact_name;

-- 7. 
SELECT company_name, contact_name, fax
FROM customers
WHERE fax IS NOT NULL;

-- 8. 
SELECT first_name, last_name, hire_date
FROM employees
ORDER BY hire_date DESC
LIMIT 1;

-- 9. 
SELECT ROUND(AVG(unit_price), 2) AS average_unit_price,
       SUM(units_in_stock) AS total_units_in_stock,
       SUM(CASE WHEN discontinued THEN 1 ELSE 0 END) AS total_discontinued_products
FROM products;

-- 1. 
SELECT p.product_name, s.company_name, c.category_name
FROM products p
JOIN categories c ON p.category_id = c.category_id
JOIN suppliers s ON p.supplier_id = s.supplier_id;

-- 2. 
SELECT c.category_name, ROUND(AVG(p.unit_price), 2) AS average_unit_price
FROM products p
JOIN categories c ON p.category_id = c.category_id
GROUP BY c.category_name;

-- 3. 
--    Crear una columna que indique si es 'customers' o 'suppliers'.
SELECT city, company_name, contact_name, 'customers' AS source
FROM customers
UNION ALL
SELECT city, company_name, contact_name, 'suppliers'
FROM suppliers;

-- 1. 
SELECT
  e.first_name,
  e.last_name,
  COUNT(o.order_id) As num_orders,
  (
    CASE
      WHEN o.shipped_date <= o.required_date THEN 'On Time'
      WHEN o.shipped_date > o.required_date THEN 'Late'
      WHEN o.shipped_date is null THEN 'Not Shipped'
    END
  ) AS shipped
FROM orders o
  JOIN employees e ON e.employee_id = o.employee_id
GROUP BY
  e.first_name,
  e.last_name,
  shipped
ORDER BY
  e.last_name,
  e.first_name,
  num_orders DESC
