<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Controller</title>
<!-- <script type="text/javascript" src="common/js/jquery-3.3.1.js"></script>  -->
<!-- <script src="http://code.jquery.com/jquery-latest.min.js"></script>  --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script>
            $(document).on("click", "#submit", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            	var jobName = document.getElementById("jobName").value;
            	$.get("JobStatus?jobName="+jobName, function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $("#result").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                });
            });
            
        </script>
</head>
<body>
		<input type="text" id="jobName"></input>
        <button id="submit">submit job</button>
        <br><br>
        <div id="result"></div>

</body>
</html>