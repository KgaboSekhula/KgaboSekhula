/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import org.openqa.selenium.By;

public class LetsGetStartedPage  {

    SeleniumDriver driverActions = new SeleniumDriver();
    By newCustomerRadioButton = By.id("makeDecision0");
    By absaCustomerOnlineBankingRadioButton = By.id("makeDecision1");
    By absaCustomerNoOnlineBankingRadioButton = By.id("makeDecision2");
    By letsContinueButton = By.xpath("//span[contains(text(), 'continue')]");

    public void SelectAbsaCustomerWithOnlineBanking() throws InterruptedException {


        driverActions.Click(absaCustomerOnlineBankingRadioButton);
        driverActions.Click(letsContinueButton);
        Thread.sleep(2000);
    }

    public void SelectAbsaCustomerNoOnlineBanking() {

        driverActions.Click(absaCustomerNoOnlineBankingRadioButton);
        driverActions.Click(letsContinueButton);
    }

    public void SelectNewCustomer() throws InterruptedException {

        driverActions.Click(newCustomerRadioButton);
        driverActions.Click(letsContinueButton);
    }
}
*/