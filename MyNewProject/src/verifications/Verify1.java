package verifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Verify1 
{

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		
		////String actualLink = driver.findElement(By.linkText("Customer Service")).getText();
		String actualLink = driver.findElement(By.linkText("Customer Service")).getAttribute("innerHTML");
		String expectedLink = "Customer Service";
		////String expectedLink = "customer service";
		////String expectedLink = "customer serv";
		System.out.println("Actual Link : "+actualLink);
		System.out.println("Expected Link : "+expectedLink);
		////if(actuaLink.equalsIgnoreCase(expectedLink))
		////if(actuaLink.contains(expectedLink))
		if(actualLink.equals(expectedLink))
			System.out.println("Both links are equal");
		else
			System.out.println("Both links are not equal");
	}

}
