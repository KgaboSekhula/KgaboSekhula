package PageObjects.AolPageObjects;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {


    SeleniumDriver driverActions = new SeleniumDriver();
    By accountNoField = By.id("j_username");
    By accountPinField = By.id("j_pin");
    By nextButton = By.xpath("//div[contains(text(),'Next')]");
    By passwordBlocks = By.xpath("//input[contains(@class, 'pf pf2')]");
    By logonButton = By.cssSelector("button[aria-label = 'Logon']");


    public void Login(String accessAccount) throws Exception {

        driverActions.SwitchToActiveWindow();

        driverActions.SendKeys(accountNoField,accessAccount, "Enter Account number: "+accessAccount);
        driverActions.SendKeys(accountPinField,AccountPin(accessAccount), "Enter Account pin ");
        driverActions.WaitForElementToBeClickable(nextButton);
        Thread.sleep(1000);
        driverActions.Click(nextButton, "User clicks on the next button in the login page");
    }
    private String AccountPin(String accessAccount)
    {
        //The Logic below gets the last 5 digits of an account numbers, will be used as the Pin
        if (accessAccount.length() > 5)
        {
            return accessAccount.substring(accessAccount.length() - 5);
        }
        else
        {
            return accessAccount;
        }
    }



    public void EnterPassword() throws Exception {

        //Password puzzle reads as password1
        List<WebElement> elements = (List<WebElement>) driverActions.ElementList(passwordBlocks);
        for (int i = 0; i < elements.size(); i++) {
            String value = elements.get(i).getAttribute("num");
            if ("1".equals(value)) {
                elements.get(i).sendKeys("p");
            } else if ("2".equals(value)) {
                elements.get(i).sendKeys("a");
            } else if ("3".equals(value)) {
                elements.get(i).sendKeys("s");
            } else if ("4".equals(value)) {
                elements.get(i).sendKeys("s");
            } else if ("5".equals(value)) {
                elements.get(i).sendKeys("w");
            } else if ("6".equals(value)) {
                elements.get(i).sendKeys("o");
            } else if ("7".equals(value)) {
                elements.get(i).sendKeys("r");
            } else if ("8".equals(value)) {
                elements.get(i).sendKeys("d");
            } else if ("9".equals(value)) {
                elements.get(i).sendKeys("1");
            } else {
                throw new Error("no value found");
            }
        }
        driverActions.Click(logonButton, "User clicks on the logon button after entering password puzzle");
        TestReporter.PassStep( "User Successfully solves the password puzzle");

    }


}