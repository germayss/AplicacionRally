<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos


 if ( isset($_GET["IDRally"]) && isset($_GET["NombreEquipo"])
    && isset($_GET["UsuarioEquipo"]) && isset($_GET["PasswordEquipo"])) {


$IDRally=$_GET['IDRally'];
$NombreEquipo=$_GET['NombreEquipo'];
$UsuarioEquipo=$_GET['UsuarioEquipo'];
$PasswordEquipo=$_GET['PasswordEquipo'];



//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        
        //consulta insert
        $insert="INSERT INTO Equipo(IDEquipo,IDRally,IDPuntoControl,ImagenEquipo,PuntajeEquipo,NombreEquipo,UsuarioEquipo,PasswordEquipo,Tipo) VALUES (NULL,'{$IDRally}',1,NULL,0,'{$NombreEquipo}','{$UsuarioEquipo}','{$PasswordEquipo}',3)";

    $resultado_insert=mysqli_query($conexion,$insert);
        
        if($resultado_insert){
            $consulta="SELECT * FROM Equipo WHERE  IDRally='{$IDRally}' and NombreEquipo='{$NombreEquipo}' and UsuarioEquipo='{$UsuarioEquipo}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['equipo'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);//devuelve la informacion en json

        }
else{
       
          $resulta["NombreEquipo"]=0;
            $resulta["IDRally"]='0';
            $resulta["PasswordEquipo"]='No conecta2';
            $resulta["UsuarioEquipo"]='No conecta2';
   
            $json['equipo'][]=$resulta;
            echo json_encode($json);
        }

        } else{
       
          $resulta["NombreEquipo"]=0;
            $resulta["IDRally"]='0';
            $resulta["PasswordEquipo"]='No conecta';
            $resulta["UsuarioEquipo"]='No conecta';
   
            $json['equipo'][]=$resulta;
            echo json_encode($json);
        }
    

    ?>






