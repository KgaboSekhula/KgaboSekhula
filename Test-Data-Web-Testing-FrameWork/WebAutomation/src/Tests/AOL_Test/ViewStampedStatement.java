package Tests.AOL_Test;

import ExcelSheetFields.BankConfirmationLettersFields;
import ExcelSheetFields.StampedStatementFields;
import PageObjects.AolPageObjects.AccountsPage;
import PageObjects.AolPageObjects.LoginPage;
import Utilities.ExcelFunctions;
import Utilities.ReadObject;
import Utilities.SeleniumDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static ExcelSheetFields.BankConfirmationLettersFields.accountNo;

public class ViewStampedStatement {

    SeleniumDriver seleniumDriver;
    LoginPage loginPage;

    AccountsPage accountsPage;
    String filePath;
    FileInputStream input_document;
    ExcelFunctions excel;
    ReadObject p = new ReadObject();
    String url;
    String scenario;

    @BeforeMethod
    public void SetUpTest() throws IOException {

        seleniumDriver = new SeleniumDriver();
        url = p.getObjectRepository().getProperty("absaOnlineUrl2");
        filePath = p.getObjectRepository().getProperty("StampedStatementTestScenariosFlowPath");
        input_document = new FileInputStream(String.valueOf(new File(filePath)));
        excel = new ExcelFunctions(input_document);

        loginPage = new LoginPage();
        accountsPage = new AccountsPage();
    }

    @Test
    public void ViewStampedStatementTest() {

        for (int i = 1 ; i<ExcelFunctions.ScenarioCount; i++){

            try {
                SetData(i);
                seleniumDriver.SetupTest(url, scenario);
                loginPage.Login(accountNo);
                loginPage.EnterPassword();
                accountsPage.ClickAccountTypeLink(accountNo);
                accountsPage.ClickStampedStatementIcon();
                accountsPage.ClickGetStampedStatementButton();
                accountsPage.SwitchToPopWindow();
                Thread.sleep(15000);
                //seleniumDriver.WaitForPageToLoad(100,"Waited for the PDF Page to load");
                accountsPage.ConfirmBankLettersPdfUrl(accountNo);
                seleniumDriver.EndTest();
            }
            catch (Exception ex) {

                seleniumDriver.EndTest();
                System.out.println(ex.getMessage());
            }

        }
    }

    private void SetData(int Scenario) throws IOException {

        accountNo = excel.ReadCell(Scenario, excel.columnsNames.indexOf(StampedStatementFields.accountNo));
        scenario = excel.ReadCell(Scenario, excel.columnsNames.indexOf(BankConfirmationLettersFields.scenarioName));

    }

}
