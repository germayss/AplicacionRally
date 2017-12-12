<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";
$json=array();

if (isset($_GET["IDE"]) && isset($_GET["IDPC"])) {

	$Equipo=$_GET['IDE'];
	$Punto=$_GET['IDPC'];
	//conexion
	$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	
	//Equipos
	$consulta="SELECT HoraSalidaEquipoControl FROM `Control` WHERE IDEquipo = {$Equipo} AND IDPuntoControl = {$Punto}";
	$resultado=mysqli_query($conexion,$consulta);
	while($registro=mysqli_fetch_array($resultado)){
		$json['hora'][]=$registro;
	}
	
	mysqli_close($conexion);
	
	if(empty($json)){
		$resulta["HoraSalidaEquipoControl"]='0';
		$json['hora'][]=$resulta;
		echo json_encode($json);
	}else{	
		echo json_encode($json);
	}		
}
// no hay conexion no llegarond atos
else{
	$resulta["HoraSalidaEquipoControl"]='0';
	$json['hira'][]=$resulta;
	echo json_encode($json);
}

?>