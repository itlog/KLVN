package ml.kelvinco.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobInstance;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class KLVNJobStatus
 */
@WebServlet(urlPatterns = { "/JobStatus" })
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
		String jobName = request.getParameter("jobName");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter	out = response.getWriter();
		
		if(jobName!=null) {
		JobOperator jobOperator = getJobOperator();
		List<Long> jobStatus= new ArrayList<Long>();
		try {
			jobStatus = jobOperator.getRunningExecutions(jobName);
			log("start", "jobOperator.getRunningExecutions(): " + jobOperator.getRunningExecutions(jobName));
		} catch (NoSuchJobException e1) {
			// TODO Auto-generated catch block
			log("start","go ahead and run this job " +jobName);  
		} catch (JobSecurityException e1) {
			log("start","go ahead and run this job " +jobName); 
		}
		if(jobStatus.isEmpty()) {
			out.print("Theres no instance of this job..." +jobName);
		}else {
			for(int x=0;x<jobStatus.size();x++) {				
				JobInstance jobInstance = jobOperator.getJobInstance(jobStatus.get(x));
				out.print(jobInstance +"<br>");				
			}
			
			
		}
			
		
	}	
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    /**
     * @return the batch JobOperator
     */
    protected JobOperator getJobOperator() {
        return (jobOperator != null) ? jobOperator : (jobOperator = BatchRuntime.getJobOperator());
    }   	
    protected static void log(String method, Object msg) {
        System.out.println("JobStatus: " + method + ": " + String.valueOf(msg));
    }
    
}
