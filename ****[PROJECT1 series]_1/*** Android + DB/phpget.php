<?php  
header("content-type:text/javascript;charset=utf-8");   
$con=mysql_connect('localhost','root','Thatthep11711')or die(mysql_error());    
mysql_select_db('feedroid')or die(mysql_error());  
mysql_query("SET NAMES UTF8");  

$sql="SELECT * FROM member";  
$res=mysql_query($sql);  
while($row=mysql_fetch_assoc($res)){  
       $output[]=$row;  
}  
print(json_encode($output));  
mysql_close();  
?>  
