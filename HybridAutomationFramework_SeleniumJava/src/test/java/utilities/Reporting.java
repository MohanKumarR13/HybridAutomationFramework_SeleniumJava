package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class Reporting extends TestListenerAdapter {
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext testContext) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentDateTimeStamp = dateFormat.format(dt);
		String timeStamp = new SimpleDateFormat("dd-M-yyyy hh.mm.ss").format(new Date());
		repName = "Automation-" + timeStamp + ".html";
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\" + repName);// specify
																													// location
																													// of
																													// the
																													// report
		// extentSparkReporter.loadXMLConfig(System.getProperty("user.dir")+
		// "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(extentSparkReporter);

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());

		}
		extent.setSystemInfo("user", "mohan");

		extentSparkReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		extentSparkReporter.config().setReportName("Functional Testing"); // name of the report
		extentSparkReporter.config().setTheme(Theme.STANDARD);
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// Display groups in the report
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN)); // send the passed
																								// information to the
																								// report with GREEN
																								// color highlighted
	}

	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in th report
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information
																						// to the report with GREEN
																						// color highlighted

		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		try {
			test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * public void onTestFailure(ITestResult tr) {
	 * test=extent.createTest(tr.getName());
	 * test.assignCategory(tr.getMethod().getGroups());// Display groups in the
	 * report
	 * test.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
	 * // send the passed information to the report with GREEN color highlighted
	 * 
	 * test.log(Status.FAIL,tr.getName()+"Got Failed");
	 * test.log(Status.INFO,tr.getThrowable().getMessage());
	 * 
	 * 
	 * try { String imgPath=new BaseClass().captureScreen(tr.getName());
	 * test.addScreenCaptureFromPath(imgPath); // test.fail("Screenshot is below:" +
	 * test.addScreenCaptureFromPath(screenshotPath)); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getTestClass().getName());
		test.assignCategory(tr.getMethod().getGroups());// Display groups in the report
		test.log(Status.SKIP, tr.getName() + "Got Skipped");
		test.log(Status.INFO, tr.getThrowable().getMessage());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
		String pathOfReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReports = new File(pathOfReport);
		try {
			Desktop.getDesktop().browse(extentReports.toURI());
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			URL url = new URL("file://" + System.getProperty("user.dir") + "\\reports\\" + repName);
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googleemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("pavanoltraininig@gmail.com");
			email.setSubject("Test Results");
			email.setMsg("Please Find Attached Report");
			email.addTo("mohanvedha98@gmail.com");
			email.attach(url, "extent report", "please check report");
			email.send();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
