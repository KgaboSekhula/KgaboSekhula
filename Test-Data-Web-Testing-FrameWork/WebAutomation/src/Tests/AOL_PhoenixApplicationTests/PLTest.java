/*package Tests.AOL_PhoenixApplicationTests;

import PageObjects.AolPageObjects.*;
import PageObjects.PersonalLoanApplicationPageObjects.*;
import ExcelSheetFields.PersonalLoanApplicationFields;
import Utilities.ReadObject;
import Utilities.SeleniumDriver;
import Utilities.ExcelFunctions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PLTest {


    SeleniumDriver seleniumDriver;
    LoginPage loginPage;
    PhoneixSimulatorPage phoneixSimulatorPage;
    OnlineBankingHomePage onlineBankingHomePage;
    LetsGetStartedPage letsGetStartedPage;
    PrivacyDisclaimerPage privacyDisclaimerPage;
    ApplyPage applyPage;
    LoanSetupPage loanSetupPage;
    PersonalDetailsPage personalDetailsPage;
    EmploymentPage employmentPage;
    ExpensesPage expensesPage;
    IncomePage incomePage;
    PersonalLoanOffer personalLoanOfferPage;
    DebitCheckMandatePage debitCheckMandatePage;
    LoanSummaryPage loanSummaryPage;
    EndApplicationPage endApplicationPage;
    String filePath;
    FileInputStream input_document;
    ExcelFunctions excel;
    ReadObject  p = new ReadObject();
    String url;
    String testName;

    String accountNo;
    String password;

    String application;
    String product;

    String loanAmount;
    String loanDuration;
    String settleExistingLoanStatus;
    String reasonForLoan;
    String customerAlternativeNumber;
    String emailAddress;
    String maritalStatus;
    String relationshipSpecific;
    String yearsInCurrentAddress;
    String monthInCurrentAddress;
    String residentialAddressStatus;
    String currentPropertyValue;
    String postalAddressLineOne;
    String postalAddressLineTwo;
    String suburb;
    String nextOfKin1stName;
    String nextOfKinSurname;
    String nextOfKinCellNo;
    String nextOfKinEmail;
    String nextOfKinRelationship;

    String areEmployeeDetailsCorrect;
    String employmentUnemploymentType;
    String industry;
    String jobTitle;
    String employmentLevel;
    String employmentDurationInYears;
    String employmentDurationInMonths;
    String employerName;
    String employerAddressLineOne;
    String employerAddressLineTwo;
    String employerSuburb;

    String changeIncomeInformation;
    String grossIncome;
    String netSalaryMonth1;
    String netSalaryMonth2;
    String netSalaryMonth3;
    String rentalIncome;
    String otherIncome;
    String debtDeductionStatus;
    String salaryDay;


    @BeforeMethod
    public void LaunchBrowser() throws IOException {

        seleniumDriver = new SeleniumDriver();
        url = p.getObjectRepository().getProperty("phoniexUrl");
        testName = p.getObjectRepository().getProperty("plFlowTestName");
        filePath = p.getObjectRepository().getProperty("PersonalLoanFlowFilePath");
        input_document = new FileInputStream(String.valueOf(new File(filePath)));
        excel = new ExcelFunctions(input_document);
        seleniumDriver.SetupTest(url, testName);

        loginPage = new LoginPage();
        applyPage = new ApplyPage();
        phoneixSimulatorPage = new PhoneixSimulatorPage();
        onlineBankingHomePage = new OnlineBankingHomePage();
        letsGetStartedPage = new LetsGetStartedPage();
        privacyDisclaimerPage = new PrivacyDisclaimerPage();
        personalDetailsPage = new PersonalDetailsPage();
        loanSetupPage = new LoanSetupPage();
        employmentPage = new EmploymentPage();
        incomePage = new IncomePage();
        expensesPage = new ExpensesPage();
        personalLoanOfferPage = new PersonalLoanOffer();
        debitCheckMandatePage = new DebitCheckMandatePage();
        endApplicationPage = new EndApplicationPage();

    }

    @Test
    public void OpenPlAccountTest() throws Exception {

        for (int i = 1 ; i<ExcelFunctions.ScenarioCount; i++){

            SetData(i);
            phoneixSimulatorPage.SelectProduct(application,product);
            letsGetStartedPage.SelectAbsaCustomerWithOnlineBanking();
            loginPage.Login(accountNo);
            loginPage.EnterPassword();
            privacyDisclaimerPage.AcceptPrivacyDisclaimer();
            loanSetupPage.SetupLoan(settleExistingLoanStatus,loanAmount,loanDuration,reasonForLoan);

            personalDetailsPage.FillPersonalDetails(customerAlternativeNumber,emailAddress);
            personalDetailsPage.FillRelationshipDetails(maritalStatus,relationshipSpecific);
            personalDetailsPage.FillResidentialOccupationDetails(yearsInCurrentAddress,monthInCurrentAddress,residentialAddressStatus,currentPropertyValue);
            personalDetailsPage.FillPostalAddressDetails(postalAddressLineOne,postalAddressLineTwo,suburb);
            personalDetailsPage.FillNextOfKinDetails(nextOfKin1stName, nextOfKinSurname,nextOfKinCellNo, nextOfKinEmail,nextOfKinRelationship);
            personalDetailsPage.ClickLetsContinueButton();

            employmentPage.FillTellUsWhatYouCurrentlyDo(areEmployeeDetailsCorrect, employmentUnemploymentType,employmentLevel,jobTitle,industry,employmentDurationInYears,employmentDurationInMonths);
            employmentPage.FillTellUsAboutYourCurrentEmployer(employerName, employerAddressLineOne,employerAddressLineTwo, suburb);
            employmentPage.ClickLetsContinueButton();

            incomePage.FillFinancesInformation(grossIncome, netSalaryMonth1, netSalaryMonth2, netSalaryMonth3, rentalIncome, otherIncome, debtDeductionStatus, salaryDay);
            incomePage.FillInExpenses();
            incomePage.FinancesSummary(salaryDay);

            personalLoanOfferPage.AcceptLoanOffer();
            personalLoanOfferPage.ClickLetsContinueButton();
            debitCheckMandatePage.ApproveDebitcheckMandate();
            loanSummaryPage.ClickContinueButton();
            endApplicationPage.EndApplicationButton();
        }
    }

    @AfterMethod
    public void CleanTest(){

        seleniumDriver.TearDown();
    }

    private void SetData(int Scenario) throws IOException {

        application = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.application));
        product = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.product));
        password = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.password));
        accountNo = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.accountNo));
        loanAmount = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.loanAmount));
        settleExistingLoanStatus = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.settleExistingLoanStatus));
        loanDuration = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.loanDuration));
        reasonForLoan = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.reasonForLoan));

        customerAlternativeNumber = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.customerAlternativeNumber));
        emailAddress = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.emailAddress));
        maritalStatus = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.maritalStatus));
        relationshipSpecific = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.relationshipSpecific));
        yearsInCurrentAddress = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.yearsInCurrentAddress));
        monthInCurrentAddress = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.monthInCurrentAddress));
        residentialAddressStatus = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.residentialAddressStatus));
        currentPropertyValue = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.currentPropertyValue));
        postalAddressLineOne = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.postalAddressLineOne));
        postalAddressLineTwo = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.postalAddressLineTwo));
        suburb = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.suburb));
        nextOfKin1stName = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.nextOfKin1stName));
        nextOfKinSurname = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.nextOfKinSurname));
        nextOfKinCellNo = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.nextOfKinCellNo));
        nextOfKinEmail = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.nextOfKinEmail));
        nextOfKinRelationship = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.nextOfKinRelationship));

        areEmployeeDetailsCorrect = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.areEmployeeDetailsCorrect));
        employmentUnemploymentType = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employmentUnemploymentType));
        industry = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.industry));
        jobTitle = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.jobTitle));
        employmentLevel = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employmentLevel));
        employmentDurationInYears = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employmentDurationInYears));
        employmentDurationInMonths = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employmentDurationInMonths));
        employerName = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employerName));
        employerAddressLineOne = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employerAddressLineOne));
        employerAddressLineTwo = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employerAddressLineTwo));
        employerSuburb = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.employerSuburb));

        changeIncomeInformation = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.changeIncomeInformation));
        grossIncome = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.grossIncome));
        netSalaryMonth1 = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.netSalaryMonth1));
        netSalaryMonth2 = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.netSalaryMonth2));
        netSalaryMonth3 = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.netSalaryMonth3));
        rentalIncome = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.rentalIncome));
        otherIncome = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.otherIncome));
        debtDeductionStatus = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.debtDeductionStatus));
        salaryDay = excel.ReadCell(Scenario, excel.columnsNames.indexOf(PersonalLoanApplicationFields.salaryDay));
    }


}
*/