package com.launchings;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunchings {

	public static void main(String[] args) {
		//setProperty is a static method is defined under 'System' class has two properties as key and value pairs
		System.setProperty("webdriver.chrome.driver", "C:/Users/My/Desktop/Drivers/chromedriver.exe");
		//Web Driver interface abstract methods are implemented in Driver classes
		@SuppressWarnings("unused")
		ChromeDriver driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", "C:/Users/My/Desktop/Drivers/geckodriver.exe");
		@SuppressWarnings("unused")
		FirefoxDriver driver1 = new FirefoxDriver();
	}

}
