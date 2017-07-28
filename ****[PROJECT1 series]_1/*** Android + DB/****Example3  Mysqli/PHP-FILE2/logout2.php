<?php
        if($_SERVER["REQUEST_METHOD"]=="POST"){
            require 'connection.php';
            inDATA();
        }
        
       
        function inDATA()
        {
            global $connect;
            
        	
            
            $query_clear = "UPDATE member2 SET loginstatus ='0'";
   

            mysqli_query($connect, $query_clear) or die (mysqli_error($connect));
            
            mysqli_close($connect);
        }
            
        
?>