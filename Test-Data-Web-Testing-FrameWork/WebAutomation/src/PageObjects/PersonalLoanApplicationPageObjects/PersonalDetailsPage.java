/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class PersonalDetailsPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By alternativeNumberField = By.id("id_customerAltNumber");
    By emailAddressField = By.id("id_emailAddress");
    By marriedMaritalStatusCheckBox = By.cssSelector("label[for='maritalStatus1-input']");
    By notMarriedMaritalStatusCheckBox = By.cssSelector("label[for='maritalStatus0-input']");
    By relationShipSpecificDropDown = By.id("id_yourStatus");
    By yearsInCurrentAddressField = By.id("id_years");
    By monthsInCurrentAddressField = By.id("id_months");
    By residenceStatusDropDown = By.id("id_residentialStatus");
    By propertyValueField = By.id("id_residentialAmount");
    By postalAddressHeader = By.xpath("//h2[contains(text(), 'Your postal address')]");
    By postalAddressLine1Field = By.id("id_postalAddress1");
    By postalAddressLine2Field = By.id("id_postalAddress2");
    By addressSuburbField = By.id("id_postalSuburb");
    By nextOfKinHeaderHeader = By.xpath("//h2[contains(text(), 'Your next of kin')]");
    By nextOfKinFirstNameField = By.id("id_kinFirstName");
    By nextOfKinSurnameNameField = By.id("id_kinSurname");
    By nextOfKinCellNo = By.id("id_kinContactNumber");
    By nextOfKinEmailAddressField = By.id("id_kinEmailAddress");
    By nextOfKinRelationshipDropDown = By.id("id_kinRelationship");
    By letsContinueButton = By.cssSelector("button[data-id = 'link_content_personalloan']");


    public void FillPersonalDetails(String alternativeNumbers, String emailAddress) {

        //Some of the elements are not visible for some account so a check needs to be done
        //Email address cannot be updated because it directs us to the upgrade screen
        if(driverActions.IsElementVisible(alternativeNumberField) )
        {
            TestReporter.LogStep("Type in Alternative numbers: "+alternativeNumbers);
            driverActions.SendKeys(alternativeNumberField, alternativeNumbers);
        }
    }

    public void FillRelationshipDetails(String maritalStatus, String relationshipSpecific ) throws Exception {

        if (maritalStatus.trim().equals("Not married")){

            TestReporter.LogStep("Select marital status as not married ");
            driverActions.Click(notMarriedMaritalStatusCheckBox);
        }
        else {
            TestReporter.LogStep("Select marital status as married ");
            driverActions.Click(marriedMaritalStatusCheckBox);
        }
        TestReporter.LogStep("Select type of relationship as "+relationshipSpecific +" from dropdown");
        driverActions.SelectItemFromDropDown(relationShipSpecificDropDown,relationshipSpecific);
    }

    public void FillResidentialOccupationDetails(String durationInYears, String durationMonths, String residentialStatus, String propertyValue) {

        TestReporter.LogStep("Enter the duration of years as "+durationInYears + " And months as "+ durationMonths +" of occupancy in residence");
        driverActions.SendKeys(yearsInCurrentAddressField, durationInYears);
        driverActions.SendKeys(monthsInCurrentAddressField,durationMonths);
        TestReporter.LogStep("Select residential Status: "+ residentialStatus);
        driverActions.SelectItemFromDropDown(residenceStatusDropDown, residentialStatus);
        if (residentialStatus.trim().equals("Owner")){
            TestReporter.LogStep("Type in the property valuation at present time: "+ residentialStatus);
            driverActions.SendKeys(propertyValueField,propertyValue);
        }

    }

    public void FillPostalAddressDetails(String postalAddressLine1, String postalAddressLine2, String suburb) throws InterruptedException {

        //Some of the elements are not visible for some account so a check needs to be done
        if (driverActions.IsElementVisible(postalAddressHeader)){

            TestReporter.LogStep("Postal Address details line one: "+ postalAddressLine1 +" Postal Address line 2: "+postalAddressLine2+" Suburb: "+suburb);
            driverActions.SendKeys(postalAddressLine1Field, postalAddressLine1);
            driverActions.SendKeys(postalAddressLine2Field,postalAddressLine2);
            driverActions.SelectItemFromSearchField(addressSuburbField, suburb);
        }
    }


    public void FillNextOfKinDetails(String name, String surname, String cellNo, String emailAddress, String relationship) throws Exception {

        //Some of the elements are not visible for some account so a check needs to be done
        if (driverActions.IsElementVisible(nextOfKinHeaderHeader)){

            TestReporter.LogStep("Type in next of Kin details  Name: "+name+" Surname: "+ surname+" Cell Number:"+ cellNo + " Email: "+emailAddress +" Relationship: "+relationship );
            driverActions.SendKeys(nextOfKinFirstNameField,name);
            driverActions.SendKeys(nextOfKinSurnameNameField,surname);
            driverActions.SendKeys(nextOfKinCellNo,cellNo);
            driverActions.SendKeys(nextOfKinEmailAddressField,emailAddress);
            driverActions.SelectItemFromDropDown(nextOfKinRelationshipDropDown,relationship);
        }
    }

    public void ClickLetsContinueButton() {
        TestReporter.LogStep("Click on the continue button on the Personal Details Page");
        driverActions.Click(letsContinueButton);
    }

}
*/