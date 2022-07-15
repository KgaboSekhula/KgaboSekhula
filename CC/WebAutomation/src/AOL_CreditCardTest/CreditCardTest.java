package AOL_CreditCardTest;

import ExcelSheetFields.CreditCardApplicationFields;
import PageObjects.CreditCardPageObject.*;

import Utilities.ExcelFunctions;
import Utilities.ReadObject;
import Utilities.SeleniumDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CreditCardTest {
    SeleniumDriver seleniumDriver;
    CaptchaPage captchaPage;
    BeforeYouStart beforeYouStart;
    GettingStarted gettingStarted;
    BeforeYouStart DetermineApplicationStage;
    AboutYou aboutYou;
    ChooseYourCard chooseYourCard;
    SetupYourCard setupYourCard;
    YourFinances yourFinances;
    ThankYou thankYou;
    String filePath;
    FileInputStream input_document;
    ExcelFunctions excel;
    ReadObject p = new ReadObject();
    String url;
    String testName;
    String accountNo;
    int creditCardApplicationRefno;
    int runStatusColumn;
    String password;
    String ClientName	;
    String ClientSurname	;
    String referenceNumber;
    String identificationType	;
    String ClientIDNumber	;
    String countryOfResidence	;
    String countryOfBirth	;
    String clientCasaCountryOfResidence;
    String addressLine1Residential	;
    String addressLine2Residential	;
    String addressSuburbResidential	;
    String addressCityResidential	;
    String addressPostalCodeResidential	;
    String preferredCommChannele	;
    String phoneDialCodeHome	;
    String phoneNoHome	;
    String phoneDialCodeEmployer	;
    String phoneNoEmployer	;
    String cellNo	;
    String eMailAddress	;
    String employementOccupationStatus;
    String employmentSector;
    String employmentOccupationCode;
    String employmentOccupationLevel;
    String employmentName;
    String employerPhysicalAddressLine1	;
    String employerPhysicalAddressSuburb	;
    String employerPhysicalAddressCity	;
    String employerPhysicalAddressPostalCode	;
    String totalIncome	;
    String totalNetIncome	;
    String totalRepayments	;
    String totalExpenses	;
    String maintenanceExpenses	;
    String kinFirstNames	;
    String kinClientname	;
    String kinPhoneDialCodeHome	;
    String kinPhoneNoHome	;
    String kinPhoneDialCodeEmployer	;
    String kinPhoneNoEmployer	;
    String kinCellNo	;
    String kinEMailAddress	;
    String cardProdGroupId1	;
    String debitAcccount ;
    String homeLanguage;
    String clientMaritalStatus;
    String clientNationality;
    String clientBankName;
    String clientAccountNumber;
    String clientBankAccountType;
    String clientSourceOfFunds;
    String clientResidentialStatus;
    String nextOfKinFirstName;
    String nextOfKinSurname;
    String nextOfKinRelationship;
    String nextOfKinCellPhoneNumber;
    String nextOfKinEmailAddress;
    String accountType;
    String accountNumber;
    String debitDay;
    String runStatus;

    @BeforeMethod
    public void LaunchBrowser() throws IOException,Exception {

        seleniumDriver = new SeleniumDriver();
        url = p.getObjectRepository().getProperty("nonlogin");
        testName = p.getObjectRepository().getProperty("creditCardFlowTestName");
        filePath = p.getObjectRepository().getProperty("PersonalLoanFlowFilePath");
        input_document = new FileInputStream(String.valueOf(new File(filePath)));
        excel = new ExcelFunctions(input_document);
        captchaPage = new CaptchaPage();
        beforeYouStart = new BeforeYouStart();
        gettingStarted  = new GettingStarted();
        aboutYou = new AboutYou();
        chooseYourCard = new ChooseYourCard();
        setupYourCard = new SetupYourCard();
        yourFinances = new YourFinances();
        thankYou = new ThankYou();
        creditCardApplicationRefno = excel.columnsNames.indexOf(CreditCardApplicationFields.creditCardReferenceNumber);
        runStatusColumn = excel.columnsNames.indexOf(CreditCardApplicationFields.runStatus);
    }


    @Test
    public void OpenCreditCardTest() {

        for (int i = 1; i < ExcelFunctions.ScenarioCount; i++) {

            try {

                SetData(i);
                if(runStatus.equalsIgnoreCase("No Run")){

                    seleniumDriver.SetupTest(url, "Credit Card Application - "+i);
                    Thread.sleep(2000);
                    //For New Applications
                    captchaPage.captchaNewApplication();
                    beforeYouStart.DeclarationInformation();
                    gettingStarted.DetailsInformation(ClientName, ClientSurname, identificationType, ClientIDNumber, countryOfResidence, countryOfBirth, clientCasaCountryOfResidence);
                    gettingStarted.AddressInformation(addressLine1Residential, addressLine2Residential, addressSuburbResidential, addressCityResidential, addressPostalCodeResidential);
                    gettingStarted.ContactInformation(cellNo, preferredCommChannele);
                    String ref = yourFinances.EmploymentInformation(employementOccupationStatus, employmentSector, employmentOccupationCode, employmentOccupationLevel, employmentName, employerPhysicalAddressLine1, employerPhysicalAddressSuburb, employerPhysicalAddressCity, employerPhysicalAddressPostalCode, "1");
                    yourFinances.FinancialInformation(totalIncome, totalNetIncome,totalRepayments, totalExpenses, maintenanceExpenses, clientBankName, clientAccountNumber, clientBankAccountType, clientSourceOfFunds);
                    aboutYou.PersonalDetails(clientNationality,clientMaritalStatus,homeLanguage , homeLanguage);
                    aboutYou.AddressInformation(clientResidentialStatus);
                    aboutYou.NextOfKinInformation(nextOfKinFirstName,nextOfKinSurname,nextOfKinRelationship,nextOfKinCellPhoneNumber,nextOfKinEmailAddress);
                    chooseYourCard.ChoosingCard();
                    setupYourCard.CreditCardOptions();
                    setupYourCard.paymentSettings(accountType, accountNo,ClientSurname +" "+ ClientName , debitDay);
                    setupYourCard.paymentSettings(accountType, accountNo,ClientSurname +" "+ ClientName , debitDay);
                    setupYourCard.StatementDelivery();
                    setupYourCard.QuotationDetails();

                    thankYou.ConfirmCreditCardApproval();
                    Thread.sleep(2000);
                    excel.WriteToCell(i,creditCardApplicationRefno,ref.trim());

                    excel.WriteToCell(i,runStatusColumn,"Run");
                    seleniumDriver.EndTest();
                }


            }
            catch (Exception ex){
                seleniumDriver.EndTest();
                System.out.println(ex.getMessage());
            }
        }
    }

    private void SetData(int Scenario) throws IOException {

        runStatus = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.runStatus));
        accountNo = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.accountNo));
        ClientName = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.ClientName));
        ClientSurname = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.ClientSurname));
        referenceNumber = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.referenceNumber));
        identificationType = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.identificationType));
        ClientIDNumber = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.ClientIDNumber));
        countryOfResidence = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.countryOfResidence));
        countryOfBirth = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.countryOfBirth));
        clientCasaCountryOfResidence = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.clientCasaCountryOfResidence));
        countryOfResidence = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.countryOfResidence));
        addressLine1Residential = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.addressLine1Residential));
        addressLine2Residential = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.addressLine2Residential));
        addressSuburbResidential = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.addressSuburbResidential));
        addressCityResidential = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.addressCityResidential));
        addressPostalCodeResidential = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.addressPostalCodeResidential));
        preferredCommChannele = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.preferredCommChannele));
        phoneDialCodeHome = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.phoneDialCodeHome));
        phoneNoHome = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.phoneNoHome));
        phoneDialCodeEmployer	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.phoneDialCodeEmployer));
        phoneNoEmployer	 = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.phoneNoEmployer));
        cellNo	 = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.cellNo));
        eMailAddress	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.eMailAddress));
        employementOccupationStatus	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employementOccupationStatus));
        employmentSector	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employmentSector));
        employmentOccupationCode	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employmentOccupationCode));
        employmentOccupationLevel	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employmentOccupationLevel));
        employmentName = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employmentName));
        employerPhysicalAddressLine1	 = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employerPhysicalAddressLine1));
        employerPhysicalAddressSuburb	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employerPhysicalAddressSuburb));
        employerPhysicalAddressCity	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employerPhysicalAddressCity));
        employerPhysicalAddressPostalCode	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.employerPhysicalAddressPostalCode));
        totalIncome	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.totalIncome));
        totalNetIncome	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.totalNetIncome));
        totalRepayments	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.totalRepayments));
        totalExpenses	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.totalExpenses));
        maintenanceExpenses	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.maintenanceExpenses));
        kinFirstNames	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinFirstNames));
        kinClientname	 = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinClientname));
        kinPhoneDialCodeHome	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinPhoneDialCodeHome));
        kinPhoneNoHome	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinPhoneNoHome));
        kinPhoneDialCodeEmployer	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinPhoneDialCodeEmployer));
        kinPhoneNoEmployer	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinPhoneNoEmployer));
        kinCellNo	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinCellNo));
        kinEMailAddress	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.kinEMailAddress));
        cardProdGroupId1	= excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.cardProdGroupId1));
        debitAcccount = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.debitAcccount));
        homeLanguage = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.homeLanguage));
        clientMaritalStatus = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.clientMaritalStatus));
        clientNationality = excel.ReadCell(Scenario, excel.columnsNames.indexOf(CreditCardApplicationFields.clientNationality));
        clientBankName = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.clientBankName));
        clientAccountNumber = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.clientAccountNumber));
        clientBankAccountType = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.clientBankAccountType));
        clientSourceOfFunds = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.clientSourceOfFunds));
        clientResidentialStatus = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.clientResidentialStatus));
        nextOfKinFirstName = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.nextOfKinFirstName));
        nextOfKinSurname = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.nextOfKinSurname));
        nextOfKinRelationship = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.nextOfKinRelationship));
        nextOfKinCellPhoneNumber = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.nextOfKinCellPhoneNumber));
        nextOfKinEmailAddress = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.nextOfKinEmailAddress));
        accountType = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.accountType));
        accountNumber = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.accountNumber));
        debitDay = excel.ReadCell(Scenario,excel.columnsNames.indexOf(CreditCardApplicationFields.debitDay));
       }
}
