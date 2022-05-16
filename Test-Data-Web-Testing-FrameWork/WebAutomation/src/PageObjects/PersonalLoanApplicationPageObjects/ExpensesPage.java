/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpensesPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By expensesPlusIcon = By.cssSelector("span[class = 'expense-plus-icon ng-star-inserted']");
    By accountToDebitDropDown = By.id("id_debitAccount");
    By accountFundingOptions = By.cssSelector("mat-pseudo-checkbox[class='mat-pseudo-checkbox ng-star-inserted']");
    By letsContinueButton = By.cssSelector("button[data-id = 'link_content_personalloan']");


    public void FillInExpenses()  {
        //driverActions.SwitchToPopUpWindow();

        List<WebElement> expensesIconToAdd = driverActions.ElementList(expensesPlusIcon);
        ArrayList<Integer> numbers = GenerateRandomNumbers();

        for (int i = 0; i <numbers.size(); i++) {

            expensesIconToAdd.get(i).click();
            driverActions.SendKeys(buildExpensesFieldXpath(i), "5000");
        }

    }

    public void SelectAccountToDebit(String accountNoToDebit){

        driverActions.SelectItemFromDropDown(accountToDebitDropDown, accountNoToDebit);
    }

    public  void SelectHowYouWillBeFundingTheAccount(){

        List<WebElement> listOfFundingMeans = driverActions.ElementList(accountFundingOptions);
        ArrayList<Integer> numbers = GenerateRandomNumbers();
        for (Integer number : numbers) {

            listOfFundingMeans.get(number).click();
        }
    }

    private By buildExpensesFieldXpath(int index){

        String xpathTemplate = "(//div//input)['%s']";
        String expensesXpath = String.format(xpathTemplate, index+1);
        return By.xpath(expensesXpath);

    }


    private ArrayList<Integer> GenerateRandomNumbers(){

        ArrayList<Integer> numbers = new ArrayList<>();
        Random randomGenerator = new Random();
        while (numbers.size() < 4) {

            int random = randomGenerator .nextInt(17);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
    }

    public void ClickLetsContinueButton() throws Exception {

        driverActions.Click(letsContinueButton);
    }


}
*/