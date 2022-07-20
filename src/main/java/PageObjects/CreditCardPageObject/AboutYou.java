package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class AboutYou {

    SeleniumDriver driverActions = new SeleniumDriver();
    By clientNationalityDropdown = By.cssSelector("select[name='nationality']");
    By clientMaritalStatusContractGroupDropdown = By.cssSelector("select[name='maritalStatusContractGroup']");
    By clientLanguageCorrespondenceDropdown = By.cssSelector("select[name='languageCorrespondence']");
    By clientResidentialStatusDropdown = By.cssSelector("select[name='residentialStatus']");
    By clientHomeLanguageDropDown = By.cssSelector("select[name='languageHome']");
    By clientRadioButton = By.cssSelector("input[name='postalInd']");
    By nextOfKinFirstName = By.cssSelector("input[name='kinFirstNames']");
    By nextOfKinSurname = By.cssSelector("input[name='kinClientname']");
    By nextOfKinRelationship = By.cssSelector("select[name='relationship']");
    By nextOfKinCellPhone = By.cssSelector("input[name='kinCellNo']");
    By nextOfKinEmail = By.cssSelector("input[name='kinEMailAddress']");
    By ContinueButton = By.cssSelector("input[name='button_processContinue']");


    public void PersonalDetails(String clientNationality, String clientMaritalStatusContractGroup, String homeLanguage, String clientLanguageCorrespondence) throws Exception {

        //  if (SafeToContinue) {
        TestReporter.LogStep("User capturing 'Personal Details' section of the '4.About You' Page");

        // if (driverActions.IsElementVisible(clientNationalityDropdown)) {
        driverActions.WindowScroll();
        driverActions.SelectDropDown(clientNationalityDropdown, clientNationality,"User has selected clientNationality :"+clientNationality);
        driverActions.SelectDropDown(clientMaritalStatusContractGroupDropdown, clientMaritalStatusContractGroup,"User has selected clientMaritalStatusContractGroup :"+clientMaritalStatusContractGroup);
        driverActions.SelectDropDown(clientHomeLanguageDropDown, homeLanguage,"User has selected homeLanguage :"+homeLanguage);
        driverActions.SelectDropDown(clientLanguageCorrespondenceDropdown, clientLanguageCorrespondence,"User has selected clientLanguageCorrespondence :"+clientLanguageCorrespondence);
        Thread.sleep(2000);
        //}

        //  if (driverActions.IsElementVisible(clientRadioButton)) {
        //      SafeToContinue = false;
        //      TestReporter.FailScenario("Client could not continue to the next section with the Credit card application process");
        // } else {
        // SafeToContinue = true;
        //TestReporter.LogStep("User has successfully Completed the '2.Getting started' Section Capture section of the Credit card application process");
        //  }
        //  }
        //  return SafeToContinue;
    }

    public void AddressInformation(String clientResidentialStatus) throws Exception {

        TestReporter.LogStep("User capturing 'Address Information' section of the '4.About You' Page");

        driverActions.SelectDropDown(clientResidentialStatusDropdown, clientResidentialStatus,"User has selected clientResidentialStatus :"+clientResidentialStatus);
        driverActions.Click(clientRadioButton,"User has successfully selected Post radio button");
        driverActions.WindowScroll();

    }

    public void NextOfKinInformation(String firstName,String Surname,String relationship,String nextOfKinCellPhoneNumber,String nextOfKinEmailAddress) throws Exception {

            TestReporter.LogStep("User completing 'Next Of Kin Information' section of the '4 About You' Page");

            driverActions.WindowScroll();
            driverActions.WindowScroll();
            driverActions.WindowScroll();
            driverActions.SendKeys(nextOfKinFirstName,firstName,"User has captured the Next of Kin first name " + firstName);
            driverActions.SendKeys(nextOfKinSurname,Surname,"User has captured the Next of Kin Surname :" + Surname);
            driverActions.SelectDropDown(nextOfKinRelationship,relationship,"User has selected relationship :"+relationship);
            driverActions.SendKeys(nextOfKinCellPhone,nextOfKinCellPhoneNumber,"User has captured the Next of Kin nextOfKinCellPhoneNumber :" + nextOfKinCellPhoneNumber);
            driverActions.SendKeys(nextOfKinEmail,nextOfKinEmailAddress,"User has captured the Next of Kin nextOfKinEmailAddress " + nextOfKinEmailAddress);
            driverActions.Click(ContinueButton,"User has successfully clicked Continue button of the About You Page");
            Thread.sleep(3000);
    }
}