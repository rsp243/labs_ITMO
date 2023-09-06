<?php
    $startTime = hrtime(true);

    if (!(isset($_GET['x']) && isset($_GET['y']) && isset($_GET['r']))) {
        http_response_code(422); 
    } else {
        if (!(is_numeric($_GET['x']) && is_numeric($_GET['y']) && is_numeric($_GET['r']))) {
            http_response_code(422);
            return;
        }
    } 
?>