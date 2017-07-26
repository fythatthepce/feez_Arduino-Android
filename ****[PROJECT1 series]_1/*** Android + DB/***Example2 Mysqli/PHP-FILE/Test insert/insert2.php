<?php
            include 'connection.php';
       
            global $connect;
            
            $username = "user_test";
            $pass = "pass_test";
            
            
            $query = " Insert into member(Id,username,pass) values ('','$username','$pass');";
            
            mysqli_query($connect, $query) or die (mysqli_error($connect));
            mysqli_close($connect);
            
        
?>