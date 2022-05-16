package PageObjects.AolPageObjects;

import Utilities.*;
import org.openqa.selenium.By;

//import java.io.FileNotFoundException;


public class AccountsPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    CommonFunctions commonFunctions = new CommonFunctions();

    By bankConfirmationLetterTab = By.xpath("//li[contains(text(), 'Bank confirmation letter')]");
    By viewButton = By.xpath("//div[contains(text(), 'View')]");
    By printButton = By.xpath("//button[@aria-label='Print stamped transaction history. ']");
    By getStampedStmButton = By.xpath("//div[contains(text(), 'Get Stamped Statement')]");
    By popUpDialogBox = By.xpath("//div[@role='alertdialog']");
    By PopUpText = By.xpath("//td[text() = 'Please note: Stamped statements carries an additional cost per statement. ");
    By GetStampedStmButton = By.xpath("//*[@id='g90']/div[1]/div/div/div/div[3]/div[1]/div/div[6]/div/div[3]/button[2]/div/div/div");



    public void ClickAccountTypeLink(String accessAccount) throws Exception {

        By accountType = By.xpath("//div[text()= "+accessAccount+"]");

        driverActions.Click(accountType,"User Clicks on the account wished to get an archive statement for");
        TestReporter.PassScenarioWithScreenShot("Account type screenshots");
    }

    public void ClickAccountBankConfirmationTab() throws Exception {

        Thread.sleep(400);
        driverActions.Click(bankConfirmationLetterTab,"User Clicks on Bank Confirmation Letter tab");
        TestReporter.PassScenarioWithScreenShot("AccountBankConfirmationTab screenshot");    }

    public void ClickViewButton() throws Exception {

        Thread.sleep(400);
        driverActions.Click(viewButton,"User clicks on the View button to view the Confirmation letter");
        TestReporter.PassScenarioWithScreenShot("View button screenshot");
    }

    public void ClickStampedStatementIcon() throws Exception {

      Thread.sleep(400);
       driverActions.Click(printButton,"User Clicks on the stamped statement icon");
        TestReporter.PassScenarioWithScreenShot("stamped statement screenshot");
    }
   public void ConfirmBankLettersPdfUrl(String expectedURLString) throws Exception {

        //reading the PDF URL
       String actualPdfURL = commonFunctions.GetCurrenPageUrl();

       TestReporter.LogStep("The Confirmed Bank Letter PDF URL is:  " +actualPdfURL);

       //verifying if the String Check is contained in actual PDF URL
       boolean result = commonFunctions.UrlVerification(actualPdfURL,expectedURLString );

       if (result) {

           Thread.sleep(500);
           TestReporter.PassScenarioWithScreenShot("The Confirmed Bank Letters is  successfully");

           Thread.sleep(500);
       } else {
           TestReporter.FailScenario("Failed to confirm Bank Letters successfully");
       }
    }

    public String ExpectedUrl(String expectedValue) throws Exception {

        return  String.valueOf(commonFunctions.GetCurrenPageUrl().contains(expectedValue));
    }

    public String ActualPdfUrl() throws Exception {
        return commonFunctions.GetCurrenPageUrl();
    }


    public void SwitchToPopWindow()
    {
        driverActions.SwitchToActiveWindow();
    }

   public void ClickGetStampedStatementButton() throws Exception {
       try {

               if (driverActions.IsElementVisible(popUpDialogBox)) {

                   if (driverActions.IsElementDisplayed(GetStampedStmButton)) {
                       driverActions.Click(GetStampedStmButton,"User Clicks on the Get Stamped Statement button");
                   } else {
                       TestReporter.FailScenario("User failed to click on the Get Stamped Statement button");
                   }
               } else {
                   TestReporter.FailScenario("Dialog box is not visible after clicking on the Stamped Statement Icon");

               }

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
}