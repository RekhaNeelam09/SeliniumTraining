package com.launchings;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{	
	public static WebDriver driver;
	//Initializing Variable to get project Root Directory dynamically we use 'System.getProperty("user.dir")'
	//which will gives us current project Root Directory
	public static String projectPath = System.getProperty("user.dir");
	public static FileInputStream fis;
	public static Properties p;
	public static Properties mainProp;
	public static Properties childProp;
	public static Properties locatorProp;
	public static Date d;
	public static ExtentReports report;
	public static ExtentTest test;
	
	// Here Giving 'throws' to handle Exception for all types of Exceptions :
	public static void init() throws Exception
	{
		d = new Date(0);
		//Here as we need to read the data from data.properties file using 'FileInputStream' class which is available in 'java.io' :
		fis = new FileInputStream(projectPath+"\\data.properties");
		p = new Properties();
		//loading 'fis' file with the reference variable 'p' with 'load(InputStreamRef)' method :
		p.load(fis); // Here 'fis' is the InputStreamRef
		
		fis = new FileInputStream(projectPath+"\\environment.properties");
		mainProp = new Properties();
		mainProp.load(fis);
		String e = mainProp.getProperty("env");
		System.out.println(e);
		
		fis = new FileInputStream(projectPath+"\\"+e+".properties"); 
		childProp = new Properties();
		childProp.load(fis);
		System.out.println(childProp.getProperty("amazonurl"));
		
		fis = new FileInputStream(projectPath+"\\locators.properties");
		locatorProp = new Properties();
		locatorProp.load(fis);
		
		fis = new FileInputStream(projectPath+"\\log4jconfig.properties");
		// Here 'configure()' is a static method to configure the above log file from 'PropertyConfigure' class  
		PropertyConfigurator.configure(fis);
		
		//calling the 'getInstance()' method // whereas 'get Instance()' is a static method from ExtentManager class
		// with the return type of 'ExtentReports' class type :
		// Here , this is for initializing/generating HTML Reports :
		report = ExtentManager.getInstance();
		}
		
	public static void launch(String browser)
	{
		if(p.getProperty(browser).equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			//This for User-defined Chrome Browser :
			ChromeOptions option = new ChromeOptions();
			
			//Below is User defined Chrome path , after checking the path in browser we should copy the path and click on 'win+r' and paste over there.
			//That will redirect us to the location. Next There we need to create a folder named 'default' and copy the remaining stuff into the created folder.
			////option.addArguments("user-data-dir= C:\\Users\\My\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 2");
			driver = new ChromeDriver(option);
			
			//To handle the Notifications , here in this way also we can do : 
			////option.addArguments("--disable-notifications");
			
			//To maximize the window , here in way also we can give like this :
			////option.addArguments("--start-maximized");
			
			//To handle the Certificate Errors , here in this way we can give like this : 
			////option.addArguments("--ignore-certificate-errors-spki-list");
			
			//To work with Proxy Settings :
			// Here 'proxy server' means any specific server that we want to connect.
			//Ex : Google.com is in diff regions , if we want to connect to a specific region , every region is having an IP and Port Numbers.
			//If we give that IP and Port Number , our application will be connected and loading in our computer .
			// This kind of info will be provide by our organizations.
			////option.addArguments("--proxy-server=https://192.168.0.105:9090"); //dummy IP : '192.168.10.1' and Port Number : '9090'  We are providing here 
			
		}
		else if(p.getProperty(browser).equals("firefox"))
		{
			//load Binaries , clear the logs , handle Notifications , Certificate Errors , work with proxy settings all these we can do using our 
			//User defined firefox profiles
			WebDriverManager.firefoxdriver().setup();
			
			// We use a class called 'profileIni' to initiate and load the User defined firefox profile
			ProfilesIni p = new ProfilesIni();
			
			//After getting this profile , we have to set this profile. To set the profile we use a 'FirefoxOptions' class
			
			FirefoxProfile profile = p.getProfile("firefox Profile");
			
			FirefoxOptions option = new FirefoxOptions();
			option.setProfile(profile);
			
			//Binaries : If the firefox is not loading, physically or forcefully we use this to load the firefox browser
			////option.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			
			//Certificate Errors
			////profile.setAcceptUntrustedCertificates(true);
			////profile.setAssumeUntrustedCertificateIssuer(false);
			
			//Work with proxy settings //about:config
			////profile.setPreference("network.proxy.type", 1);
			//This is for IP Address in proxy preferences setting
			////profile.setPreference("network.proxy.socks", "192.168.10.1");//Note : dummy IP Address
			////profile.setPreference("network.proxy.socks_pory", 1744);
			
			
			//To Handle any notifications , we give the setPreference() having a Standard Key 'dom.webnotifications.enabled' and nxt parameter 
			//should be set to false so that we can stop the notifications
			profile.setPreference("dom.webnotifications.enabled", false);
			
			//For Logs for our user defined firefox , in our project in our root file
			////System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, projectPath+"\\firefoxlogs.log");
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public static void navigateUrl(String url)
	{
		//If we want to navigate to any application , the Web Driver method we use is 'get()' method.
		//The difference of using get() and navigate() is we can only navigate to the required 'url' but not go backward,forward and refresh.
		////driver.get(childProp.getProperty(url));
		//Using 'navigate().to()' we can use all the methods belonging to navigate() which are stated above .
		//One more difference is all the times the page/url loading will not hit the server it will take from the local cache.
		//Performance wise 'navigate().to()' will be fast.
		driver.navigate().to(childProp.getProperty(url));
		//To avoid more page loading time we use 
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}
	public static void clickElement(String locatorKey) 
	{
		//driver.findElement(By.xpath(LocatorProp.getProperty(locatorKey)).click();
		getElement(locatorKey).click();
	}

	public static void selectListOption(String locatorKey, String option) 
	{
		//driver.findElement(By.id(locatorKey)).sendKeys(option );
		getElement(locatorKey).sendKeys(option);
	}
	
	public static void type(String locatorKey, String text) 
	{		
		//driver.findElement(By.name(locatorKey)).sendKeys(text);
		getElement(locatorKey).sendKeys(text);
	}
	public static WebElement getElement(String locatorKey) 
	{
		//check for presence of Element
		if(!isElementPresent(locatorKey)) 
		//report the failure
		System.out.println("Element is not present: "+locatorKey);
		
		//check for visibility of Element
		if(!isElementVisible(locatorKey))
		//report the failure	
		System.out.println("Element is not visible: "+locatorKey);
		
		WebElement element = null;
		
		element = driver.findElement(getLocator(locatorKey));
		
		return element;
		  
	}

	public static By getLocator(String locatorKey) 
	{
	
		By by = null;
		if(locatorKey.endsWith("_id"))
		{
			by = By.id(locatorProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_name"))
		{
			by = By.name(locatorProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_classname"))
		{
			by = By.className(locatorProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_xpath")) 
		{
			by = By.xpath(locatorProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_css"))
		{
			by = By.cssSelector(locatorProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_linktext"))
		{
			by = By.linkText(locatorProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_partiallinktext"))
		{
			by = By.partialLinkText(locatorProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_tagname"))
		{
			by = By.tagName(locatorProp.getProperty(locatorKey));
		}
		return by;
		
	}

	public static boolean isElementVisible(String locatorKey) 
	{
		System.out.println("Checking visibility of :- "+locatorKey);
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		try 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
		}
		catch (Exception e) 
		{
			return false;
		}
		return true;
	}

	public static boolean isElementPresent(String locatorKey) 
	{
		System.out.println("Checking Element Presence :- "+locatorKey);
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		try 
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));
		}
		catch (Exception e) 
		{	
			return false;
		}
		return true;
	}
	
	public static boolean isLinkEqual(String expectedLink)
	{
		String actualLink = driver.findElement(By.linkText("Customer Service")).getText();
		if(actualLink.equals(expectedLink))
			return true;
		else
			return false;
	}
	//*********************************************** Reportings ******************************************************************
	public static void reportsSuccess(String successMsg) 
	{
		test.log(Status.PASS, successMsg);
		
	}

	public static void reportsFailure(String failureMsg) throws Exception 
	{
		
		test.log(Status.FAIL, failureMsg);
		takesScreenshot();
	}

	public static void takesScreenshot() throws Exception 
	{
		Date dt = new Date();
		System.out.println(dt);
		String dateFormat = dt.toString().replace(':', '_').replace(' ', '_') ;
		System.out.println(d);
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// Here 'copy()' is a static method with two parameters (File From , File To) calls with a class called 'FileHandler' :
		FileHandler.copy(srcfile, new File(projectPath+"//failurescreenshots//"+dateFormat));
		// Here we are using 'test.addScreenCaptureFromPath()' : 
		test.log(Status.INFO, "Screenshot ---> "+test.addScreenCaptureFromPath(projectPath+"//failurescreenshots//"+dateFormat));
		
	}


}
