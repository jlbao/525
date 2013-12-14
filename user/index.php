<?php
$con=mysqli_connect("localhost:3306","root","","tag");
// Check connection
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

//$result = mysqli_query($con,"SELECT * FROM company WHERE CompanyName LIKE 'dell%';");
$result = mysqli_query($con, "SELECT * FROM Customers
WHERE City IN Paris','London'");

while($row = mysqli_fetch_array($result))
  {
  echo $row['CompanyName'] . " " . $row['TopTags'];
  echo "<br>";
  }

mysqli_close($con);
?> 
