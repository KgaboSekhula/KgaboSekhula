/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class OnlineBankingHomePage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By wantSimSwapButton = By.xpath("//div[contains(text(), 'I want SIM Swap protection')]");
    By dontWantSimSwapButton = By.xpath("//div[contains(text(), \"I don't want SIM Swap protection\")]");
    By readConfirmCheckBox = By.name("readConfirm");
    By continueButton = By.xpath("//div[contains(text(),'Continue banking')]");
    By popUpDialogBox = By.cssSelector("div[role='alertdialog']");
    By applyTab = By.cssSelector("a[href='#apply']");

    public void HandlePopUps() throws Exception {

        TestReporter.LogStep( "Pop ups are being handled so that the user can continue to the landing page.");
        while (true){

            if (driverActions.IsElementVisible(popUpDialogBox) ){

                if(driverActions.IsElementVisible(dontWantSimSwapButton)){

                    driverActions.Click(dontWantSimSwapButton);
                }
                else if(driverActions.IsElementVisible(wantSimSwapButton)){

                    driverActions.Click(readConfirmCheckBox);
                    driverActions.Click(continueButton);
                }
            }
            else {
                break;
            }
        }
    }

    public void NavigateToApplyPage() throws Exception {
        TestReporter.LogStep( "User clicks on Apply tab.");
        driverActions.Click(applyTab);
    }
}
*/