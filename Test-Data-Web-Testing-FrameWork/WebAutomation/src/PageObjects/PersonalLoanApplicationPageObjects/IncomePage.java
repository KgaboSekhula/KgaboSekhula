/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IncomePage {

    //The income page includes the page expenses page as the functions are related
    SeleniumDriver driverActions = new SeleniumDriver();
    By grossIncomeField = By.cssSelector("input[id ='id_grossMonthlyIncome']");
    By netSalaryMonthOneField = By.cssSelector("input[id ='id_netSalaryMonth1']");
    By netSalaryMonthTwoField = By.cssSelector("input[id ='id_netSalaryMonth2']");
    By netSalaryMonthThreeField = By.cssSelector("input[id ='id_netSalaryMonth3']");
    By rentalIncomeField = By.cssSelector("input[id ='id_rentalIncome']");
    By otherIncomeField = By.cssSelector("input[id ='id_otherIncome']");
    By yesIAgreeWithSalaryInformationRadioButton = By.xpath("//span[normalize-space(text())='Yes, I agree']");
    By noIDontAgreeWithSalaryInformationRadioButton = By.xpath("//span[normalize-space(text())=\"No, I'd like to update\"]");
    By yesDebtDeductionRadioButton = By.cssSelector("label[for='debtDeductRadio0-input']");
    By noDebtDeductionRadioButton = By.cssSelector("label[for='debtDeductRadio1-input']");
    By salaryDayPicker = By.cssSelector("label[id = 'dayPicker_label']");
    By expensesPlusIcon = By.cssSelector("span[class = 'expense-plus-icon ng-star-inserted']");
    By accountToDebitDropDown = By.id("id_debitAccount");
    By accountFundingOptions = By.cssSelector("mat-pseudo-checkbox[class='mat-pseudo-checkbox ng-star-inserted']");
    By letsContinueButton = By.cssSelector("button[data-id = 'link_content_personalloan']");
    By processingLoanDialog = By.id("mat-dialog-0");
    private boolean ifExpensesAreFilled;


    public void FillFinancesInformation(String grossIncome, String netSalaryMonth1, String netSalaryMonth2, String netSalaryMonth3, String rentalIncome, String otherIncome, String debtDeductionStatus, String salaryDay ) throws InterruptedException {
        //Method handles the financial information of the particular user if and if only the financial information is not pre-populated
       if (driverActions.IsElementVisible(netSalaryMonthOneField)){
           TestReporter.LogStep("User types in the salary information as Gross salary: "+grossIncome+" Net Salary Month One: "+netSalaryMonth1+" Net Salary Month Two: "+netSalaryMonth2
                   +" Net Salary Month Three: "+netSalaryMonth3 +" Rental income as: "+rentalIncome +" Other income : "+otherIncome);
            driverActions.SendKeys(grossIncomeField, grossIncome);
            driverActions.SendKeys(netSalaryMonthOneField, netSalaryMonth1);
            driverActions.SendKeys(netSalaryMonthTwoField, netSalaryMonth2);
            driverActions.SendKeys(netSalaryMonthThreeField, netSalaryMonth3);
            driverActions.SendKeys(rentalIncomeField, rentalIncome);
            driverActions.SendKeys(otherIncomeField, otherIncome);
            PickSalaryDay(salaryDay);


            if (debtDeductionStatus.equals("Yes")){

                TestReporter.LogStep("User clicks click on the Yes radio button for debt deduction on salary");
                driverActions.Click(yesDebtDeductionRadioButton);
            }
            else {
                TestReporter.LogStep("User clicks click on the No radio button for debt deduction on salary");
                driverActions.Click(noDebtDeductionRadioButton);
            }
            TestReporter.LogStep("User clicks on the lets continue buttton");
            driverActions.Click(letsContinueButton);
            FillInExpenses();
          //  SelectAccountType(accountToDebitDropDown);??
          // SelectHowYouWillBeFundingTheAccount();
         //  PickSalaryDay(salaryDay);

        }
    }

    private void PickSalaryDay(String salaryDay){

        //Method used to pick the salary day
        TestReporter.LogStep("User selects salary day as: "+salaryDay);
        driverActions.Click(salaryDayPicker);
        String template = "//div[normalize-space(text())='%s']";
        String dropDownItemXpath = String.format(template, salaryDay);
        By dateLocator = By.xpath(dropDownItemXpath);
        driverActions.Click(dateLocator);

    }

    public void FinancesSummary(String salaryDay) throws InterruptedException {

        //This method will only be used if the expenses' page is shown(some users already have the expenses filled out)
        if (!ifExpensesAreFilled) {

            driverActions.Click(yesIAgreeWithSalaryInformationRadioButton);
            //Test steps are logged on the particular method
            SelectAccountType(accountToDebitDropDown);
            SelectHowYouWillBeFundingTheAccount();
            PickSalaryDay(salaryDay);
            TestReporter.LogStep("User clicks on the lets continue button on the finances page");
            driverActions.Click(letsContinueButton);
        }
    }

    private void SelectAccountType(By locator) throws InterruptedException {
        //string do build generic xpath that will be used to select the account we will use, either be it cheque or savings
        String template = "//span[contains(text(),'%s')]";
        String savings = "Savings Account";
        String cheque = "Cheque Account";
        String savingsXpath = String.format(template, savings);
        String chequeXpath = String.format(template, cheque);
        driverActions.WaitForElementToBeClickable(locator);
        Thread.sleep(2000);
        driverActions.Click(locator);
        TestReporter.LogStep("User enters R100 as an expense");
        if (driverActions.IsElementVisible(By.xpath(savingsXpath)))
        {
            TestReporter.LogStep("User selects the account we will be debiting from as: "+ savings);
            driverActions.Click(By.xpath(savingsXpath));
        }
        else {
            TestReporter.LogStep("User selects the account we will be debiting from as: "+ cheque);
            driverActions.Click(By.xpath(chequeXpath));
        }
    }



    public void FillInExpenses() throws InterruptedException {

      // This particular method handles the  expenses' page so that we can randomly select which  expense we can select
        //This method is subjected to change after consulting with the stakeholders
        Thread.sleep(5000);
        if (driverActions.IsElementVisible(expensesPlusIcon) && !ifExpensesAreFilled)
        {
            List<WebElement> expensesIconToAdd = driverActions.ElementList(expensesPlusIcon);
            ArrayList<Integer> numbers = GenerateRandomNumbers();

            //For now, we only enter one expenses as to make sure that the test passes
            TestReporter.LogStep("User enters R100 as an expense");
            for (int i = 0; i <1; i++) {

                expensesIconToAdd.get(i).click();
                driverActions.SendKeys(buildExpensesFieldXpath(), "100");
            }
            SelectAccountType(accountToDebitDropDown);
            SelectHowYouWillBeFundingTheAccount();
            driverActions.Click(letsContinueButton);
            ifExpensesAreFilled =true;
        }
    }

    private   void SelectHowYouWillBeFundingTheAccount(){

        //For Now We will on click on the salary option until further consultation
        //mat-dialog-0
        TestReporter.LogStep("User selects that the account will be funded using salary");
        List<WebElement> listOfFundingMeans = driverActions.ElementList(accountFundingOptions);
        listOfFundingMeans.get(0).click();

    }

    private By buildExpensesFieldXpath(){


        String xpathTemplate = "(//input[@type='text'])[1]";
        return By.xpath(xpathTemplate);

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





}
*/