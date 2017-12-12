<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos

 if (isset($_GET["IDEquipo"]) && isset($_GET["UsuarioEquipo"])
    && isset($_GET["NombreEquipo"]) && isset($_GET["PasswordEquipo"])) {

$IDEquipo=$_GET['IDEquipo'];
$UsuarioEquipo=$_GET['UsuarioEquipo'];
$NombreEquipo=$_GET['NombreEquipo'];
$PasswordEquipo=$_GET['PasswordEquipo'];



//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        
        //consulta insert
        $consulta="UPDATE Equipo SET  UsuarioEquipo='{$UsuarioEquipo}',NombreEquipo='{$NombreEquipo}',
       PasswordEquipo='{$PasswordEquipo}'
        WHERE IDEquipo='{$IDEquipo}'";

        $resultado_insert=mysqli_query($conexion,$consulta);
        
        if($resultado_insert){
            $consulta="SELECT * FROM Equipo WHERE  IDEquipo='{$IDEquipo}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['juez'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);//devuelve la informacion en json

        }

        //sino registra
        else{
            $resulta["IDEquipo"]=0;
            $resulta["UsuarioEquipo"]='No Registra';
            $resulta["NombreEquipo"]='No Registra';
            $resulta["PasswordEquipo"]='No Registra';
        
        

            $json['j'][]=$resulta;
            echo json_encode($json);
        }
        
    }
    // no hay conexion no llegarond atos
    else{
            //$resulta["IDPersona"]=4;
            $resulta["IDEquipo"]=0;
            $resulta["UsuarioEquipo"]='ws no retorna';
            $resulta["NombreEquipo"]='ws no retorna';
            $resulta["PasswordEquipo"]='ws no retorna';
    
            $json['j'][]=$resulta;
            echo json_encode($json);
        }


?>