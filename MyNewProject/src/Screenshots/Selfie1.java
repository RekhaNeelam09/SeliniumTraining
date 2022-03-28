package Screenshots;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selfie1 {

	public static void main(String[] args) throws Exception 
	{
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https:www.amazon.in");
		
		// Here 'TakesScreenshot' is an interface for getting the screenshot using 'getScreenshotAs()' with the parameters
		// of 'OutputType.FILE'  class with the return type 'FILE' :
		// And also taking this in ref variable 'srcfile' of type 'File' :
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// Here 'copy()' is a static method with two parameters (File From , File To) calls with a class called 'FileHandler' :
		FileHandler.copy(srcfile, new File("C:\\Users\\My\\Desktop\\Screenshots\\amazon.png"));

	}

}
