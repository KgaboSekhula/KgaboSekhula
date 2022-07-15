package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class YourFinances {
    SeleniumDriver driverActions = new SeleniumDriver();
    By clientEmployementOccupationStatusDropdown = By.cssSelector("select[name='occupationStatus']");
    By clientEmploymentSectorDropdown = By.cssSelector("select[name='employmentSector']");
    By clientEmploymentOccupationCodeDropdown = By.cssSelector("select[name='occupationCode']");
    By clientEmploymentOccupationLevelDropdown = By.cssSelector("select[name='occupationLevel']");

    By clientEmploymentNameText = By.cssSelector("input[name='employersName']");
    By clientEmploymentAddress1Text = By.cssSelector("input[name='employerPhysicalAddressLine1']");
    By clientEmploymentAddress2Text = By.cssSelector("input[name='employerPhysicalAddressSuburb']");
    By clientEmploymentCityText = By.cssSelector("input[name='employerPhysicalAddressCity']");
    By clientEmploymentPostalCodeText = By.cssSelector("input[name='employerPhysicalAddressPostalCode']");
    By clientQualificationRadioButton = By.cssSelector("input[value='Y'][name='postMatricQualificationInd']");
    By highestQualificationButton = By.cssSelector("select[name='qualification']");
    By clientDebitDayDropdown = By.cssSelector("select[name='noOfDependents']");

    ///--Employment------
    By clientTotalIncomeText = By.cssSelector("input[value='0.00'][name='totalIncome']");
   // By clientTotalNetIncomeText = By.cssSelector("input[value='0.00'][name='totalNetIncome']");
    By clientTotalNetIncomeText = By.cssSelector("input[name='totalNetIncome']");

    By clientTotalRepaymentsText = By.cssSelector("input[value='0.00'][name='totalRepayments']");
    By clientTotalExpensesText = By.cssSelector("input[value='0.00'][name='totalExpenses']");
    By clientMaintenanceExpensesText = By.cssSelector("input[value='0.00'][name='maintenanceExpenses']");
    By clientCreditBankNameDropdown = By.cssSelector("select[name='creditBankName']");
    By clientCreditAccountText = By.cssSelector("input[name='creditAccount']");
    By clientAccountTypeDropdown = By.cssSelector("select[name='accountType']");
    By clientSourceOfFundsDropdown = By.cssSelector("select[name='sourceOfFunds']");
    By declaredRadioButton = By.cssSelector("input[value='20']");
    By clientConsentRadioButton = By.cssSelector("input[value='Y'][name='thirdPartyConsent']");
    By clientVerifyIncomeConsenttCheck = By.cssSelector("input[value='Y'][name='verifyIncomeConsent']");
    By declaredInsolventRadioButton = By.cssSelector("input[value='2']");
   // By clientTermsCheck = By.cssSelector("input[value='Y'][name='acceptTerms']");
    By clientTermsCheck  = By.cssSelector("input[type='checkbox'][name='acceptTerms']");
    By clientConfirmInformationCheck = By.cssSelector("input[value='Y'][name='confirmInformation']");
    By ContinueButton = By.cssSelector("input[value='Continue']");
    By referenceNumberMessage = By.xpath("//body[1]/div[2]/form[1]/table[1]/tbody[1]/tr[8]/td[1]");

    public void FinancialInformation(String clientGrossIncome, String clientTotalNetIncome, String clientTotalRepayments, String clientTotalExpenses, String clientMaintenanceExpenses, String clientCreditBankName, String clientCreditAccount, String clientAccountType, String clientSourceOfFunds) throws Exception {

           //boolean SafeToContinue ;
           // if (SafeToContinue) {

            TestReporter.LogStep("User capturing 'Financial Information' section of the '3. Your finance' Page");
            Thread.sleep(2000);

            driverActions.SendKeys(clientTotalIncomeText, clientGrossIncome,"User captured clientGrossIncome :"+clientGrossIncome);
            driverActions.DoubleClick(clientTotalNetIncomeText);
            driverActions.BackSpaceKey();
            driverActions.SendKeys(clientTotalNetIncomeText, clientTotalNetIncome,"User captured clientTotalNetIncome :"+clientTotalNetIncome);
            driverActions.SendKeys(clientTotalRepaymentsText, clientTotalRepayments,"User captured clientTotalRepayments :" +clientTotalRepayments);
            driverActions.SendKeys(clientTotalExpensesText, clientTotalExpenses,"User captured clientTotalExpenses :" +clientTotalExpenses);
            driverActions.SendKeys(clientMaintenanceExpensesText, clientMaintenanceExpenses,"User captured clientMaintenanceExpenses :"+clientMaintenanceExpenses);
            driverActions.SelectDropDown(clientCreditBankNameDropdown, clientCreditBankName,"User has selected clientCreditBankName :"+clientCreditBankName);
            driverActions.SendKeys(clientCreditAccountText, clientCreditAccount,"User captured clientCreditAccount :"+clientCreditAccount);
            driverActions.SelectDropDown(clientAccountTypeDropdown, clientAccountType,"User has selected clientAccountType :"+ clientAccountType);
            driverActions.SelectDropDown(clientSourceOfFundsDropdown, clientSourceOfFunds,"User has selected clientSourceOfFunds :"+clientSourceOfFunds);
            driverActions.Click(declaredRadioButton,"User has selected declare radio button");
            driverActions.Click(clientConsentRadioButton,"User has selected consent radio button");
            driverActions.Click(clientVerifyIncomeConsenttCheck,"User has declared consent on verifying Income Consent Check ");
            driverActions.Click(declaredInsolventRadioButton,"User has selected insolvent radio button");
            driverActions.Click(clientTermsCheck,"User accepted terms amd conditions");
            driverActions.Click(clientConfirmInformationCheck,"User has confirmed information checking");
            driverActions.Click(ContinueButton,"User has clicked continued button ");
            Thread.sleep(2000);
         //   SafeToContinue = true;
         //   if(driverActions.IsElementVisible(ContinueButton));
         //       {
         //           SafeToContinue = false;
         //           TestReporter.FailScenario("Client could not continue to the next section with the Credit card application process in this Section");

         //       }
             //SafeToContinue = false;
        //    TestReporter.FailScenario("Client could not continue to the next section with the Credit card application process in this Section");
        // }
            //} else {
            //    //discontinue the application if this section could not be completed
            //    SafeToContinue = false;
            //    TestReporter.FailScenario("Client could not continue to the next section with the Credit card application process in this Section");
            // }
        //}
       // return SafeToContinue;
    }

    public String EmploymentInformation(String clientEmploymentOccupationStatus,String clientEmploymentSector,String clientEmploymentOccupationCode,String clientEmploymentOccupationLevel,String clientEmploymentName,String clientEmploymentAddress1,String clientEmploymentAddress2,String clientEmploymentCity, String clientEmploymentPostalCode , String clientDependancy) throws Exception {

            // if (SafeToContinue) {
            TestReporter.LogStep("The User is capturing the 'Employment Information' in the '2.Getting started' page of the application process");

            if (driverActions.IsElementVisible(referenceNumberMessage)) {

                String refNumber = driverActions.GetText(referenceNumberMessage);
                String[] splitRefNumber = refNumber.split(" ");
                String CreditCardRefNumber = splitRefNumber[7];
                TestReporter.LogStep("The actual reference Number is " + CreditCardRefNumber);
                Thread.sleep(2000);

                driverActions.SelectDropDown(clientEmployementOccupationStatusDropdown, clientEmploymentOccupationStatus,"User has selected clientEmploymentOccupationStatus :"+clientEmploymentOccupationStatus);
                driverActions.WaitForElementToBeClickable(clientEmploymentSectorDropdown);
                driverActions.SelectDropDown(clientEmploymentSectorDropdown, clientEmploymentSector,"User has selected the clientEmploymentSector :"+clientEmploymentSector);
                driverActions.SelectDropDown(clientEmploymentOccupationCodeDropdown, clientEmploymentOccupationCode,"User has selected the clientEmploymentOccupationCode :"+clientEmploymentOccupationCode);
                driverActions.SelectDropDown(clientEmploymentOccupationLevelDropdown, clientEmploymentOccupationLevel,"User has selected clientEmploymentOccupationLevel :"+clientEmploymentOccupationLevel);
                driverActions.SendKeys(clientEmploymentNameText, clientEmploymentName, "User captured clientEmploymentName :"+clientEmploymentName);
                driverActions.SendKeys(clientEmploymentAddress1Text, clientEmploymentAddress1, "User captured clientEmploymentAddress1 :"+clientEmploymentAddress1);
                driverActions.SendKeys(clientEmploymentAddress2Text, clientEmploymentAddress2, "User captured clientEmploymentAddress2 :"+clientEmploymentAddress2);
                driverActions.SendKeys(clientEmploymentCityText, clientEmploymentCity, "User captured clientEmploymentName :"+clientEmploymentCity);
                driverActions.SendKeys(clientEmploymentPostalCodeText, clientEmploymentPostalCode, "User captured clientEmploymentPostalCode :"+clientEmploymentPostalCode);
                driverActions.Click(clientQualificationRadioButton, "User declared qualifications");
                driverActions.SelectDropDown(highestQualificationButton, "Masters","User has selected the client highest Qualification ");
                driverActions.SelectDropDown(clientDebitDayDropdown, clientDependancy,"User has selected clientDependancy :"+clientDependancy);
                return CreditCardRefNumber;
            }

            return null;
               // } else {
                    //discontinue the application if this section could not be completed
                 //   SafeToContinue = false;
                 //   TestReporter.FailScenario("Client could not continue to the next section with the Credit card application process in landing solution tower Section");
               // }
        //   }

       // return SafeToContinue;
    }
}
