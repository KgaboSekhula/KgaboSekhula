/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import org.openqa.selenium.By;

public class LoanSetupPage {


    SeleniumDriver driverActions = new SeleniumDriver();
    By loanAmountField = By.id("id_loanAmountInput");
    By loanRangeIncreamentButton = By.cssSelector("div[class='range-slider__action-add']");
    By loanReasonDropDown = By.cssSelector("mat-form-field[appearance='standard']");
    By settleExistingLoanRadioButton = By.cssSelector("label[for='settlementAccountRadioControl0-input']");
    By notSettleExistingLoanRadioButton = By.cssSelector("label[for='settlementAccountRadioControl1-input']");
    By eStatamentsCheckBox = By.cssSelector("label[class='mat-checkbox-layout']");
    By letsContinueButton = By.cssSelector("button[data-id = 'link_content_personalloan']");

    public void SetupLoan(String settleExistingLoanStatus,String loanAmount, String loanDuration, String ReasonForLoan) throws InterruptedException {

        int setDuration = Integer.parseInt(loanDuration) - 12;
        if ((driverActions.IsElementVisible(settleExistingLoanRadioButton) &&driverActions.IsElementVisible(notSettleExistingLoanRadioButton)))
        {
            if (settleExistingLoanStatus.equals("Yes"))
            {
                driverActions.Click(settleExistingLoanRadioButton);
            }
            else{
                driverActions.Click(notSettleExistingLoanRadioButton);
            }
        }

        driverActions.SendKeys(loanAmountField, loanAmount);

        for (int i=0; i< setDuration; i++){
            driverActions.Click(loanRangeIncreamentButton);
        }

        Thread.sleep(2000);
        driverActions.SelectItemFromDropDown(loanReasonDropDown, ReasonForLoan);
        driverActions.Click(letsContinueButton);
    }

}
*/