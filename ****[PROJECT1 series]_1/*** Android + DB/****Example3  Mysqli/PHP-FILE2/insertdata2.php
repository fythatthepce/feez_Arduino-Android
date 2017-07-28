<?php
        if($_SERVER["REQUEST_METHOD"]=="POST"){
            require 'connection.php';
            inDATA();
        }


        function inDATA()
        {
            global $connect;
            
            $username = $_POST["username"];	
            $password = $_POST["password"];	
            $email = $_POST["email"];	
    
            $query = " Insert into member2(Id,username,password,email,loginstatus) values ('','$username','$password','$email','0');";
            
            mysqli_query($connect, $query) or die (mysqli_error($connect));
            mysqli_close($connect);
            
        }
?>