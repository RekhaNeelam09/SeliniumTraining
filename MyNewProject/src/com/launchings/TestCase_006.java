package com.launchings;

import com.aventstack.extentreports.Status;

public class TestCase_006 extends BaseTest
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
		
		selectListOption("amazondropdown_id","Books");
		test.log(Status.PASS,"Selected the Books option using the locator:- " + locatorProp.getProperty("amazondropdown_id"));

		type("amazonsearchtextBox_name","Harry Potter");
		test.log(Status.PASS,"Entering the text Harry Potter using the locator:- " + locatorProp.getProperty("amazonsearchtextBox_name"));

		clickElement("amazonsearchbutton_xpath");		
		test.log(Status.PASS,"Clicked on the Element by using:- " + locatorProp.getProperty("amazonsearchbutton_xpath"));

		report.flush();
	}

}
