/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.awt.*;

public class PersonalLoanOffer {

    SeleniumDriver driverActions = new SeleniumDriver();
    By yesIAcceptRadioButton = By.xpath("//span[normalize-space(text())='Yes, I accept*']");
    By saveThisQuoteRadioButton = By.xpath("//span[normalize-space(text())='Save this quote **']");
    By noThanksRadioButton = By.xpath("//span[normalize-space(text())='No thanks']");
    By iHaveReadTheTermsAndConditionsButton = By.id("link_content_PL");
    By receiveMailOnPostRadioButton = By.xpath("//span[normalize-space(text())='Registered mail to my postal address']");
    By receiveMailOnPhysicalAddressRadioButton = By.xpath("//span[normalize-space(text())='An adult at my physical address']");
    By pendingApprovalNoteContainer = By.xpath("//div[contains(text(),'pending approval')]");
    By termsAndConditionPoPup = By.cssSelector("//span[normalize-space(text())='Agreement about the consequences of default.']");
    By letsContinueButton = By.cssSelector("button[data-id = 'link_content_personalloan']");


    public void  AcceptLoanOffer() throws Exception {

        driverActions.SwitchToActiveWindow();
        if (driverActions.IsElementVisible(pendingApprovalNoteContainer))
        {
            TestReporter.FailScenario("The Personal Loan is Pending as approval, Could not be successfully processed");
            Assert.fail();
        }
        TestReporter.LogStep("User accepts the loan offer by clicking on the Yes radio button");
        driverActions.Click(yesIAcceptRadioButton);
        driverActions.ScrollToBottom(termsAndConditionPoPup);
        TestReporter.LogStep("User accepts the terms and conditions with regard to the loan");
        driverActions.Click(iHaveReadTheTermsAndConditionsButton);
        TestReporter.LogStep("User selects option to receive mail by post");
        driverActions.Click(receiveMailOnPostRadioButton);
    }

    public void ClickLetsContinueButton(){
        TestReporter.LogStep("User clicks on the  lets continue button on the Personal loan offer page");
        driverActions.Click(letsContinueButton);
    }






}
*/