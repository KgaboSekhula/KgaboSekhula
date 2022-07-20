package PageObjects.CreditCardPageObject;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Utilities.SeleniumDriver.driver;

public class GettingStarted {
    SeleniumDriver driverActions = new SeleniumDriver();

    By clientTitleDropdown = By.cssSelector("select[name='title']");
    By clientFirstNamesText = By.cssSelector("input[name='firstNames']");
    By clientSurnameText = By.cssSelector("input[name='clientname']");
    By clientIdentificationTypeDropdown = By.cssSelector("select[name='identificationType']");
    By clientIDNumberText = By.cssSelector("input[name='idOrRegistrationNo']");
    By clientCountryOfResidenceDropdown = By.cssSelector("select[name='countryOfResidence']");

    By clientCountryOfBirthDropdown = By.cssSelector("select[name='countryOfBirth']");
    By clientCasaCountryOfResidenceDropdown = By.xpath("//select[@name='casaCountryOfResidence']");

    //--------------
    By clientAddressLine1ResidentialText = By.cssSelector("input[name='addressLine1Residential']");
    By clientAddressLine2ResidentialText = By.cssSelector("input[name='addressLine2Residential']");
    By clientAddressSuburbResidentialText = By.cssSelector("input[name='addressSuburbResidential']");
    By clientAddressCityResidentialText = By.cssSelector("input[name='addressCityResidential']");
    By clientAddressPostalCodeResidentialText = By.cssSelector("input[name='addressPostalCodeResidential']");

    By clientCellNoText = By.cssSelector("input[name='cellNo']");
    By clientPreferredCommChannelDropdown = By.cssSelector("select[name='preferredCommChannel']");
    By clientSmsMarketingMaterialCheck = By.cssSelector("input[value='Y'][name='smsMarketingMaterial']");
    By clientCreditBureauConsentCheck = By.cssSelector("input[value='Y'][name='creditBureauConsent']");
    By clientContinueButton = By.cssSelector("input[value='Continue']");

    public void DetailsInformation(String clientFirstNames, String clientSurname, String clientIdentificationType, String clientIDNumber, String clientCountryOfResidence, String clientCountryOfBirth, String clientCasaCountryOfResidence) throws Exception {
        TestReporter.LogStep("User capturing Details Information section of '2. Getting Started' Page");

        driverActions.SendKeys(clientFirstNamesText, clientFirstNames,"User has successfully captured client first names :"+clientFirstNames);
        driverActions.SendKeys(clientSurnameText, clientSurname,"User has successfully captured client surname :" +clientSurname);
        driverActions.SelectDropDown(clientIdentificationTypeDropdown, clientIdentificationType,"User selected clientI dentification Type : "+clientIdentificationType);
        driverActions.SendKeys(clientIDNumberText, clientIDNumber,"User has successfully captured client ID :"+clientIDNumber);
        driverActions.SelectDropDown(clientCountryOfResidenceDropdown, clientCountryOfResidence,"User selected clientCountryOfResidence :"+clientCountryOfResidence);
        WebElement Male = driver.findElement(By.xpath("//input[@value='1']"));
        WebElement Female = driver.findElement(By.xpath("//input[@value='2']"));
        boolean maleState = Male.isSelected();
        boolean femaleState = Female.isSelected();
        if (!maleState) {

            driverActions.SelectDropDown(clientTitleDropdown, "Miss","User selected client title as Miss");

        } else if (!femaleState) {
            driverActions.SelectDropDown(clientTitleDropdown, "Mr","User selected client title as Mr");

        }
        driverActions.SelectDropDown(clientCountryOfBirthDropdown, clientCountryOfBirth,"User has selected the clientCountryOfBirth :"+ clientCountryOfBirth);
        driverActions.SelectDropDown(clientCasaCountryOfResidenceDropdown, clientCasaCountryOfResidence,"User has selected clientCasaCountryOfResidence :"+clientCasaCountryOfResidence);

    }

    public void AddressInformation(String clientAddressLine1Residential, String clientAddressLine2Residential, String clientAddressSuburbResidential, String clientAddressCityResidential, String clientAddressPostalCodeResidential) throws Exception {
        TestReporter.LogStep("User capturing Address Information section of '2. Getting Started' Page");
        driverActions.SendKeys(clientAddressLine1ResidentialText, clientAddressLine1Residential,"User has successfully captured client residential address line1 :" +clientAddressLine1Residential);
        driverActions.SendKeys(clientAddressLine2ResidentialText, clientAddressLine2Residential,"User has successfully captured client residential address line 2 :" +clientAddressLine1Residential);
        driverActions.SendKeys(clientAddressSuburbResidentialText, clientAddressSuburbResidential,"User has successfully captured client suburb :"+clientAddressSuburbResidential);
        driverActions.SendKeys(clientAddressCityResidentialText, clientAddressCityResidential,"User has successfully captured client residential address :" +clientAddressCityResidential);
        driverActions.SendKeys(clientAddressPostalCodeResidentialText, clientAddressPostalCodeResidential,"User has successfully captured postal address code :" +clientAddressPostalCodeResidential);
    }


    public void ContactInformation(String clientCellNo,String clientPreferredCommChannel) throws Exception {

        boolean safeToContinue ;

        TestReporter.LogStep("User capturing Contact Information section of '2. Getting Started' Page");
        driverActions.SendKeys(clientCellNoText, clientCellNo, "User captured client Cell No :"+ clientCellNo);
        driverActions.SelectDropDown(clientPreferredCommChannelDropdown, clientPreferredCommChannel,"User has selected the clientPreferredCommChannel :"+clientPreferredCommChannel);
        driverActions.WindowScroll();
        driverActions.Click(clientSmsMarketingMaterialCheck, "Client selected the SMS for marketing");
        driverActions.Click(clientCreditBureauConsentCheck, "Client selected the CreditBureauConsent for marketing");
        driverActions.Click(clientContinueButton, "User clicked Continue Button");

    //    if(driverActions.IsElementVisible(clientContinueButton)) {
    //          safeToContinue = false;
     //         TestReporter.FailScenario("Client could not continue to the next section with the Credit card application process");
      //      }else {
      //         safeToContinue = true;
     //          TestReporter.LogStep("User has Completed the '1.Before you start' Section Capture section of the Credit card application process");
    //    }
       // }
     //   return safeToContinue;
    }

}