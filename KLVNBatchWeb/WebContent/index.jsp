<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Controller</title>
<script type="text/javascript" src="common/js/jquery-3.3.1.js"></script> 
<!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script>  --> 
        <script>
            $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                $.get("JobStatus", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                });
            });
        </script>
</head>
<body>

        <button id="somebutton">press here</button>
        <div id="somediv"></div>

</body>
</html>