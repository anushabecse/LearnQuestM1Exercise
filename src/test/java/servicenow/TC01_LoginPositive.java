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

public class TC01_LoginPositive {
	
	WebDriver driver;
	@BeforeTest
	public void launchAPP() throws InterruptedException
	{
		driver =new ChromeDriver();
		driver.manage().window().maximize();
		//Step 1: Navigate to service now
		
		driver.get("https://signon.service-now.com/x_snc_sso_auth.do");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Thread.sleep(6000);
		
	}
	@AfterTest
	
	public void closeTest()
	{
		driver.close();
	}
	@Test
	public void Loginvalidations() throws InterruptedException, IOException
	{
		//Step 2: Verify title is service now
		System.out.println("Title is "+driver.getTitle());
		//Step 3: Verify email text field has label of Email
		WebElement emailTextBox =driver.findElement(By.xpath("//input[@name='email']"));
		String emaillabel =driver.findElement(By.xpath("//span[contains(text(),'Email')]")).getText();
		System.out.println("Email label is "+emaillabel);
		//step 4:Enter Email
		emailTextBox.sendKeys("anushabecse11@gmail.com");
		//Step 5: Click on Next
		driver.findElement(By.id("sign_in_username_btn")).click();
		Thread.sleep(2000);
		//Step 6:Verify password text field ha label of Password
		WebElement pwdTextBox =driver.findElement(By.name("current-password"));
		String pwdlabel =driver.findElement(By.xpath("//span[contains(text(),'Password')]")).getText();
		
		System.out.println("Password label is "+pwdlabel);
		//Step 7:Enter password
		pwdTextBox.sendKeys("Password@1");
		//Step 8: User can see Forgot your password
		String forgotpwd =driver.findElement(By.linkText("Forgot your password?")).getText();
		if(forgotpwd.equals("Forgot your password?")) {
			System.out.println("'Forgot your password' is visible");
		}
		else
		{
			System.out.println("'Forgot your password' is not visible");
		}
		//Capture Screenshot
		File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target =new File(".\\screenshot\\LoginPage.png");
		FileUtils.copyFile(screenshotfile, target);
		//step 9: User can see Sign in Button
		String signele =driver.findElement(By.id("sign_in_password_btn")).getText();
		if (signele.equals("Sign In"))
		{
			System.out.println("user able to see 'Sign In' Button");
		}
		else
		{
			System.out.println("User not able to see 'Sign In' Button");
		}
		//Step 10: Click on Sign in Button
		
		driver.findElement(By.id("sign_in_password_btn")).click();
		Thread.sleep(2000);
		//Step 11:After login Verify title as service now
		
		System.out.println("After login Title is: "+driver.getTitle());
	}
}
