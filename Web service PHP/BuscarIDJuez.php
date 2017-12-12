<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos


 if (isset($_GET["IDJuez"])) {

$IDJuez=$_GET['IDJuez'];



//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
	
			$consulta="SELECT * FROM Juez WHERE IDJuez='{$IDJuez}'";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['juez'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);//devuelve la informacion en json

		}

		//sino registra
		else{
			$resulta["IDJuez"]=0;
			$resulta["IDRally"]='0';
		    $resulta["IDPuntoControl"]='1';
			$resulta["UsuarioJuez"]='No Registra';
			$resulta["NombreJuez"]='No Registra';
			$resulta["ContrasenaJuez"]='No Registra';
			$resulta["Tipo"]='No Registra';
		

			$json['j'][]=$resulta;
			echo json_encode($json);
		}
		
	
		?>

