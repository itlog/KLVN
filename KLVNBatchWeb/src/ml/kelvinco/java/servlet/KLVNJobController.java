package ml.kelvinco.java.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ml.kelvinco.java.util.KLVNJConstants;

/**
 * Servlet implementation class KLVNJobController
 */
@WebServlet(urlPatterns = { "/JobController" })
public class KLVNJobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final static Logger logger = Logger.getLogger(KLVNJobController.class.getName());  
	private JobOperator jobOperator;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KLVNJobController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("************************************************");
		System.out.println(KLVNJConstants.APP_NAME +" : v" +KLVNJConstants.APP_VERSION);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		logger.info("KLVNJobController :  doGet() : ");
		String jobName = request.getParameter("jobName");

		
		 String current = new java.io.File( "." ).getCanonicalPath();
	        System.out.println("Current dir:"+current);
		if(jobName!=null) {
			if(jobName.equals("KLVNBatchlet")) {
					System.out.println("here");	
					 start(request, response,jobName);
				}			
		}

		System.out.println("************************************************");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

    protected void start(HttpServletRequest request, HttpServletResponse response,String jobName) throws IOException {

        String jobXMLName = jobName;
        log("start", "jobXMLName: " + jobXMLName);
        Properties jobParameters = getJobParameters(request, "jobParameters");
//        log("start", "jobParameters: " + jobParameters);
        
        JobOperator jobOperator = getJobOperator();

        	List<Long> jobStatus= new ArrayList<Long>();
			try {
				jobStatus = jobOperator.getRunningExecutions(jobXMLName);
				log("start", "jobOperator.getRunningExecutions(): " + jobOperator.getRunningExecutions(jobXMLName));
			} catch (NoSuchJobException e1) {
				// TODO Auto-generated catch block
				log("start","go ahead and run this job " +jobXMLName);  
			} catch (JobSecurityException e1) {
				log("start","go ahead and run this job " +jobXMLName); 
			}

		
		log("start","jobStatus : " +jobStatus   );

        if(jobStatus.isEmpty()) {
			    long execId = jobOperator.start(jobXMLName, jobParameters);

			    JobInstance jobInstance = jobOperator.getJobInstance(execId);
			    JobExecution jobExecution = jobOperator.getJobExecution(execId);

			    log("start","jobInstance : " +jobInstance);
			    log("start","jobExecution : " +jobExecution);        	
        }else {
        	 log("start","This job " +jobXMLName +" is still running.. please wait.");     
        }

        
    }	
	
	
	
    protected Properties getJobParameters(HttpServletRequest request, String queryParmName) throws IOException {
        String[] jobParameters = request.getParameterValues(queryParmName);
        if (jobParameters == null ) {
            return null;
        }

        Properties retMe = new Properties();

        for (String jobParameter : jobParameters) {
            log("getJobParameters", "jobParameter: " + jobParameter);
            String[] keyValue = jobParameter.split("=");
            retMe.setProperty(keyValue[0], (keyValue.length >= 2) ? keyValue[1] : null);
        }

        log("getJobParameters", "retMe: " + retMe);
        return retMe;
    }
    /**
     * @return the batch JobOperator
     */
    protected JobOperator getJobOperator() {
        return (jobOperator != null) ? jobOperator : (jobOperator = BatchRuntime.getJobOperator());
    }   	
    protected static void log(String method, Object msg) {
        System.out.println("JobOperator: " + method + ": " + String.valueOf(msg));
    }
}
