# Ejercicios comandos Terminal Linux

1. Listar todos los archivos del directorio bin.
   ls /bin

2. Listar todos los archivos del directorio etc que empiecen por t.
   ls /etc/t*

3. Listar todos los archivos del directorio dev que empiecen por tty y tengan 5 caracteres.
   ls /dev/tty??

4. Listar todos los archivos del directorio dev que empiecen por tty y acaben en 1,2,3 ó 4.
   ls /dev/tty[1-4]

5. Listar todos los archivos del directorio dev que empiecen por t y acaben en S1.
   ls /dev/t*S1

6. Listar todos los archivos, incluidos los ocultos, del directorio raíz.
   ls -la /

7. Mostrar el día y la hora actual.
   date

8. Listar todos los ficheros del directorio HOME.
   ls "$HOME"

9. Crear los directorios dir1, dir2 y dir3 en el directorio PRUEBA. Dentro de dir1 crear el directorio dir11. Dentro del directorio dir3 crear el directorio dir31. Dentro del directorio dir31, crear los directorios dir311 y dir312.
   mkdir -p PRUEBA/dir1/dir11 PRUEBA/dir2 PRUEBA/dir3/dir31/dir311 PRUEBA/dir3/dir31/dir312

10. Comprobar el ejercicio anterior mediante un solo comando.
    find PRUEBA -type d | sort

11. Copiar en el directorio dir311 los archivos de /bin que tengan una a como segunda letra y su nombre tenga cuatro letras.
    cp /bin/?a?? PRUEBA/dir3/dir31/dir311/

12. Copiar el directorio de otro usuario y sus subdirectorios debajo de dir11 (incluido el propio directorio).
    cp -r /home/<usuario> PRUEBA/dir1/dir11/

13. Mover el directorio dir31 y sus subdirectorios debajo de dir2.
    mv PRUEBA/dir3/dir31 PRUEBA/dir2/

14. Borrar los archivos y directorios de dir1, incluido el propio directorio.
    rm -rf PRUEBA/dir1

15. Copiar al directorio dir312 los ficheros del directorio /dev que empiecen por t, acaben en una letra que vaya de la 1 a la 7 y tengan cinco letras en su nombre.
    cp /dev/t???[1-7] PRUEBA/dir2/dir31/dir312/

16. Borrar los archivos de dir312 que no acaben en b y tengan una q como cuarta letra.
    find PRUEBA/dir2/dir31/dir312 -maxdepth 1 -type f ! -name '*b' -name '???q?' -delete

17. Mover el directorio dir312 debajo de dir3.
    mv PRUEBA/dir2/dir31/dir312 PRUEBA/dir3/

18. Crear un enlace simbólico al directorio dir1 dentro del directorio dir3 llamado enlacedir1 (busca información sobre el comando ln).
    ln -s ../dir1 PRUEBA/dir3/enlacedir1

19. Posicionarse en dir3 y, empleando el enlace creado en el ejercicio anterior, crear el directorio nuevo1 dentro de dir1.
    (cd PRUEBA/dir3 && mkdir -p enlacedir1/nuevo1)

20. Utilizando el enlace creado copiar los archivos que empiecen por u del directorio /bin en directorio nuevo1.
    (cd PRUEBA/dir3 && cp /bin/u* enlacedir1/nuevo1/)

21. Borrar todos los archivos y directorios creados durante los ejercicios.
    rm -rf PRUEBA

22. Crear el directorio dir2 y dir3 en el directorio PRUEBA. ¿Cuáles son los actuales permisos del directorio dir2?
    mkdir -p PRUEBA/dir2 PRUEBA/dir3 && ls -ld PRUEBA/dir2

23. Utilizando la notación simbólica, eliminar todos los permisos de escritura (propietario, grupo, otros) del directorio dir2.
    chmod a-w PRUEBA/dir2

24. Utilizando la notación octal, eliminar el permiso de lectura del directorio dir2, al resto de los usuarios.
    chmod o-r PRUEBA/dir2

25. Crear el fichero uno. Quitarle todos los permisos de lectura. Comprobarlo. Intentar borrar dicho fichero.
    touch PRUEBA/uno && chmod a-r PRUEBA/uno && ls -l PRUEBA/uno && rm PRUEBA/uno

26. Quitarle todos los permisos de paso al directorio dir2 y otorgarle todos los demás.
    chmod a=rw PRUEBA/dir2

27. Crea un archivo de tamaño 0.
    : > PRUEBA/archivo_vacio

28. Mostrar cuantos usuarios tiene registrados el sistema (el registro de usuarios está en el archivo /etc/passwd).
    cut -d: -f1 /etc/passwd | wc -l
