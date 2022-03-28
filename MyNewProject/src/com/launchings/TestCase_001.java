package com.launchings;

public class TestCase_001 extends BaseTest
{

	public static void main(String[] args) throws Exception 
	{
		init();
	
		launch("chromebrowser");
		
		navigateUrl("amazonurl");
		
		driver.manage().window().maximize();
		
		String title = driver.getTitle();
		System.out.println(title);
		
		String url = driver.getCurrentUrl();
		System.out.println(url);
		
		driver.manage().deleteAllCookies(); 
		
		driver.navigate().forward();
		//delaying the browser for 4 sec we use a static method 'sleep' from 'Thread' class
		Thread.sleep(4000);
		
		driver.navigate().back();
		
		//'Thread.sleep()' is a Java wait statement but not Selenium's
		Thread.sleep(4000);
		driver.navigate().refresh();
		
		//To close the instance of the browser, we use close() method
		//closing the current active window but not the backend driver services will not be closed
		driver.close();
		
		driver.quit();
		

	}

}
