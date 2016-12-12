This project will take a json file in javascript format and modified  to php "version of json"
e.i
    js json  :

        {[ "value1":"valu1", "valu2":"value2", "value3":"value4","zone":"EJERCITO NACIONAL","name":"name"]},
    php json:
        $json['name'] = array("value1" => "value1", "value2" => "value2", "zone" => "EJERCITO NACIONAL", "name" => "name");



inpit :
    "venta":"V 19:00", "compra":"C 18:00", "nombre":"PLAZA" , "latidute":31.670633, "longitude":-106.342066, "zona":"WATERFILL"

output:
   $json['CASITA AZUL' ] = array( "venta" => "V 19:00",  "compra" => "C 18:00",  "nombre" => "CASITA AZUL" ,  "latidute" => 31.668785,  "longitude" => -106.346017,  "zona" => "WATERFILL"
 );
$json['CENTRO'] = array( "venta" => "V 19:00",  "compra" => "C 18:00",  "nombre" => "CENTRO",  "latidute" => 31.669947,  "longitude" => -106.343938,  "zona" => "WATERFILL"
 );
$json['PLAZA' ] = array( "venta" => "V 19:00",  "compra" => "C 18:00",  "nombre" => "PLAZA" ,  "latidute" => 31.670633,  "longitude" => -106.342066,  "zona" => "WATERFILL"
 );
