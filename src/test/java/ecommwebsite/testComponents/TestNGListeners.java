package ecommwebsite.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ecommwebsite.resources.ExtentReporter;

public class TestNGListeners extends BaseTest implements ITestListener

{
	ExtentTest test;
	ExtentReports extent = ExtentReporter.getExtentReportObject();

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// This method is invoked when a test method passes successfully
		test.log(Status.PASS, "Test method passed: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// This method is invoked when a test method fails
		test.log(Status.FAIL, "Test method failed: " + result.getMethod().getMethodName());
		test.fail(result.getThrowable());

		// Now Attaching screenshot of the failure
		
	
			try {
				driver =  (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		String screenshotFilePath = null;
		try {
			screenshotFilePath = takeScreenshot(result.getMethod().getMethodName(), driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.addScreenCaptureFromPath(screenshotFilePath, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// This method is invoked when a test method is skipped (not executed)
		System.out.println("Test method skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// This method is invoked when a test method fails within success percentage
		// (typically used with test retries)
		System.out.println("Test method failed within success percentage: " + result.getMethod().getMethodName());
	}

	@Override
	public void onStart(ITestContext context) {
		// This method is invoked before any test method starts execution within the
		// <test> tag
		System.out.println("Test suite started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		// This method is invoked after all test methods within the <test> tag have
		// finished execution
		extent.flush();
		System.out.println("Test suite finished: " + context.getName());
	}

}
