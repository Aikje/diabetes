<?php

include "inc_open_db_connection.php";

        if (isset($_POST['submit']))
        {
            if (!$_POST['aanhef'] | !$_POST['voornaam'] | !$_POST['achternaam'] | !$_POST['geboortedatum'] | !$_POST['email']
			| !$_POST['postcode'] | !$_POST['woonplaats'] | !$_POST['straatnaam']
			| !$_POST['huisnummer'] | !$_POST['telefoonnummer'] | !$_POST['geslacht'] | !$_POST['lengte']
			| !$_POST['gewicht'] | !$_POST['diabetestype'] | !$_POST['diabetesreden']
			| !$_POST['bloedtype'] | !$_POST['minimumbloedsuiker'] | !$_POST['maximumbloedsuiker'] | !$_POST['meting'])
            {
                echo "Je hebt niet alle velden ingevuld.";
            }
			else
			{	
				

				
				$sql = "INSERT INTO RecoMA, patient (email, aanhef, voornaam, tussenvoegsels, achternaam, geboortedatum, postcode, woonplaats,
				straatnaam, huisnummer, huisnummertoevoeging, telefoonnummer, mobielnummer, geslacht,
				lengte, gewicht, diabetes_type, diabetes_reden, bloed_type, minimum_bloedsuiker,
				maximum_bloedsuiker, metingen_per_dag)	
				VALUES ('".$_POST["email"]."','".$_POST["aanhef"]."','".$_POST["voornaam"]."','".$_POST["tussenvoegsels"]."',
				'".$_POST["achternaam"]."','".$_POST["geboortedatum"]."', '".$_POST["postcode"]."','".$_POST["woonplaats"]."',
				'".$_POST["straatnaam"]."','".$_POST["huisnummer"]."','".$_POST["huisnummertoevoeging"]."','".$_POST["telefoonnummer"]."',
				'".$_POST["mobielnummer"]."','".$_POST["geslacht"]."','".$_POST["lengte"]."','".$_POST["gewicht"]."','".$_POST["diabetestype"]."',
				'".$_POST["diabetesreden"]."','".$_POST["bloedtype"]."','".$_POST["minimumbloedsuiker"]."','".$_POST["maxiQmumbloedsuiker"]."','".$_POST["meting"]."',)";
				
				echo $sql;
				
				
				//echo "Patient toegevoegd";
			}				
		}
mysql_close($DBConnect);
?>


