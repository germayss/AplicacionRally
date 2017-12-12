<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";
$json=array();

if (isset($_GET["ID"])) {

	$Equipo=$_GET['ID'];
	//conexion
	$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	
	//Equipos
	$consulta="SELECT IDPuntoControl FROM `Equipo` WHERE IDEquipo = {$Equipo}";
	$resultado=mysqli_query($conexion,$consulta);
	while($registro=mysqli_fetch_array($resultado)){
		$json['pcontrol'][]=$registro;
	}
	
	mysqli_close($conexion);
	
	if(empty($json)){
		$resulta["IDPuntoControl"]='1';
		$json['pcontrol'][]=$resulta;
		echo json_encode($json);
	}else{
		echo json_encode($json);
	}		
}
// no hay conexion no llegarond atos
else{
	$resulta["IDPuntoControl"]='1';
	$json['pcontrol'][]=$resulta;
	echo json_encode($json);
}

?>