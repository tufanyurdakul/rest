<?php
	include("db.php");
$name=$_POST['foodName'];
$price=$_POST['foodPrice'];
$stok=$_POST['stok'];

$sql = "INSERT INTO foods (foodName,foodPrice,stok) VALUES ('$name','$price','$stok')";
                        
                             mysqli_query($conn, $sql);



?>

