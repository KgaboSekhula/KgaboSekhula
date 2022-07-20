package PageObjects.CreditCardPageObject;

import Utilities.GoogleImageReader;
import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

import org.bytedeco.javacpp.*;
import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;

import static Utilities.SeleniumDriver.driver;

public class CaptchaPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By radioButtonYes = By.cssSelector("input[value='Y'][name='savedCCApplicationyn']");
    By radioButtonNo = By.cssSelector("input[value='N'][name='savedCCApplicationyn']");
    By captchaImg = By.xpath("//img[@alt='captcha']");
    By capture = By.cssSelector("input[name='captchaInput']");
    By continueButton = By.cssSelector("input[name='button_processCCApplicationInfo']");
    By identificationTypeDropdown = By.xpath("//select[@name='identificationTypeCC']");
    By clientIDNumberTxt = By.xpath("//input[@name='Identity_NumberCC']");
    By clientReferenceNoTxt = By.xpath("//input[@name='Reference_NumberCC']");
    By errorDialogBox = By.xpath("//*[@id='errorCNTR']");

    public void captchaNewApplication() throws Exception {
        TestReporter.LogStep("User capturing the security code");
        for(int i =0 ; i<6; i++)
        {
            //For loop will try 6 times before failing test if QR code is not being captured
            driverActions.SwitchToActiveWindow();
            driver.switchTo().frame("main");
            Thread.sleep(2000);
            driverActions.Click(radioButtonNo,"user selected No button");
            driverActions.WaitForElementToBeClickable(captchaImg);
            driverActions.DownloadImageFromWebPage(captchaImg);
            Thread.sleep(5000);
            String captureText = GoogleImageReader.ReadImageText("CaptchImage/captch.png");
            captureText = captureText.replaceAll("\\s+","");
            driverActions.SendKeys(capture,captureText, "Enter Capture Details");
            driverActions.Click(continueButton,"User Clicks on the continue button");
            if (!driverActions.IsElementVisible(errorDialogBox)){
                break;
            }
            driverActions.RefreshPage();
        }


    }

    public String ReadTextFromImage(){
        BytePointer outText;
        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(null, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }

        // Open input image with leptonica library
        PIX image = pixRead( "CC/CaptchImage/captch1.tiff");
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        System.out.println("OCR output:\n" + outText.getString());

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
        return  outText.getString();
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
