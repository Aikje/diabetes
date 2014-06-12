<?php
	//include 'inc_open_db_connection.php';
	echo "GOEDVERDOEME";
	$formData = array[];
	$voornaam = $_GET['voornaam'];
	$achternaam = $_GET['achternaam'];
	$tussenvoegsel = $_GET['tussenvoegsel'];
	$geboorteDatum = $_GET['geboorte'];
	$telefoonnummer = $_GET['telefoonnummer'];
	$mobiel = $_GET['mobiel'];
	$mail = $_GET['email'];
	$plaats = $_GET['plaats'];
	$postcode = $_GET['postcode'];
	$straat = $_GET['straat'];
	$huisnr = $_GET['huisnummer'];
	$lengte = $_GET['lengte'];
	$gewicht = $_GET['gewicht'];
	$minSuiker = $_GET['min_bloed'];
	$maxSuiker = $_GET['max_bloed'];
	$metingen = $_GET['metingen'];
	$uid = $_GET['uid'];
	if($voornaam != "")
		{$formData['voornaam'] = "$voornaam"};
	if($achternaam != "")
		{$formData['achternaam'] = "$achternaam"};
	if($tussenvoegsel != "")
		{$formData['tussenvoegsel'] = "$tussenvoegsel"};
	if($geboorteDatum != "")
		{$formData['geboortedatum'] = "$geboorteDatum"};
	if($telefoonnummer != "")
		{$formData['telefoonnummer'] = "$telefoonnummer"};
	if($mobiel != "")
		{$formData['mobiel'] = "$mobiel"};
	if($mail != "")
		{$formData['mail'] = "$mail"};
	if($plaats != "")
		{$formData['plaats'] = "$plaats"};
	if($postcode != "")
		{$formData['postcode'] = "$postcode"};
	if($straat != "")
		{$formData['straat'] = "$straat"};
	if($huisnummer != "")
		{$formData['huisnummer'] = "$huisnummer"};
	if($lengte != "")
		{$formData['lengte'] = "$lengte"};
	if($gewicht != "")
		{$formData['gewicht'] = "$gewicht"};
	if($minSuiker != "")
		{$formData['minSuiker'] = "$minSuiker"};
	if($maxSuiker != "")
		{$formData['maxSuiker'] = "$maxSuiker"};
	if($metingen != "")
		{$formData['metingen'] = "$metingen"};
	if($uid != "")
		{
			var_dump($formData);
			//updateintodb
		};
	//include 'inc_close_db_connection.php';
?>
