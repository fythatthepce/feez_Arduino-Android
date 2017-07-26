<?php
        if($_SERVER["REQUEST_METHOD"]=="POST"){
            require 'connection.php';
            inDATA();
        }


        function inDATA()
        {
            global $connect;
            
            $username = $_POST["username"];	
            $pass = $_POST["pass"];	


            $query = " Insert into member(Id,username,pass) values ('','$username','$pass');";
            
            mysqli_query($connect, $query) or die (mysqli_error($connect));
            mysqli_close($connect);
            
        }
?>