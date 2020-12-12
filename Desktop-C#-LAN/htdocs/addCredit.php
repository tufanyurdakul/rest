<?php
	include("db.php");
$personId=$_POST['personId'];
$foodId=$_POST['foodId'];
$amount=$_POST['amount'];
$sql = "INSERT INTO credits (personId,foodId,amount) VALUES ('$personId','$foodId','$amount')";
                        
                             mysqli_query($conn, $sql);



?>

