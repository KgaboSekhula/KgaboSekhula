/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import org.openqa.selenium.By;

public class PhoneixSimulatorPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By applicationDropDown = By.id("application");
    By submitButton = By.xpath("//button[contains(text(), 'Submit')]");
    By productDropDown = By.id("products");

    public void SelectProduct(String application, String product) {


        driverActions.SelectDropDown(applicationDropDown,application);
        if (driverActions.IsElementVisible(productDropDown)){
            driverActions.SelectDropDown(productDropDown,product);
                    }

        driverActions.Click(submitButton);


    }
}
*/