package verifications;

import com.aventstack.extentreports.Status;
import com.launchings.BaseTest;

public class Verify3 extends BaseTest
{

	public static void main(String[] args) throws Exception 
	{
		init(); 
		//Here 'test' is reference variable from 'BaseTest'
		test = report.createTest("TC_006");
		test.log(Status.INFO,"Initializing the Properties files...." );
		
		launch("chromebrowser");
		test.log(Status.PASS,"Opened the Browser:-  " + p.getProperty("chromebrowser"));
		
		navigateUrl("amazonurl");
		test.log(Status.INFO,"Navigating to the Url:- "+childProp.getProperty("amazonurl"));
				
		
		String expectedLink = "Customer Serv";
		
		if(!isLinkEqual(expectedLink))
			//System.out.println("Both links are not equal...");
			reportsFailure("Both links are not equal...");
		else
			//System.out.println("Both links are equal...");
			reportsSuccess("Both links are equal...");
		report.flush();
	}
	
}
