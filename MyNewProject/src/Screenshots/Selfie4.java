package Screenshots;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selfie4
{

	public static void main(String[] args) throws Exception
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
		Date dt = new Date();
		System.out.println(dt);
		String d = dt.toString().replace(':', '_').replace(' ', '_') ;
		System.out.println(d);
		
		List<WebElement> links = driver.findElements(By.xpath("//div[@id='SIvCob']/a"));
		for(int i=0;i<links.size();i++) 
		{
			if(!links.get(i).getText().isEmpty()) 
			{
				String linkName = links.get(i).getText();
				links.get(i).click();
				File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
				// Here 'copy()' is a static method with two parameters (File From , File To) calls with a class called 'FileHandler' :
				FileHandler.copy(srcfile, new File("C:\\Users\\My\\Desktop\\Screenshots\\"+linkName+"_"+d+".jpeg"));
				
				links = driver.findElements(By.xpath("//div[@id='SIvCob']/a"));
			}		

		}

	}

}