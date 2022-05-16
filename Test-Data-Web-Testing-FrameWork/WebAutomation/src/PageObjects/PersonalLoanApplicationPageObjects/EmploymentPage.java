/*package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import Utilities.TestReporter;
import org.openqa.selenium.By;

public class EmploymentPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By inCorrectEmploymentDetailsRadioButton = By.cssSelector("label[for = 'detailsChangeRadio1-input']");
    By correctEmploymentDetailsRadioButton = By.cssSelector("label[for = 'detailsChangeRadio0-input']");
    By numberOfYearsEmployedField = By.id("id_years");
    By monthsEmployedField = By.id("id_months");
    By currentOccupationDropDown = By.id("id_status");
    By jobTitleDropDown = By.id("id_jobTitle");
    By employmentIndustryDropDown = By.id("id_industries");
    By employmentLevelDropDown = By.id("id_level");
    By employerNameField = By.id("id_employerName");
    By employerAddressLine1Field = By.id("id_addressLine1");
    By employerAddressLine2Field = By.id("id_addressLine2");
    By suburbField = By.id("id_suburb");
    By employerDetailsPanel = By.xpath("//div[contains(text(), 'Employer details')]");
    By letsContinueButton = By.cssSelector("button[data-id = 'link_content_']");
    boolean unEmployedGroping;

    public void FillTellUsWhatYouCurrentlyDo(String employmentDetailsStatus, String currentOccupation,String employmentLevel, String jobTitle, String industry, String yearsEmployed, String monthsEmployed) throws InterruptedException {

         //Boolean value to check the type of employment that has been placed on the datasheet.
        boolean fullTimeEmploymentStatus = currentOccupation.equals("Full Time Employed") || currentOccupation.equals("Temporary Employed") || currentOccupation.equals("Part Time/contract Worker");
        //Boolean to group the type of unemployment filled in the datasheet
        unEmployedGroping = (currentOccupation.equals("Pre-school/scholar") || currentOccupation.equals("Homemaker") || currentOccupation.equals("Unemployed") || currentOccupation.equals("Pensioner"));
        if (employmentDetailsStatus.equals("No") && !unEmployedGroping) {

            //If statement that fills in details if user has incorrect employment details and the datasheet says that the user is employed
            TestReporter.LogStep("User clicks on incorrect employment details radio button");
            driverActions.Click(inCorrectEmploymentDetailsRadioButton);
            driverActions.SelectItemFromDropDown(currentOccupationDropDown,currentOccupation);
            driverActions.SelectItemFromDropDown(jobTitleDropDown,jobTitle);
            driverActions.SelectItemFromDropDown(employmentIndustryDropDown,industry);
            if (fullTimeEmploymentStatus){
                driverActions.SelectItemFromDropDown(employmentLevelDropDown,employmentLevel);
            }
            driverActions.SendKeys(numberOfYearsEmployedField,yearsEmployed);
            driverActions.SendKeys(monthsEmployedField,monthsEmployed);
            TestReporter.LogStep("User selects their current occupation as: "+currentOccupation + " Job title: "+jobTitle+" Industry: "+ industry+
                    "Number of years employed: "+yearsEmployed+ " number of months employed: "+monthsEmployed);
        }
        else if(employmentDetailsStatus.equals("No")){

            //This else if statement covers the area where the user has incorrect employment details and the datasheet shows that the user is actually unemployed
            TestReporter.LogStep("User clicks incorrect employment details ");
            driverActions.Click(inCorrectEmploymentDetailsRadioButton);
            TestReporter.LogStep("User selects the current occupation as: "+currentOccupation);
            driverActions.SelectItemFromDropDown(currentOccupationDropDown,currentOccupation);
        }
        else{

            //This is the final decision that caters when the employment details that pre-existed are correct
            Thread.sleep(4000);
            if (driverActions.IsElementVisible(correctEmploymentDetailsRadioButton)){
                TestReporter.LogStep("User clicks on the correct employment details radio button");
                driverActions.Click(correctEmploymentDetailsRadioButton);
            }
            if (!isEmployed()) {
                //This if statement handles the employment details that are having an option of updating
                driverActions.SelectItemFromDropDown(currentOccupationDropDown, currentOccupation);

                driverActions.SelectItemFromDropDown(jobTitleDropDown, jobTitle);
                driverActions.SelectItemFromDropDown(employmentIndustryDropDown, industry);
                if (fullTimeEmploymentStatus) {
                    driverActions.SelectItemFromDropDown(employmentLevelDropDown, employmentLevel);
                    TestReporter.LogStep("User enter employment level as: "+employmentLevel);
                }
            }
            driverActions.SendKeys(numberOfYearsEmployedField,yearsEmployed);
            driverActions.SendKeys(monthsEmployedField,monthsEmployed);
            TestReporter.LogStep("User selects their current occupation as: "+currentOccupation + " Job title: "+jobTitle+" Industry: "+ industry+
                    "Number of years employed: "+yearsEmployed+ " number of months employed: "+monthsEmployed);
        }
    }

    public void FillTellUsAboutYourCurrentEmployer(String employerName, String addressLine1, String addressLine2, String Suburb) throws InterruptedException {

        //This particular method  handles the employer address details
        if(!unEmployedGroping && !isEmployed()){
            TestReporter.LogStep("User enters the employee details as: employer name: "+employerName + " address line one: "+addressLine1+
                    " address line two: "+addressLine2+ " Suburb: "+ Suburb);
            driverActions.SendKeys(employerNameField,employerName);
            driverActions.SendKeys(employerAddressLine1Field,addressLine1);
            driverActions.SendKeys(employerAddressLine2Field,addressLine2);
            driverActions.SelectItemFromSearchField(suburbField,Suburb);
        }
    }

    private boolean isEmployed() {

        return driverActions.IsElementVisible(employerDetailsPanel);
    }

    public void ClickLetsContinueButton(){

        TestReporter.LogStep("User clicks on the lets continue button on the employment page.");
        driverActions.Click(letsContinueButton);
    }

}
*/