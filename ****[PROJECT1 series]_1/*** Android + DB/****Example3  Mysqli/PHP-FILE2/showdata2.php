<?php
        header("content-type:text/javascript;charset=utf-8");  
        
        include 'connection.php';    
        $connect = mysqli_connect(hostname, user, password, databaseName);
        
        global $connect;
        
        $query = "SELECT * FROM member2";
        
        $result = mysqli_query($connect, $query);
        $number_of_rows = mysqli_num_rows($result);
        
        if($number_of_rows > 0) {
            while ($row = mysqli_fetch_assoc($result)) {
                $temp_array[] = $row;
            }
        }
        
        header('Content-Type: application/json');
        print(json_encode($temp_array));  
        mysqli_close($connect);     
?>