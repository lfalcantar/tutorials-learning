 # js json  :
 #        {[ "value1":"valu1", "valu2":"value2", "value3":"value4","zone":"EJERCITO NACIONAL","name":"name"]},
 #    php json:
 #        $json['name'] = array("value1" => "value1", "value2" => "value2", "zone" => "EJERCITO NACIONAL", "name" => "name");

 #$json['PRADERA'] = array("venta" => "V 19:00", "compra" => "C 18:00", "nombre" => "PRADERA", "latidute" => 31.703550, "longitude" => -106.419370, "zona" => "EJERCITO NACIONAL");



  #{[ "venta":"V 19:00", "compra":"C 18:00", "nombre":"VALENTIN DERECHA",        "latidute":31.709326, "longitude":-106.433270, "zona":"EJERCITO NACIONAL"]},
import os

nameIndex = 2

def construc_json(line):
    #Split the line by the name and value#
    coma_split = line.split(",")
    #Get the name of the line,modified the nameIndex if different#
    php_json = "$json[" + coma_split[nameIndex].split(":")[1].replace("\"", '\'') + "] = array("
    for temp_line in coma_split:
        colon_split = temp_line.split(":")
        php_json += colon_split[0] + " => " + colon_split[1]
        try:
            php_json += ":" + colon_split[2]
        except IndexError:
            pass  # or you could use 'continue'
        finally:
            php_json += ", "

    return php_json[:-2] + " );"  # remove space and las comma

with open('jsJson') as fp:
    for line in fp:
        print construc_json(line)



