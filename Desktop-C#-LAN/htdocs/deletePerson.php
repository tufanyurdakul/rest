<?php
	include("db.php");
$name=$_POST['personId'];

$sqlm ="DELETE FROM persons WHERE personId='".$name."'";
$sql = "DELETE FROM credits WHERE personId='".$name."'";
$sqlk = "DELETE FROM paying WHERE personId='".$name."'";       
                             mysqli_query($conn, $sql);
							 mysqli_query($conn, $sqlk);
							 mysqli_query($conn, $sqlm);

?>

