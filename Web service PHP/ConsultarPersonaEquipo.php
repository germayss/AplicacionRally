<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

        
              
        if (isset($_GET["IDEquipo"])) {

$IDEquipo=$_GET['IDEquipo'];     


    $conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

        $consulta="select * from PersonaEquipo where IDEquipo='{$IDEquipo}'";
        $resultado=mysqli_query($conexion,$consulta);
        
        while($registro=mysqli_fetch_array($resultado)){
            $json['equipos'][]=$registro;
    
        }
        mysqli_close($conexion);
        echo json_encode($json);
    }

?>