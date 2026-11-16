<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva vivienda</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        label { display: block; margin-top: 12px; }
        input, select, textarea { margin-left: 8px; padding: 4px; width: 220px; }
        textarea { vertical-align: top; }
        fieldset { margin-top: 15px; }
        button { margin-top: 18px; padding: 8px 16px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Formulario de vivienda</h1>

    <form method="post" action="insertarVivienda.php">
        <label for="tipo">Tipo de vivienda:
            <select name="tipo" id="tipo">
                <option value="Adosado">Adosado</option>
                <option value="Unifamiliar">Unifamiliar</option>
                <option value="Piso">Piso</option>
            </select>
        </label>

        <label for="zona">Zona:
            <select name="zona" id="zona">
                <option value="Centro">Centro</option>
                <option value="Norte">Norte</option>
                <option value="Sur">Sur</option>
                <option value="Este">Este</option>
                <option value="Oeste">Oeste</option>
            </select>
        </label>

        <label for="direccion">Dirección:
            <input type="text" name="direccion" id="direccion">
        </label>

        <label for="habitaciones">Habitaciones:
            <input type="number" name="habitaciones" id="habitaciones" min="0">
        </label>

        <label for="precio">Precio (€):
            <input type="number" name="precio" id="precio" step="0.01" min="0">
        </label>

        <fieldset>
            <legend>Extras</legend>
            <label><input type="checkbox" name="extras[]" value="Garaje"> Garaje</label>
            <label><input type="checkbox" name="extras[]" value="Trastero"> Trastero</label>
            <label><input type="checkbox" name="extras[]" value="Piscina"> Piscina</label>
        </fieldset>

        <label for="observaciones">Observaciones:
            <textarea name="observaciones" id="observaciones" rows="3"></textarea>
        </label>

        <button type="submit">Guardar vivienda</button>
    </form>
</body>
</html>
