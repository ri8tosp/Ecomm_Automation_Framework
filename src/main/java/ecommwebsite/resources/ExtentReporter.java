package ecommwebsite.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter

{
	static ExtentReports extent;

	public static ExtentReports getExtentReportObject()

	{

		String path = System.getProperty("user.dir") + "/reports/index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Placing Order Test");
		reporter.config().setDocumentTitle("Ecomm Website Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "SJ");

		return extent;
	}
}
