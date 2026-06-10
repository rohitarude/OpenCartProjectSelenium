package utilities;
import java.io.File;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

import java.util.List;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	

	public void onStart(ITestContext  testContext) {
		
		String timestamp =new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		repName = "test-report"+ timestamp +".html";
		sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName);
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "customer");
		extent.setSystemInfo("User Name" ,  System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operating system", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups =testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
				
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ " Test successfully passed");
		
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+ " got Failed");
		
		if(result.getThrowable() != null) {
		    test.log(Status.INFO, result.getThrowable().getMessage());
		}
		
		try {
		String imagePath = new BaseClass().captureScreen1(result.getName());
		test.addScreenCaptureFromPath(imagePath);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " test skipped");
		if(result.getThrowable() != null) {
		    test.log(Status.INFO, result.getThrowable().getMessage());
		}
		
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}

