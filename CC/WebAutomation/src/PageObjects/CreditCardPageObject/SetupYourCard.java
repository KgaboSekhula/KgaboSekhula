package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class SetupYourCard {

    SeleniumDriver driverActions = new SeleniumDriver();
    By acceptCreditLimitRadioButton = By.cssSelector("input[name='acceptLimit'][value='Y']");
    By clientBankAccountRadioButton = By.cssSelector("input[name='absaAccount'][value='4']");
    By clientAccountTypeDropDown = By.cssSelector("select[name='accountType']");
    By clientAccountNumberField = By.cssSelector("input[name='debitAcccount']");
    By clientAccountHolderField = By.cssSelector("input[name='accountName']");
    By clientDebitDayDropDown = By.cssSelector("select[name='debitDay']");
    By clientDebitOrderAmountRadioButton = By.cssSelector("input[name='debit_order_amount_option'][value='F']");
    By clientChosenCardElement = By.cssSelector("input[name='selectedCard']");
    By clientReceiveStatementRadioButton = By.cssSelector("input[name='statementPreference'][value='NOR']");
    By continueButton = By.cssSelector("input[name='button_processContinue']");
    By acceptQuoteButton = By.cssSelector("input[name='button_processAccept']");

    public void CreditCardOptions() throws Exception {

        if (driverActions.IsElementVisible(clientChosenCardElement)) {

            TestReporter.LogStep("User completing 'CreditCard Options' section of the '6.Set-up Your Card' Page");

            String ChosenCreditCard = driverActions.GetElementValue(clientChosenCardElement);
            System.out.println("Chosen credit card is " + ChosenCreditCard);
            TestReporter.LogStep("The Client hsa chosen the " +ChosenCreditCard+ "credit card");
            driverActions.Click(acceptCreditLimitRadioButton,"User has checked the Credit Limit Radio Button");
        }
    }

    public void paymentSettings(String accountType, String accountNumber,String clientAccountHolder,String debitDay) throws Exception {

        TestReporter.LogStep("User completing 'Payment Settings' section of the '6.Set-up Your Card' Page");

        driverActions.Click(clientBankAccountRadioButton,"User has selected BankAccount Radio Button");
        driverActions.SelectDropDown(clientAccountTypeDropDown,accountType,"User has selected the accountType :"+accountType);
        driverActions.SendKeys(clientAccountNumberField,accountNumber,"User has Captured the accountNumber :"+accountNumber);
        driverActions.SendKeys(clientAccountHolderField,clientAccountHolder,"User has captured the clientAccountHolder "+clientAccountHolder);
        driverActions.SelectDropDown(clientDebitDayDropDown,debitDay,"User has selected the debitDay :"+debitDay);
        driverActions.Click(clientDebitOrderAmountRadioButton,"User has selected the Debit Order Amount RadioButton");
    }

    public void StatementDelivery() throws Exception {

        TestReporter.LogStep("User completing 'Statement Delivery' section of the '6.Set-up Your Card' Page");

        driverActions.Click(clientReceiveStatementRadioButton,"User has selected the Receive Statement Radio Button");
        driverActions.Click(continueButton,"User has clicked the continue button");
        Thread.sleep(3000);
    }
    public void QuotationDetails() throws Exception {

           if(driverActions.IsElementVisible(acceptQuoteButton)){
               TestReporter.LogStep("User completing 'Quotation Details' section of the '6.Set-up Your Card' Page");

               TestReporter.PassScenarioWithScreenShot("Quotation screenshot before accepting the credit card quotation");
               driverActions.WindowScroll();
               TestReporter.PassScenarioWithScreenShot("Quotation screenshot before accepting the credit card quotation");
               System.out.println("Accept quote button is available for customer to accept credit card quotation");
               driverActions.Click(acceptQuoteButton,"User has clicked the acceptQuoteButton");
               Thread.sleep(3000);

           }
    }
}
