<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";
$json=array();

if (isset($_GET["Usuario"]) && isset($_GET["Pass"])) {

	$Usuario=$_GET['Usuario'];
	$Pass=$_GET['Pass'];
	//conexion
	$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	
	//Equipos
	$consulta="SELECT * FROM `Equipo` WHERE UsuarioEquipo = '{$Usuario}' AND PasswordEquipo = '{$Pass}'";
	$resultado=mysqli_query($conexion,$consulta);
	while($registro=mysqli_fetch_array($resultado)){
		$json['login'][]=$registro;
	}
	
	//Jueces
	$consulta="SELECT * FROM `Juez` WHERE UsuarioJuez = '{$Usuario}' AND ContrasenaJuez = '{$Pass}'";
	$resultado=mysqli_query($conexion,$consulta);
	while($registro=mysqli_fetch_array($resultado)){
		$json['login'][]=$registro;
	}
	
	//Administradores
	$consulta="SELECT * FROM `Administrador` WHERE UsuarioAdministrador = '{$Usuario}' AND PassAdministrador = '{$Pass}'";
	$resultado=mysqli_query($conexion,$consulta);
	while($registro=mysqli_fetch_array($resultado)){
		$json['login'][]=$registro;
	}
	
	mysqli_close($conexion);
	
	if(empty($json)){
		$resulta["Usuario"]='NO';
		$resulta["IDRally"]='NO';
		$json['login'][]=$resulta;
		echo json_encode($json);
	}else{
		echo json_encode($json);
	}		
}
// no hay conexion no llegarond atos
else{
	$resulta["Usuario"]='NO';
	$resulta["IDRally"]='NO';
	$json['login'][]=$resulta;
	echo json_encode($json);
}

?>