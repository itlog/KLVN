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
            function submitJob(jobName) { 
            	//var jobName = document.getElementById("jobName").value;
            	$.get("JobController?jobName="+jobName, function(responseText) {   
            		$("#"+jobName).text(responseText);
            		});
            }  
            var timeout = setInterval(reloadPage, 5000);    
            function reloadPage () {
            	$.get("JobStatus?jobName=KLVNBatchlet", function(responseText) {$("#KLVNBatchlet").text(responseText);});
            	$.get("JobStatus?jobName=KLVNChunk", function(responseText) {$("#KLVNChunk").text(responseText);});
            }
        </script>
</head>
<body>
<table border="1">
<tr>
<th>Job Name</th>
<th>action</th>
<th>Job Status</th>
</tr>
<tr>
<td>KLVNBatchlet</td>
<td><button type="button" onclick="submitJob('KLVNBatchlet')">submit job</button>
<button type="button" onclick="stopJob('KLVNBatchlet')">stop job</button>
</td>
<td><span id="KLVNBatchlet"></span></td>
</tr>

<tr>
<td>KLVNChunk</td>
<td><button type="button" onclick="submitJob('KLVNChunk')">submit job</button>
<button type="button" onclick="stopJob('KLVNChunk')">stop job</button>
</td>
<td><span id="KLVNChunk"></span></td>
</tr>


</table>


</body>
</html>