package PageObjects.AolPageObjects;

import Utilities.SeleniumDriver;
import org.openqa.selenium.By;

public class PaymentsPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By recurringPaymentsContainer = By.xpath("//div[normalize-space(text())='Recurring payments']");
    By addRecurringPaymentTab = By.cssSelector("li[class='ui-tabHead ap-tabHead-addRecurringPayment']");
    By readConfirmCheckBox = By.cssSelector("input[name='readConfirm']");
    By continueBankingButton = By.xpath("//div[normalize-space(text())='Continue banking']");
    By paymentsTab = By.xpath("//div[normalize-space(text())='Payments']");

    public void HandlePopUps(){


    }

    public void NavigateToPaymentsTab(){

    }

}
