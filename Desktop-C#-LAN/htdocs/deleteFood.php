<?php
	include("db.php");
$name=$_POST['id'];


$sql = "DELETE FROM choosenfoods WHERE id='".$name."'";
                        
                             mysqli_query($conn, $sql);



?>

