<?php
	include("db.php");
$name=$_POST['personName'];
$sname=$_POST['personSurname'];

$sql = "INSERT INTO persons (personName,personSurname) VALUES ('$name','$sname')";
                        
                             mysqli_query($conn, $sql);



?>

