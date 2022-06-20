package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class BeforeYouStart {
    SeleniumDriver driverActions = new SeleniumDriver();
    By declaredInsolventButton = By.cssSelector("input[value='N'][name='declaredInsolventUnderCouncelling']");
    By DebtCounsellingButton = By.cssSelector("input[value='N'][name='prevInsolventDebtCounselling']");
    By BusinessRescueQuestionButton = By.cssSelector("input[value='N'][name='appliedBusinessRescueQuestion']");
    By DebitOrderCheckButton = By.cssSelector("input[value='Y'][name='agreeDebitOrderCheck']");
    By continueButton = By.cssSelector("input[value='Continue']");
    By employmentInformation = By.xpath("//span[contains(text(),'Employment Information')]");

    public void DeclarationInformation() throws Exception {
        TestReporter.LogStep("User captures the declaration on '1. Before You Start' page");
        driverActions.Click(declaredInsolventButton,"User clicked declaredInsolventButton");
        driverActions.Click(DebtCounsellingButton,"User clicked DebtCounsellingButton");
        driverActions.Click(BusinessRescueQuestionButton,"User clicked BusinessRescueQuestionButton");
        driverActions.Click(DebitOrderCheckButton,"User clicked DebitOrderCheckButton");
        driverActions.Click(continueButton,"User clicked continueButton");

    }

    public boolean CreditCardApplicationStageCheck(String applicationStage) {

            boolean applicationStageCheckFlag = true;
        System.out.println("I've reached the CreditCardApplicationStageCheck method stage");

        //Client application is at '3. Your 3. Your finances' application Stage
            if (driverActions.PageTextPresent(applicationStage).contains("finance")) {
                System.out.println("I've reached the PageTextPresent method check");

                if (driverActions.IsElementVisible(employmentInformation)) {
                    TestReporter.LogStep("Client application Stage '3. Your finances' confirmed successfully");
                    System.out.println("Client application resumes from '3. Your finances' application Stage");
                }
            }
            //Client application is at '4. About You' application Stage
            else if (driverActions.PageTextPresent(applicationStage).contains("4. About You")) {
                TestReporter.LogStep("Client application Stage '4. About You' confirmed successfully");
                System.out.println("Client application resumes from '4. About You' application Stage");
                }
                //Client application is at '5. Choose your card' application Stage
                else if (driverActions.PageTextPresent(applicationStage).contains("5. Choose your card")) {
                    TestReporter.LogStep("Client application Stage '5. Choose your card' confirmed successfully");
                    System.out.println("Client application resumes from '5. Choose your card' application Stage");
                    }
                    //Client application is at '6. Set-up your card' application Stage
                    else if (driverActions.PageTextPresent(applicationStage).contains("6. Set-up your card")) {
                        TestReporter.LogStep("Client application Stage '6. Set-up your card' confirmed successfully");
                        System.out.println("Client application resumes from '6. Set-up your card' application Stage");
                        }
                        else{
                            TestReporter.LogStep("No application Stage was confirmed");
                            applicationStageCheckFlag = false;
                            }

            return applicationStageCheckFlag;
    }

      //  return applicationStageCheckFlag;
    //}

    public String DetermineApplicationStage(String[] applicationStage) {
            {
                //determine the application stage of the client to resume from
                int y;
                for (y = 0; y <= applicationStage.length; y++) {

                    System.out.println("I've reached the Determine stage");
                    if (CreditCardApplicationStageCheck(applicationStage[y])) {
                        System.out.println("I've reached the Determine stage loop");

                        TestReporter.LogStep("Client application will resume from " + applicationStage[y] + " application Stage");
                        //applicationResumeStage = true;
                        break;
                    } else {
                        System.out.println("I've reached the Determine stage loo Else condition");

                        TestReporter.LogStep("Application stage could not be determined for this client,Please check in the CFTS system");
                        // applicationResumeStage = false;
                    }
                }
                return applicationStage[y];
            }
        }
}