-- a
Select d.PROFESOR from D left join I on d.Profesor =  d.Profesor where I.Profesor is null;

-- b
Select I.Profesor, count(distinct I.modulo) AS Num_Modulos 
FROM I
Group by I.profesor
having count(distinct I.modulo) = 2;

-- c
SELECT D.DEPARTAMENTO
FROM D 
JOIN I ON I.PROFESOR = D.PROFESOR
JOIN E ON E.MODULO = I.MODULO
GROUP BY D.DEPARTAMENTO
HAVING COUNT(DISTINCT E.CICLO) > 1;

-- d
Select distinct M.Alumno
From M
Where M.Alumno not in (
  Select M.Alumno
  from M 
  where M.Nota < 5
);

-- e


-- f 
SELECT E.MODULO
FROM E 
WHERE E.CICLO = 'DAW'
ORDER BY E.MODULO ASC;

--g
Select I.Profesor, count(distinct I.modulo) AS Num_Modulos 
FROM I
Group by I.profesor
having count(distinct I.modulo) = 2;