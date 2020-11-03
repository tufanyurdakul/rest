<?php
	include("db.php");

    $name=$_POST['masaNumarasi'];


$sql = mysqli_query($conn,"SELECT id,amount,choosenfoods.foodId,foodName,foodPrice FROM choosenfoods INNER JOIN foods ON choosenfoods.foodId=foods.foodId and masaNumarasi='".$name."'");
    
    $satir=mysqli_num_rows($sql);



$data=array();
while($veri = @mysqli_fetch_assoc($sql)){

setlocale(LC_ALL, 'turkish');
 
 $data[]=$veri;




}
echo json_encode($data);

?>


