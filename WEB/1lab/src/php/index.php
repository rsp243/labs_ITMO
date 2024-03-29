<?php
function echoResponse(int $http_response_code, string $echoJson)
{
    header('Content-Type: application/json');
    http_response_code($http_response_code);
    echo ($echoJson);
    die;
}

$start_time = microtime(true);
$x = "";
$y = "";
$r = "";
$jsonInput = file_get_contents('php://input');
$data = json_decode($jsonInput, true);
if (!empty($data)) {
    if (isset($data['X']) && isset($data['Y']) && isset($data['R'])) {
        $x = $data['X'];
        $y = $data['Y'];
        $r = $data['R'];
        ini_set('precision', strlen($y));
        if (is_numeric($x) && is_numeric($y) && is_numeric($r)) {
            $x = floatval($x);
            $y = floatval($y);
            $r = floatval($r);
            if (in_array($x, [-3, -2, -1, 0, 1, 2, 3, 4, 5]) && $y < 5.0 && $y > -3.0 && in_array($r, [1, 1.5, 2, 2.5, 3])) {
                $sector_1 = $x >= 0 && $y >= 0 && $x + $y <= $r / 2;
                $sector_2 = false;
                $sector_3 = $x < 0 && $y < 0 && abs($x) + abs($y) <= $r;
                $sector_4 = $x > 0 && $y < 0 && $x <= $r && $y <= -$r;
    
                $isHit = $sector_1 || $sector_2 || $sector_3 || $sector_4;
                $hitValue = "MISS";
                if ($isHit) {
                    $hitValue = "HIT";
                }
                $currentDateTime = new DateTime('now'); 
                $currentDateTimeFormatted = $currentDateTime -> format('d-m-Y H:i:s');
                $executionTime = (microtime(true) - $start_time);
                $colorRGB = "rgb(" . rand(0, 255) . ", " . rand(0, 255) . ", " . rand(0, 255) . ")";
                $result_array = array(
                    "xValue" => "<td scope=\"row\">" . $x . "</td>",
                    "yValue" => "<td scope=\"row\">" . $y . "</td>",
                    "rValue" => "<td scope=\"row\">" . $r . "</td>",
                    "isHit" => "<td scope=\"row\">" . $hitValue . "</td>",
                    "currentTime" => "<td scope=\"row\">" . $currentDateTimeFormatted . "</td>",
                    "executionTime" => "<td scope=\"row\">" . number_format((float) $executionTime, 6, '.', '') . "</td>",
                    "color(RGB)" => "<td scope=\"row\">" . $colorRGB . "</td>"
                );
                echoResponse(200, json_encode($result_array));
            } else {
                echoResponse(400, json_encode("Parameters are not in their ranges"));
            }   
        }
        echoResponse(400, json_encode("Parameters are not numeric"));
    }
    echoResponse(400, json_encode("Not enough parameters (x, y, r is mandatory)"));
}
echoResponse(400, json_encode("The incoming request was empty"));
?>