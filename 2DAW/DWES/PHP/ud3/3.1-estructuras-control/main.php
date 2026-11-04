<?php

$array = [];

for ($i = 0; $i < 10; $i++) {
    $array[] = rand(1, 30);
}

echo "Valores del array: " . implode(', ', $array) . PHP_EOL;

$media = array_sum($array) / count($array);
echo "Valor medio: " . $media . PHP_EOL;

$valoresDescendentes = $array;
rsort($valoresDescendentes);
echo "Tres valores máximos: " . implode(', ', array_slice($valoresDescendentes, 0, 3)) . PHP_EOL;

$valoresAscendentes = $array;
sort($valoresAscendentes);
echo "Tres valores mínimos: " . implode(', ', array_slice($valoresAscendentes, 0, 3)) . PHP_EOL;

$arrayCopiado = $array;
sort($arrayCopiado);
echo "Array copiado y ordenado: " . implode(', ', $arrayCopiado) . PHP_EOL;

array_splice($arrayCopiado, 1, 1);
echo "Array tras eliminar el segundo elemento: " . implode(', ', $arrayCopiado) . PHP_EOL;

?>

