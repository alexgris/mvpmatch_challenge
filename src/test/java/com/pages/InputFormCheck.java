package com.pages;

import init.settings.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static ax.generic.Waitings.waitForExtAjaxIsReadyState;
import static ax.generic.Waitings.waitTillDescendentElementsAvailable;

public class InputFormCheck extends PageObject {

    //Locating 'First Name' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::input[@name='name']")
    private WebElement input_FirstName;

    //Locating 'Last Name' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[contains (text(), 'Last Name')]/ancestor::p[1]/input")
    private WebElement input_LastName;

    //Locating 'Marital Status' radio button
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[contains (text(), 'Marital Status')]/ancestor::fieldset[1]/div/label[2]/input[contains (@name, 'm_status')]")
    private WebElement radioButton_MaritalStatus;

    //Locating 'Hobby' check-box
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[contains (text(), 'Hobby')]/ancestor::fieldset[1]/div/label[2]/input[contains (@name, 'hobby')]")
    private WebElement checkBox_Hobby;


    //Locating 'Country' drop-box
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[text()='Country:']/ancestor::fieldset[1]/select")
    private WebElement selector_Country;

    //Locating 'Month' drop-box
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[text()='Date of Birth:']/ancestor::fieldset[1]/div[1]/select")
    private WebElement selector_Month;

    //Locating 'Day' drop-box
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[text()='Date of Birth:']/ancestor::fieldset[1]/div[2]/select")
    private WebElement selector_Day;

    //Locating 'Year' drop-box
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[text()='Date of Birth:']/ancestor::fieldset[1]/div[3]/select")
    private WebElement selector_Year;

    //Locating 'Phone Number' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::input[@name='phone']")
    private WebElement input_PhoneNumber;

    //Locating 'UserName' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::input[@name='username']")
    private WebElement input_UserName;

    //Locating 'Email' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::input[@name='email']")
    private WebElement input_Email;

    //Locating 'About Yourself' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::label[text()='About Yourself']/ancestor::fieldset[1]/textarea")
    private WebElement input_AboutYourself;

    //Locating 'Password' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::input[@name='password']")
    private WebElement input_Password;

    //Locating 'Confirm Password' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::input[@name='c_password']")
    private WebElement input_ConfirmPassword;

    //Locating 'Your Profile Picture' field
    @FindBy(xpath = "//form[contains (@id, 'register_form')]/descendant::input[contains (@value, 'submit')]")
    private WebElement button_Submit;


