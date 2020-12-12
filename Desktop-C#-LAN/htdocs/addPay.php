<?php
	include("db.php");
$id=$_POST['personId'];
$name=$_POST['payValue'];

$sql = "INSERT INTO paying (personId,payValue) VALUES ('$id','$name')";
                        
                             mysqli_query($conn, $sql);



?>

