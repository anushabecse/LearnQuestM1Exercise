package lumaapplication;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateLumaApplication extends GenerateRandomString{
	
	WebDriver driver;
	
	@BeforeTest
	public void launchApp() throws InterruptedException
	{
		driver =new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
		
		Thread.sleep(3000);
	}
	@AfterTest
	
	public void closeTest()
	{
		driver.close();
	}
	@Test(priority=1)
	public void CreateAppliation() throws InterruptedException, IOException
	{
		
		//Personal Information
		
		WebElement firstname =driver.findElement(By.name("firstname"));
		firstname.sendKeys(getAlpahanumericString(8));
		
		WebElement lastname =driver.findElement(By.name("lastname"));
		lastname.sendKeys(getAlpahanumericString(5));
		
		//signin information
		
		WebElement emailid =driver.findElement(By.id("email_address"));
		emailid.sendKeys(getAlpahanumericString(8)+"@gmail.com");
		
		WebElement password =driver.findElement(By.id("password"));
		String pwd ="Password@1";
		password.sendKeys(pwd);
		
		WebElement confirmPwd =driver.findElement(By.id("password-confirmation"));
		confirmPwd.sendKeys(pwd);
		
		//click on create button
		driver.findElement(By.xpath("//button[@title='Create an Account']")).click();
		
		Thread.sleep(3000);
		//Validation Navigated to MyAccount page or not
		String myaccount =driver.findElement(By.xpath("//span[@class='base']")).getText();
		String actualtext ="My Account";
		
		if (myaccount.equalsIgnoreCase(actualtext))
		{
			System.out.println("Login Successful!!Naviagated to My Account Page");
		}
		else
		{
			System.out.println("Login failed!");
		}
		
		File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target =new File(".\\screenshot\\MyAccountpage.png");
		FileUtils.copyFile(screenshotfile, target);
		
	}
	
	@Test(priority=2)
	
	public void AddAddress() throws InterruptedException, IOException
	{
	// Click on Address Book	
		driver.findElement(By.linkText("Address Book")).click();
		Thread.sleep(2000);
		WebElement street1= driver.findElement(By.xpath("//input[@id='street_1']"));
		street1.sendKeys(getAlpahanumericString(9));
		WebElement street2= driver.findElement(By.xpath("//input[@id='street_2']"));
		street2.sendKeys(getAlpahanumericString(7));
		WebElement street3= driver.findElement(By.xpath("//input[@id='street_3']"));
		street3.sendKeys(getAlpahanumericString(8));
		WebElement phonenum =driver.findElement(By.name("telephone"));
		phonenum.sendKeys(generateNumbers(10));
		Select drpCountry =new Select(driver.findElement(By.name("country_id")));
		drpCountry.selectByValue("IN");
		WebElement strcity =driver.findElement(By.name("city"));
		strcity.sendKeys("Chennai");
		Select drpSate  =new Select(driver.findElement(By.name("region_id")));
		drpSate.selectByVisibleText("Tamil Nadu");
		WebElement postalcode =driver.findElement(By.name("postcode"));
		postalcode.sendKeys("600125");
		Thread.sleep(1000);
		
		//Save address
		driver.findElement(By.xpath("//button[@title='Save Address']")).click();
		Thread.sleep(2000);
		
		//Validation
		WebElement strSaveAddress =driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		
		String strActualText =strSaveAddress.getText();
		String strExpText ="You saved the address.";
		if(strActualText.equalsIgnoreCase(strExpText))
		{
			System.out.println("Pass!Able to add address successfully");
		}
		else
		{
			System.out.println("Fail!Address not added successfully");
		}
		
		File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target =new File(".\\screenshot\\SaveAddress.png");
		FileUtils.copyFile(screenshotfile, target);
	}

}

