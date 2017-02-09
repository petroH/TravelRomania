<?php
//insertnew.php
/*
 * Following code will create a new product row
 * All product details are read from HTTP GET Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_GET['nume']) && isset($_GET['mail']) && isset($_GET['regiune']) && isset($_GET['vot'])) {
 
    $nume = $_GET['nume'];
    $mail = $_GET['mail'];
    $regiune= $_GET['regiune'];
    $vot= $_GET['vot'];
 
    // include db connect class
    require_once __DIR__ . '/connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();

    $conn = mysqli_connect("localhost","root","","travel");
 
    // mysql inserting a new row (idioms)
    $query = "INSERT INTO voturi(nume, mail, regiune, vot) VALUES('$nume', '$mail', '$regiune', '$vot')";
    $result  = mysqli_query($conn , $query);
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "new VOT saved....";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>