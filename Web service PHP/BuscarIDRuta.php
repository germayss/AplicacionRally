<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos


 if (isset($_GET["IDRuta"])) {

$IDRuta=$_GET['IDRuta'];



//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
	
			$consulta="SELECT * FROM Ruta WHERE IDRuta='{$IDRuta}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['ruta'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);//devuelve la informacion en json

		}
	
		?>