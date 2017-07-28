<?php
            include 'connection.php';
       
            global $connect;
            
            $username = "user_test";
            $password = "pass_test";
            $email = "email_test";	
            
            
            $query = "Insert into member2(Id,username,password,email,loginstatus) values ('','$username','$password','$email','0');";
            
            mysqli_query($connect, $query) or die (mysqli_error($connect));
            mysqli_close($connect);
            
        
?>