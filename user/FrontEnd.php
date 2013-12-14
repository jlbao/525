 <!DOCTYPE HTML>
<html>
<head>
<style>
.error {color: #FF0000;}
</style>
</head>
<body>

<?php
// define variables and set to empty values
$usernameErr = $passwordErr = "";
$username = $password = $tags = "";

if ($_SERVER["REQUEST_METHOD"] == "POST")
{

   if (empty($_POST["username"]))
     {$usernameErr = "username is required";}
   else
     {$username = test_input($_POST["username"]);}
  
   if (empty($_POST["password"]))
     {$passwordErr = "password is required";}
   else
     {$password = test_input($_POST["password"]);}

   if (empty($_POST["tags"]))
     {$tags = "";}
   else
     {$tags = test_input($_POST["tags"]);}
 
}

function test_input($data)
{
     $data = trim($data);
     $data = stripslashes($data);
     $data = htmlspecialchars($data);
     return $data;
}
?>

<h2>Create your account</h2>
<p><span class="error">* required field.</span></p>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
   username: <input type="text" name="username">
   <span class="error">* <?php echo $usernameErr;?></span>
   <br><br>
   Password: <input type="text" name="password">
   <span class="error">* <?php echo $passwordErr;?></span>
   <br><br>
   Tags: <textarea name="tags" rows="5" cols="40"></textarea>
   <br><br>
   <input type="submit" name="submit" value="Submit">
</form>

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST"){
$con=mysqli_connect("localhost:3306","root","","tag");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$sql="INSERT INTO user (username, password, tags)
VALUES
('$_POST[username]','$_POST[password]','$_POST[tags]')";

if (!mysqli_query($con,$sql))
  {
  die('Error: ' . mysqli_error($con));
  }
echo "Recommend Companies:";

//mysqli_close($con);
//$page= "http://www.google.com";
//header("Location: $page");
}


$con=mysqli_connect("localhost:3306","root","","tag");

$result = mysqli_query($con,"SELECT * FROM company WHERE TopTags LIKE '%$_POST[tags]%'");

//$result = mysqli_query($con,"SELECT * FROM Customers WHERE TopTags  ''"); 

while($row = mysqli_fetch_array($result))
  {
  echo $row['CompanyName'] . " " ;
  echo "<br>";
  }

mysqli_close($con);
?> 

</body>
</html>

