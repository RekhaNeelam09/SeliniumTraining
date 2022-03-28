package com.launchings;

import org.apache.log4j.Logger;

public class TestCase_004 extends BaseTest
{
	// Here we are creating a logger class reference variable 'log' to generate logs for the test cases.
	// 'getLogger' is one of the static methods with the className 'Logger' we call which is having a return type
	// 'logger'
	private static final Logger log = Logger.getLogger(TestCase_004.class);
	
	public static void main(String[] args) throws Exception 
	{
		init(); 
		log.info("Execution log info : "+d);
		log.info("Initializing the Properties files....");
		
		launch("chromebrowser");
		log.info("Opened the Browser:-  " + p.getProperty("chromebrowser"));
		
		navigateUrl("amazonurl");
		log.info("Navigating to the Url:- "+childProp.getProperty("amazonurl"));
		
		selectListOption("amazondropdown_id","Books");
		log.info("Selected the Books option using the locator:- " + locatorProp.getProperty("amazondropdown_id"));

		type("amazonsearchtextBox_name","Harry Potter");
		log.info("Entering the text Harry Potter using the locator:- " + locatorProp.getProperty("amazonsearchtextBox_name"));

		clickElement("amazonsearchbutton_xpath");		
		log.info("Clicked on the Element by using:- " + locatorProp.getProperty("amazonsearchbutton_xpath"));

	}

}
