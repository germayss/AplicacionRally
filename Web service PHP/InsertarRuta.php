<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos


 if ( isset($_GET["IDRally"]) && isset($_GET["NombreRuta"])
    && isset($_GET["FechaInicioRuta"]) && isset($_GET["HoraInicioRuta"])) {


$IDRally=$_GET['IDRally'];
$HoraInicioRuta=$_GET['HoraInicioRuta'];
$NombreRuta=$_GET['NombreRuta'];
$FechaInicioRuta=$_GET['FechaInicioRuta'];



//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        
        //consulta insert
        $insert="INSERT INTO Ruta(IDRuta,IDRally,NombreRuta,FechaInicioRuta,HoraInicioRuta,EstadoRuta) VALUES (NULL,'{$IDRally}',
        '{$NombreRuta}','{$FechaInicioRuta}','{$HoraInicioRuta}',1)";

        $resultado_insert=mysqli_query($conexion,$insert);
        
        if($resultado_insert){
            $consulta="SELECT * FROM Ruta WHERE  NombreRuta='{$NombreRuta}' and IDRally='{$IDRally}'";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['ruta'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);//devuelve la informacion en json

        }

        //sino registra
        else{
            $resulta["IDRuta"]=0;
            $resulta["IDRally"]='0';
            $resulta["HoraInicioRuta"]='1';
            $resulta["NombreRuta"]='No Registra';
            $resulta["FechaInicioRuta"]='No Registra';
            $resulta["EstadoRuta"]='No Registra';
           
        

            $json['r'][]=$resulta;
            echo json_encode($json);
        }
        
    }

    
    // no hay conexion no llegarond atos
    else{
            //$resulta["IDPersona"]=4;
          $resulta["IDRuta"]=0;
            $resulta["IDRally"]='0';
            $resulta["HoraInicioRuta"]='No conecta';
            $resulta["NombreRuta"]='No conecta';
            $resulta["FechaInicioRuta"]='No conecta';
            $resulta["EstadoRuta"]='No conecta';
            $json['r'][]=$resulta;
            echo json_encode($json);
        }





?>