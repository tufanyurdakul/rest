<?php
	include("db.php");
$name=$_POST['id'];


$sql = "DELETE FROM foods WHERE foodId='".$name."'";
                        
                             mysqli_query($conn, $sql);



?>

