package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class ChooseYourCard {
    SeleniumDriver driverActions = new SeleniumDriver();

    By PlatinumCardRadioButton = By.cssSelector("input[value='PLT']");
    By GoldCardRadioButton = By.cssSelector("input[value='GLD']");
    By ElectronicRadioButton = By.cssSelector("input[value='ELT']");
    By continueButton = By.cssSelector("input[name='button_processContinue'");


    public void ChoosingCard() throws Exception {

        TestReporter.LogStep("User completing 'Choose Card' section of the '5.Choose card' Page");

        driverActions.Click(GoldCardRadioButton,"User has selected the Gold Credit credit for the client");
        driverActions.Click(continueButton,"User has clicked the Continue button");
        Thread.sleep(3000);
    }
}
