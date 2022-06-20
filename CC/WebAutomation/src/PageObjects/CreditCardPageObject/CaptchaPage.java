package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

import static Utilities.SeleniumDriver.driver;

public class CaptchaPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By radioButtonYes = By.cssSelector("input[value='Y'][name='savedCCApplicationyn']");
    By radioButtonNo = By.cssSelector("input[value='N'][name='savedCCApplicationyn']");
    By captchaImg = By.xpath("//img[@alt='captcha']");
    By capture = By.cssSelector("input[name='captchaInput']");
    By continueButton = By.cssSelector("input[value='Continue']");
    By identificationTypeDropdown = By.xpath("//select[@name='identificationTypeCC']");
    By clientIDNumberTxt = By.xpath("//input[@name='Identity_NumberCC']");
    By clientReferenceNoTxt = By.xpath("//input[@name='Reference_NumberCC']");

    public void captchaNewApplication() throws Exception {
        TestReporter.LogStep("User capturing the security code");
        driverActions.SwitchToActiveWindow();
        driver.switchTo().frame("main");
        Thread.sleep(2000);
        driverActions.Click(radioButtonNo,"user selected No button");
        Thread.sleep(5000);
    }
    public void captchaExistingApplication(String idNumber, String refNumber,String identificationType) throws Exception {
        TestReporter.LogStep("User capturing the security code for Existing application");
        driverActions.SwitchToActiveWindow();
       driver.switchTo().frame("main");
       Thread.sleep(2000);
        driverActions.Click(radioButtonYes,"User selected Yes button for existing application");
       Thread.sleep(2000);
       // driverActions.Click(radioButtonNo);
        driverActions.SelectDropDown(identificationTypeDropdown,identificationType,"User selected the identificationType :"+identificationType);
        driverActions.SendKeys(clientIDNumberTxt,idNumber,"User has selected idNumber :"+idNumber);
        driverActions.SendKeys(clientReferenceNoTxt,refNumber,"User has selected the refNumber :"+refNumber);
        driverActions.SwitchToActiveWindow();
        Thread.sleep(3000);

    /*  File src= driver.findElement(By.xpath("//img[@alt='captcha']")).getScreenshotAs(OutputType.FILE);
             String path = System.getProperty("user.dir")+"/captchascreenshot/captcha.png";
             FileHandler.copy(src,new File(path));
             ITesseract image=new Tesseract();
             image.setDatapath("WebAutomation/src/Utilities/tessdata");
             image.setLanguage("eng");
             String results = OCR.readText(image);

            // String imageTxt=image.doOCR(new File(path));
            // String finalText =imageTxt.split("below")[0].replaceAll("[^a-z0-9]","");
            // System.out.println("Final captcha value is "+imageTxt);
           //  driverActions.SendKeys(capture,finalText);
            // driverActions.SendKeys(capture,results);

             driverActions.Click(continueButton);


       */ }

}
