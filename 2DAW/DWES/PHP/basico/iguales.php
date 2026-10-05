<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php

    $a1 = [
        1 => 5,
        2 => 5
    ];

    $a2 = [
        // 1 => $a1[1],
        // 2 => $a1[2]
        1 => "5",
        2 => "5"
    ];

if ($a1 === $a2) {
        echo "Son iguales";
    } else {
        echo "No son iguales";
    }
    echo "<br>";
    echo "a1: {$a1[1]}, {$a1[2]}";
    echo "<br>";
    echo "a2: {$a2[1]}, {$a2[2]}";
        
    ?>
</body>
</html>