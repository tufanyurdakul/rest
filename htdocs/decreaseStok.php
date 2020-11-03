<?php
	include("db.php");
    $fid=$_POST['foodId'];



$sql = mysqli_query($conn,"SELECT stok FROM foods WHERE foodId='".$fid."'");
    
    $satir=mysqli_num_rows($sql);

//$say=1;
//echo '{"MenuModel": ';
//echo "[";
//echo "";

$data=array();
while($veri = @mysqli_fetch_assoc($sql)){

setlocale(LC_ALL, 'turkish');
 
 $data[]=$veri;
// echo "{";
 
 //echo "}";
//$id = str_replace(array("\n", "\r"), '', $veri['foodId']);
//$name = $veri['foodName'];
//$price = $veri['foodPrice'];



//if($say<$satir){
//$virgul=",";
//}else{
//$virgul="";
//}
//echo  '{';
  //      echo '"foodId": "'.$id.'",';
    //   echo '"foodName": "'.$name.'",';
       
      // echo '"foodPrice": "'.$price.'"';

//echo  '}'.$virgul;
//$say=$say+1;
}
echo json_encode($data);
//echo "]}";
?>


