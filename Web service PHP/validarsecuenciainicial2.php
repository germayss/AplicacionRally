<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";
$json=array();

if (isset($_GET["ID"])) {

	$Ruta=$_GET['ID'];
	//conexion
	$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	
	//Equipos
	$consulta="SELECT IDPuntoControl FROM `PuntoControl` WHERE SecuenciaPuntoControl = 1 AND IDRuta = '{$Ruta}'";
	$resultado=mysqli_query($conexion,$consulta);
	while($registro=mysqli_fetch_array($resultado)){
		$json['ruta'][]=$registro;
	}
	
	mysqli_close($conexion);
	
	if(empty($json)){
		$resulta["IDPuntoControl"]='0';
		$json['ruta'][]=$resulta;
		echo json_encode($json);
	}else{	
		echo json_encode($json);
	}		
}
// no hay conexion no llegarond atos
else{
	$resulta["IDPuntoControl"]='0';
	$json['ruta'][]=$resulta;
	echo json_encode($json);
}

?>