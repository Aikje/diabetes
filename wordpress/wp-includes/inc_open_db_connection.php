<?php
    $username = "RecoMA";
    $password = '$_KoM783';
    $database = "RecoMA";
    $server = "localhost";
    
    $DBConnect = mysql_connect($server, $username, $password);
    
    if (!$DBConnect)
    {
        die("<p>Unable to connect to the database server.</p>" . "<p>Error code " . mysql_errno() . ": " . mysql_error() . "</p>");
    }
  
    if (!@mysql_select_db($database, $DBConnect))
    {
        die("<p>Unable to select the database.</p>" . "<p>Error code " . mysql_errno() . ": " . mysql_error() . "</p>");
    }
		
?>
