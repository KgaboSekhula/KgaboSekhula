package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver  {


    public static WebDriver driver;
    private static WebDriverWait webDriverWait;
    ReadObject  p = new ReadObject();

    public final static int TIMEOUT =120;
    public final static int PAGE_LOAD_TIMEOUT =120;
    public  static String browser;
    private WebDriverWait wait;
    JavascriptExecutor js;
    boolean hasTestStarted =false;

    public void SetupTest(String url, String testName) throws IOException {
        BrowserConfig();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver,TIMEOUT);
        driver.manage().timeouts().implicitlyWait(TIMEOUT,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        if (!hasTestStarted){
            ReportClass.ReportDirectory();
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
            driver = new FirefoxDriver();
        }
        else if (browser.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
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
    public void TearDown(){

        if (driver!=null){

            driver.close();
            driver.quit();
        }
        TestReporter.extent.endTest(TestReporter.test);
        TestReporter.extent.flush();
    }

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

    public String GetElementValue(By locator)
    {
       return driver.findElement(locator).getAttribute("value");
    }

    public void SelectDropDown(By locator, String optionText,String stepInfo) throws Exception {
            try {
                WaitForElementToBeClickable(locator);
                Select dropDownOptions = new Select(driver.findElement(locator));
                dropDownOptions.selectByVisibleText(optionText);
                TestReporter.PassStep(stepInfo);

            }catch (Exception ex){

            TestReporter.FailScenario("Unable to complete step - "+stepInfo);
            throw ex;
        }
    }

    public void WindowScroll(){
        JavascriptExecutor j = (JavascriptExecutor)driver;
        j.executeScript("window.scrollBy(0,-500)");
    }

    public void SelectItemFromDropDown(By locator, String optionText){

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

    public void SelectItemFromSearchField(By locator, String optionText) throws InterruptedException {

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


   public void BackSpaceKey() throws AWTException {
    Robot robot = new Robot();
    robot.setAutoDelay(250);
    robot.keyPress(KeyEvent.VK_BACK_SPACE);
}
    public void DoubleClick(By locator)
    {
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(locator)).doubleClick().build().perform();
          }
    public String PageTextPresent(String text)
    {
       // driver.switchTo().frame("main");

        if(driver.getPageSource().contains(text)){
            System.out.println("Text is present");
        }else{
            System.out.println("Text is absent");
        }
        return text;
    }
    public Boolean IsElementVisible(By locator)
    {
        boolean exists;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        exists = driver.findElements(locator).size() != 0;
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        return exists;

    }

    public List<WebElement> ElementList(By locator){

        return  driver.findElements(locator);
    }

    public void WaitForElementToBeClickable(By locator)  {

        wait = new WebDriverWait(driver,15);
       // wait.until(ExpectedConditions.elementToBeClickable (locator));
    }

    public void ScrollElementToView(By locator){

        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", locator);
    }

    public void RefreshPage(){

        driver.navigate().refresh();
    }

    public String SwitchToActiveWindow(){

        String mainWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        return mainWindow;
    }

    public String GetText(By locator){

        return driver.findElement(locator).getText();
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

    public void BFG() throws IOException {
        File input = new File("CC/CaptchImage/captch.png");
        BufferedImage image = ImageIO.read(input);

        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphic = result.createGraphics();
        graphic.drawImage(image, 0, 0, Color.GREEN, null);
        graphic.dispose();

        File output = new File("CC/CaptchImage/captch.png");
        ImageIO.write(result, "jpg", output);
    }//main() ends here



    public void DownloadImageFromWebPage(By locator) throws IOException, InterruptedException {

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        WebElement element=driver.findElement(locator);
        System.out.println(element.getSize());

        // Take full screen screenshot
        BufferedImage  fullImg = ImageIO.read(screenshot);
        ImageIO.read(screenshot).getHeight();
        System.out.println(fullImg.getHeight());
        System.out.println(fullImg.getWidth());

        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();

        // Now no exception here
        BufferedImage elementScreenshot= fullImg.getSubimage(660, 500,elementWidth+150,elementHeight+30);

        // crop the image to required
        ImageIO.write(elementScreenshot, "png", screenshot);
        FileUtils.copyFile(screenshot, new File("CC/CaptchImage/captch1.png"));
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