    //Constructor
    public InputFormCheck(WebDriver driver) throws AWTException {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public Logger logger = Logger.getLogger(InputFormCheck.class);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // creating object of Robot class
    Robot robot = new Robot();

    // creating object of Actions class
    Actions action = new Actions(driver);

    public void openFirstTestPage() throws Exception {

        driver.manage().window().maximize();

        driver.navigate().to("https://www.way2automation.com/way2auto_jquery/registration.php#load_box");

        waitTillDescendentElementsAvailable(15, "//form[contains (@id, 'register_form')]/descendant::fieldset", "LANDING PAGE");

    }


    public void filloutAllFieldsAndControls(String FirstName, String LastName, String PhoneNumber, String UserName, String Email, String AboutYourself, String Password, String ConfirmPassword) {

        try {

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_FirstName);
            input_FirstName.clear();
            //input_FirstName.sendKeys(FirstName);
            js.executeScript("arguments[0].value='" + FirstName + "';", input_FirstName);
            logger.info("FIRST NAME field was SUCCESSFULLY entered!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_LastName);
            input_LastName.clear();
            //input_LastName.sendKeys(LastName);
            js.executeScript("arguments[0].value='" + LastName + "';", input_LastName);
            logger.info("LAST NAME field was SUCCESSFULLY entered!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", radioButton_MaritalStatus);
            js.executeScript("arguments[0].click();", radioButton_MaritalStatus);
            logger.info("MARITAL STATUS radio-button was SUCCESSFULLY selected with the value MARRIED!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", checkBox_Hobby);
            js.executeScript("arguments[0].click();", checkBox_Hobby);
            logger.info("HOBBY check-box was SUCCESSFULLY selected with the value READING!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", selector_Country);
            Select drpCountry = new Select(selector_Country);
            drpCountry.selectByIndex(1);
            logger.info("COUNTRY field was SUCCESSFULLY filled out!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", selector_Month);
            Select drpMonth = new Select(selector_Month);
            drpMonth.selectByValue("1");
            logger.info("MONTH field was SUCCESSFULLY filled out!");
            Thread.sleep(1000);

            Select drpDay = new Select(selector_Day);
            drpDay.selectByValue("1");
            logger.info("DAY field was SUCCESSFULLY filled out!");
            Thread.sleep(1000);

            Select drpYear = new Select(selector_Year);
            drpYear.selectByValue("2014");
            logger.info("YEAR field was SUCCESSFULLY filled out!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_PhoneNumber);
            input_PhoneNumber.clear();
            //input_PhoneNumber.sendKeys(PhoneNumber);
            js.executeScript("arguments[0].value='" + PhoneNumber + "';", input_PhoneNumber);
            logger.info("PHONE NUMBER field was SUCCESSFULLY entered!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_UserName);
            input_UserName.clear();
            //input_UserName.sendKeys(UserName);
            js.executeScript("arguments[0].value='" + UserName + "';", input_UserName);
            logger.info("USER NAME field was SUCCESSFULLY entered!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_Email);
            input_Email.clear();
            //input_Email.sendKeys(Email);
            js.executeScript("arguments[0].value='" + Email + "';", input_Email);
            logger.info("EMAIL field was SUCCESSFULLY entered!");
            Thread.sleep(1000);

            attachPictureFile();//upload picture
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_AboutYourself);
            input_AboutYourself.clear();
            //input_AboutYourself.sendKeys(AboutYourself);
            js.executeScript("arguments[0].value='" + AboutYourself + "';", input_AboutYourself);
            logger.info("ABOUT YOURSELF field was SUCCESSFULLY entered!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_Password);
            input_Password.clear();
            //input_Password.sendKeys(Password);
            js.executeScript("arguments[0].value='" + Password + "';", input_Password);
            logger.info("PASSWORD field was SUCCESSFULLY entered!");
            Thread.sleep(1000);

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", input_ConfirmPassword);
            input_ConfirmPassword.clear();
            //input_ConfirmPassword.sendKeys(ConfirmPassword);
            js.executeScript("arguments[0].value='" + ConfirmPassword + "';", input_ConfirmPassword);
            logger.info("CONFIRM PASSWORD field was SUCCESSFULLY entered!");
            Thread.sleep(1000);


        } catch (Exception e) {
            logger.error("Failure to fill out all of the required fields on the LANDING page: " + e.getMessage());
        }
    }


    public void attachPictureFile() {

        try {

            WebElement input_YourProfilePicture = driver.findElement(By.xpath("//form[contains (@id, 'register_form')]/descendant::label[contains (text(), 'Your Profile Picture')]/ancestor::fieldset[1]/input[contains (@type, 'file')]"));

            action.moveToElement(input_YourProfilePicture).click().build().perform();
            //input_YourProfilePicture.click(); // Click on browse option on the webpage
            //js.executeScript("arguments[0].click();", input_YourProfilePicture);
            Thread.sleep(2000); // suspending execution for specified time period
            logger.info("ATTACH PICTURE PROFILE button was clicked.");


            // copying File path to Clipboard
            StringSelection str = new StringSelection("C:\\Temp\\TestTask_AGrischenko\\src\\main\\resources\\picture\\profile.png");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            // press Contol+V for pasting
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            // release Contol+V for pasting
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            // for pressing and releasing Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            logger.info("PROFILE picture was attached.");


        } catch (Exception e) {
            logger.error("Unable to upload profile picture: " + e.getMessage());
        }

    }


    public void submitForm() {

        try{

            //scroll the field to be visible (if outside of vision portal) and click on it
            js.executeScript("arguments[0].scrollIntoView(true);", button_Submit);
            //action.moveToElement(button_Submit).click().build().perform();
            //button_Submit.click();
            js.executeScript("arguments[0].click();", button_Submit);

            logger.info("SUBMIT button was clicked.");

            waitForExtAjaxIsReadyState(20, "DATA SUBMISSION COMPLETE");

        }catch(Exception e){
            logger.error("Error while clicking on SUBMIT button: " + e.getMessage());
        }

    }


}
