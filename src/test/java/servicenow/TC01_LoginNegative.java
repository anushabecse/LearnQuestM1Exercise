package servicenow;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC01_LoginNegative {

	
	WebDriver driver;
//	WebDriverWait wait;
	@BeforeTest
	public void launchAPP() throws InterruptedException
	{
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		//Step 1: Navigate to service now
		
		driver.get("https://signon.service-now.com/x_snc_sso_auth.do");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Thread.sleep(10);
		//WebDriverWait wait =new WebDriverWait(driver,20);
		
	}
	
	@AfterTest
	
	public void closeTest()
	{
		driver.close();
	}
	
	@Test
	public void loginNegativeVal() throws InterruptedException, IOException
	{
		//Step 2: Verify title is service now
		System.out.println("Title is "+driver.getTitle());
		Thread.sleep(2000);
		//Step 3: User clicks on login button
		driver.findElement(By.xpath("//a[@id='sign_in_username_btn']")).click();
		Thread.sleep(2000);
		//Step 4:Verify error message
		WebElement emailrequired =driver.findElement(By.xpath("//span[@class='UF_validation_message UF_invalid']"));
		String erremailReq =emailrequired.getText();
		System.out.println("Error message is dispalayed as " +erremailReq);
		//Step 5: Enter email id
		WebElement emaillabel =driver.findElement(By.xpath("//input[@name='email']"));
		emaillabel.sendKeys("anushabecse11@gmail.com");
		//Step 6:Click on Next button and click on sign in
		driver.findElement(By.id("sign_in_username_btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("sign_in_password_btn")).click();
		Thread.sleep(2000);
		//Step 7:Validate error message
		WebElement pwdrequired =driver.findElement(By.xpath("//span[@class='UF_validation_message UF_invalid']"));
		String errpwdReq =pwdrequired.getText();
		System.out.println("Error message is dispalayed as " +errpwdReq);
		//Capture Screesnhot
		File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target =new File(".\\screenshot\\ErrorPage.png");
		FileUtils.copyFile(screenshotfile, target);
		
		
	}
}
