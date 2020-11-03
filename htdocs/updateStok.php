<?php
	include("db.php");
$id=$_POST['foodId'];
$stok=$_POST['stok'];

$sql = "UPDATE foods SET stok='".$stok."' WHERE foodId='".$id."'";
                        
                             mysqli_query($conn, $sql);



?>

