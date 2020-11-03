<?php
	include("db.php");
$id=$_POST['foodId'];
$name=$_POST['masaNumarasi'];
$amount=$_POST['amount'];
$sql = "INSERT INTO choosenfoods (foodId,masaNumarasi,amount) VALUES ('$id','$name','$amount')";
                        
                             mysqli_query($conn, $sql);



?>

