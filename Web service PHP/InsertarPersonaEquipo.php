<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos


 if ( isset($_GET["IDRally"]) && isset($_GET["IDEquipo"])&& isset($_GET["NombrePersonaEquipo"]) && isset($_GET["LiderPersonaEquipo"])) {


$IDRally=$_GET['IDRally'];
$IDEquipo=$_GET['IDEquipo'];
$NombrePersonaEquipo=$_GET['NombrePersonaEquipo'];
$LiderPersonaEquipo=$_GET['LiderPersonaEquipo'];




//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        
        //consulta insert
        $insert="INSERT INTO PersonaEquipo(IDPersonaEquipo,IDEquipo,IDRally,NombrePersonaEquipo,LiderPersonaEquipo) VALUES 
        (NULL,'{$IDEquipo}','{$IDRally}','{$NombrePersonaEquipo}','{$LiderPersonaEquipo}')";

        $resultado_insert=mysqli_query($conexion,$insert);

              if($resultado_insert){
            $consulta="SELECT * FROM PersonaEquipo";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['equipo'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);//devuelve la informacion en json

        

        }else{



             $resulta["IDRuta"]=0;
            $resulta["IDRally"]='0';
            $resulta["NombrePersonaEquipo"]='No conecta';
            $resulta["LiderPersonaEquipo"]='No conecta';
 
            $json['PersonaEquipo'][]=$resulta;
            echo json_encode($json);
        }
        


        }else{



             $resulta["IDRuta"]=0;
            $resulta["IDRally"]='0';
            $resulta["NombrePersonaEquipo"]='No conecta2';
            $resulta["LiderPersonaEquipo"]='No conecta2';
 
            $json['PersonaEquipo'][]=$resulta;
            echo json_encode($json);
        }
        
    

    ?>


