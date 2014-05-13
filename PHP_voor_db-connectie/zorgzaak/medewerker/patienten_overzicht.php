<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Overzicht Patienten</title>
    </head>
    <body>
        <?php
        include 'config.php';
        connect_db();
        $result = mysql_query(" SELECT `uid`, `name`, `email`, `created_at`
				FROM `patient` ");
        $aantal = mysql_num_rows($result);
        ?>
        <table width='40%' border='1'>
            <tr style='font-weight: bold;'>
                <th>ID</th>
                <th>Naam en achternaam</th>
                <th>E-mail</th>
                <th>Datum aangemaakt</th>
            </tr>
            <?php
            while ($row = mysql_fetch_assoc($result))
            {
                ?>
                <tr>
                    <td><?php echo $row['uid'] ?></td>
                    <td><?php echo $row['name'] ?></td>
                    <td><?php echo $row['email'] ?></td>
                    <td><?php echo $row['created_at'] ?></td>
                </tr>
                <?php
            }
            ?>
        </table>
        Er zitten <?php echo $aantal ?> patienten in de database
        <br><br><br><br><br>
        <?php
        mysql_close();
        ?>
    </body>
</html>



