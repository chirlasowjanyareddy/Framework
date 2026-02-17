package TestComponents;

import Sowjanya.resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends  BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal();
    @Override
    public void onTestStart(ITestResult result) {
        // called when test starts
        test = extent.createTest(result.getMethod().getMethodName()); // we have to create an entry for the extent report
        extentTest.set(test); // creates unique thread ID
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // called when test passes
        test.log(Status.PASS,"Test is Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // called when test fails
        extentTest.get().fail(result.getThrowable());
        // 1)screenshot 2) Attach to report
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filepath = null;
        try {
            filepath = String.valueOf(getScreenShot(result.getMethod().getMethodName(),driver));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // called when test is skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // rarely used
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // when test fails due to timeout
    }

    @Override
    public void onStart(ITestContext context) {
        // before <test> tag starts
    }

    @Override
    public void onFinish(ITestContext context) {
        // after <test> tag completes
        extent.flush();
    }
}
