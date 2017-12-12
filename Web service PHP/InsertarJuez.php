<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos


 if (isset($_GET["IDJuez"]) && isset($_GET["IDRally"]) && isset($_GET["IDPuntoControl"]) && isset($_GET["UsuarioJuez"])
	&& isset($_GET["NombreJuez"]) && isset($_GET["ContrasenaJuez"]) && isset($_GET["Tipo"])) {

$IDJuez=$_GET['IDJuez'];
$IDRally=$_GET['IDRally'];
$IDPuntoControl=$_GET['IDPuntoControl'];
$UsuarioJuez=$_GET['UsuarioJuez'];
$NombreJuez=$_GET['NombreJuez'];
$ContrasenaJuez=$_GET['ContrasenaJuez'];
$Tipo=$_GET['Tipo'];


//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		//consulta insert
		$insert="INSERT INTO Juez(IDJuez,IDRally,IDPuntoControl,UsuarioJuez,NOmbreJuez,ContrasenaJuez,Tipo) VALUES (NULL,'{$IDRally}',
		'{$IDPuntoControl}','{$UsuarioJuez}','{$NombreJuez}','{$ContrasenaJuez}',2)";

		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM Juez WHERE  NombreJuez= '{$NombreJuez}' and ContrasenaJuez='{$ContrasenaJuez}'";
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
		
	}
	// no hay conexion no llegarond atos
	else{
			//$resulta["IDPersona"]=4;
			$resulta["IDJuez"]=0;
			$resulta["IDRally"]=0;
		    $resulta["IDPuntoControl"]=0;
			$resulta["UsuarioJuez"]='ws no retorna';
			$resulta["NombreJuez"]='ws no retorna';
			$resulta["ContrasenaJuez"]='ws no retorna';
			$resulta["Tipo"]='ws no retorna';
			$json['j'][]=$resulta;
			echo json_encode($json);
		}





?>



