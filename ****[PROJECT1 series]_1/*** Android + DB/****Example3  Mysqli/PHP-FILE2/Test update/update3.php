<?php
            include 'connection.php';
       
            global $connect;
            
            $username = "user_test";
            $password = "pass_test";
            $email = "email_test";	
            
        
            $query = "UPDATE member2 SET loginstatus ='1' WHERE id=1";
            mysqli_query($connect, $query) or die (mysqli_error($connect));
            mysqli_close($connect);
            
        
?>