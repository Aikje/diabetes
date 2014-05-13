<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Config voor medewerker</title>
    </head>
    <body>
        <?php

        function connect_db()
        {
            $host = "localhost";
            $username = "root";
            $password = "";
            $db_name = "zorgzaak";

            if (mysql_connect($host, $username, $password))
            {
                mysql_select_db($db_name) or die(mysql_error());
            }
            else
            {
                echo "Er kan geen connectie gemaakt worden met de database";
                exit;
            }
        }
        ?>
    </body>
</html>

