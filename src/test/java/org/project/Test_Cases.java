package org.project;

import com.pages.AlertPopupVerification;
import com.pages.ErrorTextValidation;
import com.pages.InputFormCheck;
import init.settings.SeleniumSetUp;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static ax.generic.FileProcessing.deleteFilesFromFolder;
import static ax.generic.FileProcessing.deleteOld_Files;


public class Test_Cases extends SeleniumSetUp {


    @BeforeSuite
    public void preTestSettinngs() {

        //Delete previously created screenshots from .\src\main\resources\current_images folder
        deleteFilesFromFolder(".\\src\\main\\resources\\current_images");

        //Delete previously created screenshots from .\src\main\resources\error_images folder
        deleteFilesFromFolder(".\\src\\main\\resources\\error_images");

        //Delete previously created log4j files from /log_files_temp folder
        deleteFilesFromFolder(".\\src\\main\\resources\\log_files_temp");
    }


    @BeforeTest
    public void preTestConfigurations() throws Exception {

        //Delete previously created log4j files
        deleteOld_Files(".\\src\\main\\resources\\log_files", "LOG files from 'log_files' folder:");

        //Path to the Log4j logger config file
        PropertyConfigurator.configure(".\\src\\main\\resources\\log4j.properties");

    }


    @Test(priority = 0)
    @Parameters({"FirstName", "LastName", "PhoneNumber", "UserName", "Email", "AboutYourself", "Password", "ConfirmPassword"})
    public void firstTest_Testing(String FirstName, String LastName, String PhoneNumber, String UserName, String Email, String AboutYourself, String Password, String ConfirmPassword) throws Exception {

        InputFormCheck input_FormCheck = new InputFormCheck(driver); //Instantiating object of "InputFormCheck" class

        input_FormCheck.openFirstTestPage();//open the URL of the landing page and wait till it gets loaded
        Thread.sleep(1000);

        input_FormCheck.filloutAllFieldsAndControls(FirstName, LastName, PhoneNumber, UserName, Email, AboutYourself, Password, ConfirmPassword);//fill out all inputs and other control elements
        Thread.sleep(1000);


        input_FormCheck.submitForm();//click on SUBMIT button and check the resulting message
        Thread.sleep(1000);

    }


    @Test(dependsOnMethods = {"firstTest_Testing"})
    public void secondTest_Testing() throws Exception {

        AlertPopupVerification alertPopup = new AlertPopupVerification(driver); //Instantiating object of " AlertPopupVerification" class

        alertPopup.openSecondTestPage();//open the URL of the landing page and wait till it gets loaded
        Thread.sleep(1000);

        alertPopup.clickDisplayAlertButton();//click on the button "Display alert" to get the alert popup
        Thread.sleep(1000);

        alertPopup.verifyAlertTextAndCloseAlertPopup();//verify text on the alert popup and close it
        Thread.sleep(1000);
    }


    @Test(dependsOnMethods = {"secondTest_Testing"})
    @Parameters({"City", "State", "Zip"})
    public void thirdTest_Testing(String City, String State, String Zip) throws Exception {

        ErrorTextValidation validateError = new ErrorTextValidation(driver); //Instantiating object of "ErrorTextValidation" class

        validateError.openThirdTestPage();//open the URL of the landing page and wait till it gets loaded
        Thread.sleep(1000);

        validateError.clickSubmitFormButton();//click 'Submit Form' button without having selected or filled in any fields
        Thread.sleep(1000);

        validateError.verifyEachErrorText(City, State, Zip);//verify if any of the field has an error text - if yes, then fill it in
        Thread.sleep(1000);
    }

}
