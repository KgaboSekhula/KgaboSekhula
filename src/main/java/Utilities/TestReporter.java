package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReporter {

    //TestReporter members are static and must be implimented as such

    public static ExtentTest test;
    public static ExtentReports extent;
    static SeleniumDriver driverActions = new SeleniumDriver();


    public static void LogStep(String stepInfo){

        test.log(LogStatus.INFO, stepInfo);
    }

    public static void LogFailure(String reasonForFailure){

        test.log(LogStatus.FAIL, reasonForFailure);
    }

    public static void AddScreenShotToLog() throws Exception {

        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.INFO,  test.addScreenCapture(screenshotPath));
    }

    public static void PassStep(String passInfo) {

        test.log(LogStatus.PASS, passInfo);
    }

    public static void FailScenario(String reasonForFailure) throws Exception {

        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.FAIL,reasonForFailure,test.addScreenCapture(screenshotPath));
    }

    public static void PassScenarioWithScreenShot(String passInfo) throws Exception {

        String screenshotPath = driverActions.GetScreenshot();
        test.log(LogStatus.PASS, passInfo, test.addScreenCapture(screenshotPath));
    }
}
