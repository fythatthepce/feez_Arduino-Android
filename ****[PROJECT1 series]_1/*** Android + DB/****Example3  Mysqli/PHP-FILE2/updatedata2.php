<?php
        if($_SERVER["REQUEST_METHOD"]=="POST"){
            require 'connection.php';
            inDATA();
        }
        
       
        function inDATA()
        {
            global $connect;
            
        	
            $username = $_POST["username"];	
            
            //$query_clear = "UPDATE member2 SET loginstatus ='0'";
            $query = "UPDATE member2 SET loginstatus ='1' WHERE username='$username' ";

            //mysqli_query($connect, $query_clear) or die (mysqli_error($connect));
            mysqli_query($connect, $query) or die (mysqli_error($connect));
            
            mysqli_close($connect);
        }
            
        
?>