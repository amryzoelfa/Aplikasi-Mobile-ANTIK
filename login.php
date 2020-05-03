 <?php
 define('HOST','localhost');
 define('USER','root');
 define('PASS','');
 define('DB','db_antik-281119');

 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
 if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values
 $username = $_POST['username'];
 $password = $_POST['password'];

 //Creating sql query
 $sql = "SELECT * FROM tb_user WHERE username='$username' AND password='$password'";

 //executing query
 $result = mysqli_query($con,$sql);

 //fetching result
 $check = mysqli_fetch_array($result);

 //if we got some result
 if(isset($check)){
 //displaying success
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }
?>