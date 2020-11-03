<?php
	include("db.php");
$name=$_POST['personId'];


$sql = "DELETE FROM credits WHERE personId='".$name."'";
$sqlk = "DELETE FROM paying WHERE personId='".$name."'";       
                             mysqli_query($conn, $sql);
							 mysqli_query($conn, $sqlk);


?>

