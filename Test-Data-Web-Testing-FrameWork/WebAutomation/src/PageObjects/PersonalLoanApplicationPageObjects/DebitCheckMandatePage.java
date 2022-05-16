/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import org.openqa.selenium.By;

public class DebitCheckMandatePage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By approveMandateRadioButton = By.xpath("//span[normalize-space(text())='Approve mandate']");
    By saveThisQuoteRadioButton = By.xpath("//span[normalize-space(text())='Save this quote **']");
    By noThanksRadioButton = By.xpath("//span[normalize-space(text())='No thanks']");
    By iHaveReadTheTermsAndConditionsButton = By.id("link_content_PL");
    By receiveMailOnPostRadioButton = By.xpath("//span[normalize-space(text())='Registered mail to my postal address']");
    By receiveMailOnPhysicalAddressRadioButton = By.xpath("//span[normalize-space(text())='An adult at my physical address']");

    By letsContinueButton = By.cssSelector("button[data-id = 'link_content_personalloan']");


    public void  ApproveDebitcheckMandate(){
        driverActions.SwitchToActiveWindow();
        driverActions.Click(approveMandateRadioButton);
        driverActions.Click(letsContinueButton);
    }
}
*/