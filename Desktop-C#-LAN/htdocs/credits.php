<?php
	include("db.php");

    $name=$_POST['personId'];


$sql = mysqli_query($conn,"SELECT creditId,foodName,foodPrice,amount FROM credits INNER JOIN foods ON credits.foodId=foods.foodId and credits.personId='".$name."'");
    
    $satir=mysqli_num_rows($sql);



$data=array();
while($veri = @mysqli_fetch_assoc($sql)){

setlocale(LC_ALL, 'turkish');
 
 $data[]=$veri;




}
echo json_encode($data);

?>


