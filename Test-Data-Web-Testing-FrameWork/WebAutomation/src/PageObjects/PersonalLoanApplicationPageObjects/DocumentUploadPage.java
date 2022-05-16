package PageObjects.PersonalLoanApplicationPageObjects;

import Utilities.SeleniumDriver;
import org.openqa.selenium.By;

public class DocumentUploadPage {

    SeleniumDriver driverActions = new SeleniumDriver();
    By addFilesButton = By.cssSelector("div[class='document-upload__action-one']");
    By sendDocumentsButton = By.cssSelector("button[data-text='documents upload | Send documents button click']");
    By netSalaryMonthTwoField = By.cssSelector("input[id ='id_netSalaryMonth2']");


    public void UploadDocuments(){

    }
}
