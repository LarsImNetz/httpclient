<?php

include "startendwith.php";

print_r($_FILES);

if (isset($_FILES['privateuserfile']) && is_uploaded_file($_FILES['privateuserfile']['tmp_name'])) {
   if (endswith($_FILES['privateuserfile']['name'], '.php')) die ("keine PHP Files!");
   if (endswith($_FILES['privateuserfile']['name'], '.phps')) die ("keine PHP Files!");
   if (endswith($_FILES['privateuserfile']['name'], '.php5')) die ("keine PHP Files!");
   if (endswith($_FILES['privateuserfile']['name'], '.php7')) die ("keine PHP Files!");
   if (endswith($_FILES['privateuserfile']['name'], '.phtml')) die ("keine PHP Files!");
   if (endswith($_FILES['privateuserfile']['name'], '.htm')) die ("keine HTML Files!");
   if (endswith($_FILES['privateuserfile']['name'], '.html')) die ("keine HTML Files!");
   
   echo "File ". $_FILES['privateuserfile']['name'] ." uploaded successfully.\n";
  move_uploaded_file ($_FILES['privateuserfile'] ['tmp_name'], $_FILES['privateuserfile'] ['name']);
}
else {
  echo "Possible file upload attack: ";
  if (isset($_FILES['privateuserfile'])) {
     echo "filename '". $_FILES['privateuserfile']['tmp_name'] . "'.";
  }
  print_r($_FILES);
}
?>
