/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class ApplyPage  {



    SeleniumDriver driverActions = new SeleniumDriver();
    By landingSolutions = By.xpath("//*[@id=\"portal\"]/div[25]/div/div[1]/div[1]");
    By applyIcon2 = By.xpath("//*[@id=\"g15\"]/div[1]/div/div/div/table/tbody/tr[1]/td[3]/div/button");
    By applyIcon = By.xpath("//*[@id=\"g15\"]/div[1]/div/div/div/table/tbody/tr[2]/td[2]/div/div/div/table/tbody/tr[1]/td[7]/div/button[1]");
    By continueButton = By.cssSelector("button[aria-label='Continue. ']");
    By continueButton1 = By.xpath("(//*[@id=\"modalSwitchToDSP\"]/div[3]/button[2])[2]");

    public void ApplyForLoan() throws Exception {

        Thread.sleep(10000);
        TestReporter.LogStep("User clicks on the landing solution tower");
        driverActions.Click(landingSolutions);
        TestReporter.LogStep("User clicks on the apply icon as to navigate to the next page");
        driverActions.Click(applyIcon2);
        driverActions.Click(applyIcon);
        driverActions.Click(continueButton);
        driverActions.Click(continueButton1);
        }
}
*/