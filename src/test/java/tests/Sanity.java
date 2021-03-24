package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Main;
import utilites.GetDriver;
import utilites.Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.logging.log4j.spi.LocationAwareLogger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;


public class Sanity {

	// Global variables 
	// Add extent reports
	private ExtentReports extent;
	private ExtentTest myTest;
	private static String reportPaht = System.getProperty("user.dir") + "\\test-output\\payoneer.html";

	private WebDriver driver;
	private String baseUrl;
	
	
	//pages
	private Main main;
	
//	private static final Logger Logger = LogManager.getLogger(Sanity.class);
	
	
	@BeforeClass
	public void beforeClass() {
		extent = new ExtentReports(reportPaht);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\payoneer-extent-config.xml"));
		baseUrl = "https://www.payoneer.com/";
		driver = GetDriver.getDriver("chrome", baseUrl);
		
		main = new Main(driver);
	
	}

	@BeforeMethod
	public void beforeMethod(Method method) throws IOException {
		myTest = extent.startTest(method.getName());
		myTest.log(LogStatus.INFO, "Starting test", "Start test");
	}
	
	
	/*  Prerequisite: getting into https://www.payoneer.com/
	 * 		Given: Client is in site 
	 * 		When: click on register button
	 *  	Then: Getting into 'select account page'
	 */
	
	@Test(priority = 1, enabled = false, description = "verify date")
	public void go_to_registration() throws InterruptedException, IOException {	
		System.out.println("*** clicked on the button");
		Assert.assertTrue(main.goRegister());
	}
	
	@Test(priority = 2, enabled = false, description = "verify date")
	public void selectedFromFild() throws InterruptedException, IOException {
		System.out.println("**** selected fileds");
		Assert.assertTrue(main.selectFaild());
	}
	
	@Test(priority = 3, enabled = false, description = "verify date")
	public void registerSecundPage() throws InterruptedException, IOException {	
		System.out.println("*** clicked on the secound regis");
		Assert.assertTrue(main.goRegister2());
	}
	
	@Test(priority = 4, enabled = true, description = "verify date")
	public void verifyURL() throws InterruptedException, IOException {
		System.out.println("verify page URL");
		Assert.assertTrue(main.verfyUrl());
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			myTest.log(LogStatus.FAIL, "Test failed: " + result.getName());
			myTest.log(LogStatus.FAIL, "Test failed reason: " + result.getThrowable());
			myTest.log(LogStatus.FAIL, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));
		}
		else {
			myTest.log(LogStatus.PASS, result.getName(), "Verify successful ");
			myTest.log(LogStatus.PASS, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));

		}

		myTest.log(LogStatus.INFO, "Finish test", "Finish test ");
		extent.endTest(myTest);
	
		//return to base URL 
		//driver.get(baseUrl);
	}

	@AfterClass
	public void afterClass() {
		extent.flush();
		extent.close();
		driver.quit();

	}

	
}
