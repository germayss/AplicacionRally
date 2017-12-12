<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos

 if (isset($_GET["IDJuez"]) && isset($_GET["UsuarioJuez"])
	&& isset($_GET["NombreJuez"]) && isset($_GET["ContrasenaJuez"])) {

$IDJuez=$_GET['IDJuez'];
$UsuarioJuez=$_GET['UsuarioJuez'];
$NombreJuez=$_GET['NombreJuez'];
$ContrasenaJuez=$_GET['ContrasenaJuez'];



//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		//consulta insert
		$consulta="UPDATE Juez SET 	UsuarioJuez='{$UsuarioJuez}',NombreJuez='{$NombreJuez}',
		ContrasenaJuez='{$ContrasenaJuez}'
		WHERE IDJuez='{$IDJuez}'";

		$resultado_insert=mysqli_query($conexion,$consulta);
		
		if($resultado_insert){
			$consulta="SELECT * FROM Juez WHERE  IDJuez='{$IDJuez}'";
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
			$resulta["UsuarioJuez"]='No Registra';
			$resulta["NombreJuez"]='No Registra';
			$resulta["ContrasenaJuez"]='No Registra';
		
		

			$json['j'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	// no hay conexion no llegarond atos
	else{
			//$resulta["IDPersona"]=4;
			$resulta["IDJuez"]=0;
			$resulta["UsuarioJuez"]='ws no retorna';
			$resulta["NombreJuez"]='ws no retorna';
			$resulta["ContrasenaJuez"]='ws no retorna';
	
			$json['j'][]=$resulta;
			echo json_encode($json);
		}


?>