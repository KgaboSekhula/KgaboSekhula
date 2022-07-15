package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class ThankYou {

    SeleniumDriver driverActions = new SeleniumDriver();
    By applicationApprovalText = By.xpath("/html/body/div[2]/form/table/tbody/tr[5]/td/table/tbody/tr[3]/td");
    By doneButton = By.cssSelector("input[name='button_done']");

    public String ConfirmCreditCardApproval() throws Exception {

            if (driverActions.IsElementVisible(applicationApprovalText)){

                TestReporter.LogStep("User completing 'Confirm Credit Card Approval' section of the '7.Thank You' Page");
                String approvalMessage = driverActions.GetText(applicationApprovalText);
                TestReporter.LogStep("Application completion message is that :" + approvalMessage);
                String creditCardReferenceNumber = approvalMessage.replaceAll("Your application has been submitted and your reference number is ", "");
                TestReporter.PassScenarioWithScreenShot("user has successfully completed the client credit application");
                if (driverActions.IsElementVisible(doneButton)) {
                    driverActions.Click(doneButton,"User has clicked done button to confirm the approval of the Credit application");
                    return creditCardReferenceNumber;
                }
            else{
                 TestReporter.FailScenario("user has not successfully completed the client credit application");
                }
            }
           return null;
    }

}
