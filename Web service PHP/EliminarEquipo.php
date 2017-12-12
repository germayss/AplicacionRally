
        
    

    <?php

$hostname_localhost="localhost";
$database_localhost="id3754333_aplicacionrallydb";
$username_localhost="id3754333_developersgss";
$password_localhost="developergss2017";

$json=array();//objeto almacena info del ws

//validaciones si llegan datos

 if (isset($_GET["IDEquipo"])) {

$IDEquipo=$_GET['IDEquipo'];



//conexion
$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
        
       
            $consulta="DELETE FROM Equipo WHERE IDEquipo='{$IDEquipo}'";

        $resultado_insert=mysqli_query($conexion,$consulta);
        
        if($resultado_insert){
            $consulta="SELECT * FROM Equipo";
            $resultado=mysqli_query($conexion,$consulta);
            
            if($registro=mysqli_fetch_array($resultado)){
                $json['equipo'][]=$registro;
            }
            mysqli_close($conexion);
            echo json_encode($json);//devuelve la informacion en json

        }
        //sino registra
        else{
          $resulta["IDEquipo"]='false';
            
                $json['j'][]=$resulta;
            echo json_encode($json);
        }
        
    }
    // no hay conexion no llegarond atos
    else{
            //$resulta["IDPersona"]=4;
       $resulta["IDEquipo"]=0;
            $resulta["UsuarioEquipo"]='ws no retorna1';
            $resulta["NombreEquipo"]='ws no retorna1';
            $resulta["PasswordEquipo"]='ws no retorna1';
            $json['j'][]=$resulta;
            echo json_encode($json);
        }


?>