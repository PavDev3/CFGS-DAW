<?php

    //Se une a la sesión
    session_start();
    //vaciamos el array sesión
    $_SESSION = array();
    //Eliminamos sesión
    session_destroy();
        
    //Eliminamos las cookies
    setcookie(session_name(),123,time()-1000,"/");
    header("Location: sesiones_login.php");

