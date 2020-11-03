<?php
	include("db.php");
$name=$_POST['id'];


$sql = "DELETE FROM choosenFoods WHERE masaNumarasi='".$name."'";
                        
                             mysqli_query($conn, $sql);



?>

