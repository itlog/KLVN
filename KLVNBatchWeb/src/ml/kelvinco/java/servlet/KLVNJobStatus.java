package ml.kelvinco.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KLVNJobStatus
 */
@WebServlet("/JobStatus")
public class KLVNJobStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobOperator jobOperator;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KLVNJobStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String jobName = request.getParameter("jobName");
//		response.setContentType("text/plain");
//		PrintWriter	out = response.getWriter();
//		out.print("Theres no instance of this job...");
		   String text = "some text";

		    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		    response.getWriter().write(text);       // Write response body.		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		
		
//		if(jobName!=null) {
//			JobOperator jobOperator = getJobOperator();
//			List<Long> jobStatus= new ArrayList<Long>();
//			try {
//				jobStatus = jobOperator.getRunningExecutions(jobName);
//				log("start", "jobOperator.getRunningExecutions(): " + jobOperator.getRunningExecutions(jobName));
//			} catch (NoSuchJobException e1) {
//				// TODO Auto-generated catch block
//				log("start","go ahead and run this job " +jobName);  
//			} catch (JobSecurityException e1) {
//				log("start","go ahead and run this job " +jobName); 
//			}
//			if(jobStatus.isEmpty()) {
//				out.print("Theres no instance of this job...");
//			}else {
//				out.print(jobOperator.getRunningExecutions(jobName).toString());
//			}
//				
//			
//		}	
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