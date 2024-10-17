package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseTestpack.BaseTest;

public class ExtentReportUtils extends BaseTest implements ITestListener{
	ExtentSparkReporter sparkReporter;
	ExtentReports report;
	ExtentTest test;
	public void onStart(ITestContext context) {
		SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd_HH-mm-SS");
		String date =sd.format(new Date());
		sparkReporter = new ExtentSparkReporter("./reports/AutomationTestReport-"+date);
		sparkReporter.config().setDocumentTitle("AutomationTestReport");
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Application", "OpenCart");
		report.setSystemInfo("Module", "Customer");
		report.setSystemInfo("Username", System.getProperty("user.name"));
		report.setSystemInfo("browser", context.getCurrentXmlTest().getParameter("browser"));
		report.setSystemInfo("Operating System", context.getCurrentXmlTest().getParameter("os"));
		report.setSystemInfo("Environment", "QA Environment");
		if(!context.getCurrentXmlTest().getIncludedGroups().isEmpty()) {
			report.setSystemInfo("Test groups", context.getCurrentXmlTest().getIncludedGroups().toString());
		}
		else {
			report.setSystemInfo("Test groups", "Generic(No Testgroup Included!)");
		}
	}
	public void onFinish(ITestContext context) {
		report.flush();
		//can open the report automatically after test execution is completed!
		// and send report after test execution is completed!
		// Ref here for how to? https://youtu.be/xeEVNVEVefM?list=PLUDwpEzHYYLtQzEEEldbjPAR-gnStv4sR&t=4013
	}
	public void onTestStart(ITestResult result) {
		//can use this method for other purpose but not used for reporting as it is not useful.
	}
	public void onTestSuccess(ITestResult result) {
		//test= report.createTest(result.getName());
		test= report.createTest(result.getTestClass().getName()+"- "+result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Passed!");
	}
	public void onTestFailure(ITestResult result) {
		test= report.createTest(result.getTestClass().getName()+"- "+result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed!");
		test.log(Status.INFO, result.getThrowable().getMessage().equals(null) ? "No exception! check the validation logic!": result.getThrowable().getMessage());
		try {
			BaseTest baseTest= new BaseTest();
			String path = baseTest.takeScreenshot(result.getName());
			System.out.println(path);
			test.addScreenCaptureFromPath(path);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result) {
		test= report.createTest(result.getTestClass().getName()+"- "+result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped!");
		test.log(Status.INFO, result.getThrowable().getMessage().equals(null) ? "No exception! check the validation logic!": result.getThrowable().getMessage());
	}
}
