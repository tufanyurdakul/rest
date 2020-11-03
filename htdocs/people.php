<?php
	include("db.php");




$sql = mysqli_query($conn,"SELECT * FROM persons");
    
    $satir=mysqli_num_rows($sql);



$data=array();
while($veri = @mysqli_fetch_assoc($sql)){

setlocale(LC_ALL, 'turkish');
 
 $data[]=$veri;

}
echo json_encode($data);

?>


