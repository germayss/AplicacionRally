<?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";
$json=array();

if (isset($_GET["IDPC"])) {

	$Punto=$_GET['IDPC'];
	//conexion
	$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
	
	//Equipos
	$consulta="SELECT * FROM `PuntoControl` WHERE IDPuntoControl = {$Punto}";
	$resultado=mysqli_query($conexion,$consulta);
	while($registro=mysqli_fetch_array($resultado)){
		$json['Pcontrol'][]=$registro;
	}
	
	mysqli_close($conexion);
	
	if(empty($json)){
		$resulta["IDPuntoControl"]='0';
		$resulta["IDRuta"]='0';
		$resulta["IDRally"]='0';
		$resulta["AreaPuntoControl"]='0';
		$resulta["SecuenciaPuntoControl"]='0';
		$resulta["MultiplePuntoControl"]='0';
		$json['Pcontrol'][]=$resulta;
		echo json_encode($json);
	}else{	
		echo json_encode($json);
	}		
}
// no hay conexion no llegarond atos
else{
	$resulta["IDPuntoControl"]='0';
	$resulta["IDRuta"]='0';
	$resulta["IDRally"]='0';
	$resulta["AreaPuntoControl"]='0';
	$resulta["SecuenciaPuntoControl"]='0';
	$resulta["MultiplePuntoControl"]='0';
	$json['Pcontrol'][]=$resulta;
	echo json_encode($json);
}

?>