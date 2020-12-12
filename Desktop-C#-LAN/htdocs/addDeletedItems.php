<?php
	include("db.php");
$id=$_POST['foodId'];
$name=$_POST['masaNumarasi'];
$amount=$_POST['amount'];
$date=$_POST['date'];
$sql = "INSERT INTO deleteditems (foodId,masaNumarasi,amount,datetime) VALUES ('$id','$name','$amount','$date')";
                        
                             mysqli_query($conn, $sql);



?>

