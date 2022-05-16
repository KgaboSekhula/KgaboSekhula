/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;

public class PrivacyDisclaimerPage {


    ExtentTest test;
    SeleniumDriver driverActions = new SeleniumDriver();
    By letsContinueButton = By.cssSelector("button[data-id='link_content_personalloan']");
    By iAgreeWithTheAboveCheckBox = By.xpath("//span[contains(text(), ' I agree to the above ')]");

    public void AcceptPrivacyDisclaimer() {

        driverActions.SwitchToActiveWindow();
        driverActions.Click(iAgreeWithTheAboveCheckBox);
        driverActions.Click(letsContinueButton);
    }
}*/