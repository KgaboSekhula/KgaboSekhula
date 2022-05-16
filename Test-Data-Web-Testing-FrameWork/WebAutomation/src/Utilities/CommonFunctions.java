package Utilities;

public class CommonFunctions {

    SeleniumDriver driverActions = new SeleniumDriver();

    public boolean UrlVerification(String actualUrl, String expectedUrlVerificationString){

        return actualUrl.contains(expectedUrlVerificationString);
    }

    public String GetCurrenPageUrl() throws Exception {
        String url = "";
            try {
                url = driverActions.GetURL();
            } catch (Exception ex) {

                TestReporter.FailScenario("Unable to complete action");
                throw ex;
            }
        return url;
    }
}