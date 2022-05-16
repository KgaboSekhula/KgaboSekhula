package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;


public class SeleniumDriver  {


    public SeleniumDriver(){

    }

    private static WebDriver driver;
    private static WebDriverWait webDriverWait;
    ReadObject  p = new ReadObject();

    public final static int TIMEOUT =70;
    public final static int PAGE_LOAD_TIMEOUT =70;
    public  static String browser;
    private WebDriverWait wait;
    JavascriptExecutor js;
    boolean hasTestStarted =false;

    public void SetupTest(String url, String testName) throws IOException {

        BrowserConfig();
        ReportClass.ReportDirectory();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver,TIMEOUT);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, SECONDS);
        js = (JavascriptExecutor) driver;
        if (!hasTestStarted){
            TestReporter.extent = new ExtentReports(ReportClass.fullReportPath, true);
            hasTestStarted = true;
        }
        TestReporter.test = TestReporter.extent.startTest(testName);
        OpenPage(url);
    }



    private void BrowserConfig() throws IOException {

        browser = p.getObjectRepository().getProperty("browser");

        if (browser.equals("Chrome")) {

            WebDriverManager.chromedriver().setup();

            if (driver == null) {
                driver = new ChromeDriver();
            }
        }
        else if (browser.equals("FireFox")){
            WebDriverManager.firefoxdriver().setup();
        }
        else if (browser.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
        }
    }

    public void OpenPage(String url){
        driver.get(url);
    }

    public void EndTest(){

        if (driver!=null){

            driver.quit();
            driver = null;
        }
        TestReporter.extent.endTest(TestReporter.test);
        TestReporter.extent.flush();
    }

    //Actions Section

    public void Click(By locator, String stepInfo) throws Exception {

        try{

            WaitForElementToBeClickable(locator);
            driver.findElement(locator).click();
            TestReporter.PassStep(stepInfo);
        }
        catch (Exception ex){

            TestReporter.FailScenario("Unable to complete action - "+stepInfo);
            throw ex;
        }
    }

    public void SelectDropDownItemUsingSelector(By locator, String optionText, String stepInfo) throws Exception {


        try{

            WaitForElementToBeClickable(locator);
            Select dropDownOptions = new Select(driver.findElement(locator));
            dropDownOptions.selectByVisibleText(optionText);
            TestReporter.PassStep(stepInfo);
        }
        catch (Exception ex){

            TestReporter.FailScenario("Unable to complete step - "+stepInfo);
            throw ex;
        }


    }

    public void SelectItemFromDropDown(By locator, String optionText, String stepInfo) throws Exception {

        try{
            //string do build generic xpath
            String template = "//span[contains(text(),'%s')]";
            String dropDownItemXpath = String.format(template, optionText);
            WaitForElementToBeClickable(locator);
            //Click on the dropDown
            driver.findElement(locator).click();
            //Click on the item on the dropdown
            WaitForElementToBeClickable(By.xpath(dropDownItemXpath));
            driver.findElement(By.xpath(dropDownItemXpath)).click();
        }
        catch (Exception ex){

            TestReporter.FailScenario("Unable to complete step - "+stepInfo);
            throw ex;
        }

    }

    public void SelectItemFromSearchField(By locator, String optionText, String stepInfo) throws Exception {

        try{

            //string do build generic xpath
            String template = "//span[contains(text(),'%s')]";
            String dropDownItemXpath = String.format(template, optionText);
            WaitForElementToBeClickable(locator);
            //Clear search field

            driver.findElement(locator).clear();
            Thread.sleep(2000);
            driver.findElement(locator).sendKeys(optionText);
            String  isEmpty = driver.findElement(locator).getText();

            //Click on the item on the dropdown
            WaitForElementToBeClickable(By.xpath(dropDownItemXpath));
            driver.findElement(By.xpath(dropDownItemXpath)).click();
        }
        catch (Exception ex){

            TestReporter.FailScenario("Unable to complete step - "+stepInfo);
            throw ex;
        }

    }

    public void SendKeys(By locator, String text, String stepInfo) throws Exception {

        try{

            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
            TestReporter.PassStep(stepInfo);
        }
        catch (Exception ex){

            TestReporter.FailScenario("Unable to complete step - "+stepInfo);
            throw ex;
        }
    }

    public Boolean IsElementVisible(By locator)
    {
        boolean exists;
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        exists = driver.findElements(locator).size() != 0;
        driver.manage().timeouts().implicitlyWait(TIMEOUT, SECONDS);
        return exists;

    }

    public Object ElementList(By locator) throws Exception {

        try{
            return  driver.findElements(locator);
        }
        catch (Exception ignored){

            return null;
        }
    }

    public void WaitForElementToBeClickable(By locator)  {

        try{
            wait = new WebDriverWait(driver,15);
            wait.until(ExpectedConditions.elementToBeClickable (locator));
        }
        catch (Exception ignored){
        }
    }

    public void WaitForPageToLoad(int duration , String StepInfo) throws Exception {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(duration));
            TestReporter.PassStep(StepInfo);
        }catch (WebDriverException e)
        {
            TestReporter.FailScenario("Failed to execute the step "+ StepInfo);
        }
    }
     public boolean IsElementDisplayed(By locator){
        return driver.findElement(locator).isDisplayed();
     }


    public void SwitchToActiveWindow(){

        String mainWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

    }

    public String GetText(By locator){

        try{
            return driver.findElement(locator).getText();
        }
        catch (Exception ex){

            System.out.println("Unable to get get text from element");
            return null;
        }
    }

    public String GetURL() {
        return driver.getCurrentUrl();
    }

    public Alert PopUpAlert() {

        return driver.switchTo().alert();
    }

    public void AcceptAlert() throws InterruptedException {

        Alert alert = PopUpAlert();
        Thread.sleep(500);
        alert.accept();
    }
    public void ScrollToBottom(By Bodylocator) {

        /* body = driver.findElement(Bodylocator);

        Actions scrollDown = new Actions(driver).
                .MoveToElement(body, body.Size.Width - 10, 15) // position mouse over scrollbar
                .ClickAndHold()
                .MoveByOffset(0, 50) // scroll down
                .Release()
                .Build();

        scrollDown.Perform();*/


    }

    public String GetScreenshot() throws Exception {

        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = ReportClass.screenshortReportPath + File.separator + dateName+".PNG";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }

}
