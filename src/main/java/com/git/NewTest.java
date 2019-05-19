package com.git;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.utils.ExtentManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.apache.bcel.classfile.Method;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class NewTest {
	
	WebDriver driver;
	ExtentTest extentTest;
	
  @Test(priority=1)
  public void launch_Application() {
	  driver.get("https://www.google.com/");
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  Assert.assertEquals("Google", driver.getTitle());
  }
  
  @Test(priority=2)
  public void search_Keyword() {
	  driver.findElement(By.name("q")).sendKeys("India"+Keys.ENTER);
	  
	  Assert.assertEquals("India - Google Search", driver.getTitle());
  }
  
  @BeforeMethod
  public void beforeMethod(Method method) {
	  extentTest=ExtentManager.getInstance().startTest(method.getName().replaceAll("_", " "));
  }

  @AfterMethod
  public void afterMethod(Method method,ITestResult result) {
	  switch (result.getStatus()) {
		case ITestResult.SUCCESS:
				extentTest.log(LogStatus.PASS, "Test case "+method.getName()+" is passed");
			break;
		case ITestResult.FAILURE :
			extentTest.log(LogStatus.FAIL, "Test case "+method.getName()+" is failed");
			break;
		case ITestResult.SKIP :
			extentTest.log(LogStatus.SKIP, "Test case "+method.getName()+" is skipped");

		default:
			break;
		}
		  ExtentManager.getInstance().endTest(extentTest);
  }

  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	  driver=new ChromeDriver();
  }

  @AfterClass
  public void afterClass() {
	  ExtentManager.getInstance().flush();
	  ExtentManager.getInstance().close();
	  driver.close();
  }

}
