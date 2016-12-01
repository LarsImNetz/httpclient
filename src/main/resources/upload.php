<?php

include "startendwith.php";

print_r($_FILES);

if (isset($_FILES['userfile']) && is_uploaded_file($_FILES['userfile']['tmp_name'])) {
   if (endswith($_FILES['userfile']['name'], '.php')) die ("keine PHP Files!");
   if (endswith($_FILES['userfile']['name'], '.phps')) die ("keine PHP Files!");
   if (endswith($_FILES['userfile']['name'], '.php5')) die ("keine PHP Files!");
   if (endswith($_FILES['userfile']['name'], '.php7')) die ("keine PHP Files!");
   if (endswith($_FILES['userfile']['name'], '.phtml')) die ("keine PHP Files!");
   if (endswith($_FILES['userfile']['name'], '.htm')) die ("keine HTML Files!");
   if (endswith($_FILES['userfile']['name'], '.html')) die ("keine HTML Files!");
   
   echo "File ". $_FILES['userfile']['name'] ." uploaded successfully.\n";
  move_uploaded_file ($_FILES['userfile'] ['tmp_name'], $_FILES['userfile'] ['name']);
}
else {
  echo "Possible file upload attack: ";
  if (isset($_FILES['userfile'])) {
     echo "filename '". $_FILES['userfile']['tmp_name'] . "'.";
  }
  print_r($_FILES);
}
?>
