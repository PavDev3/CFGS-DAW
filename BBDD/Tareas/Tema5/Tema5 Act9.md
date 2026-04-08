En la base de datos empresa, crea:

El procedimiento ac09semanaIf que reciba como entrada un entero que represente un día de la semana y que devuelva una cadena con el nombre del día de la semana correspondiente (utilizando IF). Por ejemplo, para la entrada 1 debería devolver Lunes.

El procedimiento ac09semanaCase que reciba como entrada un entero que represente un día de la semana y que devuelva una cadena con el nombre del día de la semana correspondiente (utilizando CASE)

El procedimiento ac09semanaCasIng que reciba como entrada un entero que represente un día de la semana y una cadena con el idioma (los posibles valores son CAS o ING) y que devuelva una cadena con el nombre del día de la semana correspondiente en el idioma indicado (puedes utilizar las sentencias condicionales que consideres). Por ejemplo, para 1 y CAS, devolverá Lunes, pero si es ING devolverá Monday.
Debes pensar y argumentar qué sucede si cualquiera de los parámetros recibidos como entrada no contienen alguno de los valores esperados.

A continuación, sobre la tabla habilidad, crea:

El procedimiento ac09insertaHabilidad que reciba como entrada un código de habilidad y su descripción, y que sólo la inserte si el código de la habilidad tiene un tamaño de 5 caracteres.
El procedimiento ac09upsertHabilidad que reciba como entrada un código de habilidad y su descripción, y que sólo la inserte si el código de la habilidad tiene un tamaño de 5 caracteres. Si el código ya existe, debe modificar la habilidad con la nueva descripción, y si no, la insertará.
El procedimiento ac09upsertHabilidadPlus que además de todo lo anterior, informe al usuario de la operación realizada. En el caso de que los datos de entrada sean incorrectos o incompletos, deberá también informar de ello.
Además, no olvides adjuntar, para cada apartado, tanto las sentencias necesarias para su creación como para su prueba y una captura de pantalla con su ejecución.

06bd-empresa.sql 06bd-empresa.sql10 de marzo de 2026, 21:11
