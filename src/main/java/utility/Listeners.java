package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.Base;

public class Listeners extends Base implements ITestListener {

	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports = new ExtentReports();
	ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	ExtentTest extentTest;
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
	String currtime = sdf.format(new Date());

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		String path = System.getProperty("user.dir") + "\\reports\\TestReport.html";
		extentSparkReporter = new ExtentSparkReporter(new File(path));
		extentSparkReporter.config().setReportName("Test Automation Report");
		extentSparkReporter.config().setDocumentTitle("Test Report");
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("tester", prop.getProperty("tester"));
		extentReports.setSystemInfo("env", prop.getProperty("env"));
		extentReports.setSystemInfo("OS", prop.getProperty("OS"));
		extentReports.setSystemInfo("browser", prop.getProperty("browser"));
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
		System.out.println("Report started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.get().log(Status.PASS, result.getMethod().getMethodName() + " passed");
		System.out.println("TC passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test.get().log(Status.FAIL, result.getMethod().getMethodName() + " failed");
		test.get().log(Status.FAIL, result.getThrowable());
		String sspath = System.getProperty("user.dir") + "\\screenshots\\" + result.getMethod().getMethodName() + "_"
				+ currtime + "_Fail.png";
		captureScreenshot(getDriver(), sspath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.get().log(Status.SKIP, result.getMethod().getMethodName() + " skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReports.flush();
	}

	void captureScreenshot(WebDriver driver, String sspath) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File ssFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(ssFile, new File(sspath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Screenshot captured successfully");
	}

}
