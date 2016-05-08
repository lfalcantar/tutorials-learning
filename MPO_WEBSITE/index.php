<?php
echo "<table border = '2' style='color:#ffffff;'>";
$i = 0;
foreach($_SERVER as $key=>$value){
if($i%2 == 0) {
echo "<tr bgcolor='#993300'>";
} else {
echo "<tr bgcolor='#0099FF'>";
}
echo "<td>";
echo $key;
echo "</td>";
echo "<td>";
echo $value;
echo "</td>";
echo "</tr>";
$i++;
}
echo "</table>";
?>
