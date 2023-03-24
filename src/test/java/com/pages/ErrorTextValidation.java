package com.pages;

import init.settings.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ax.generic.Waitings.*;

public class ErrorTextValidation extends PageObject {

    //Locating "Text Error Validation" form
    @FindBy(xpath = "//h3[contains (text(), 'Form with Validations')]")
    private WebElement form_Validations;

    //Locating "Submit Form" button
    @FindBy(xpath = "//button[contains (text(), 'Submit Form')]")
    private WebElement button_SubmitForm;

    //Locating "City" input field
    @FindBy(xpath = "//input[contains (@placeholder, 'City')]")
    private WebElement input_City;

    //Locating "State" input field
    @FindBy(xpath = "//input[contains (@placeholder, 'State')]")
    private WebElement input_State;

    //Locating "ZIP" input field
    @FindBy(xpath = "//input[contains (@placeholder, 'Zip')]")
    private WebElement input_Zip;

    //Locating check-box "Agree to Terms and Conditions"
    @FindBy(xpath = "//label[contains (text(), 'Agree to terms and conditions')]")
    private WebElement checkBox_Agree;


    //Constructor
    public ErrorTextValidation(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Logger logger = Logger.getLogger(ErrorTextValidation.class);

    JavascriptExecutor js = (JavascriptExecutor) driver;

    // creating object of Actions class
    Actions action = new Actions(driver);


    public void openThirdTestPage() throws Exception {

        driver.manage().window().maximize();

        driver.navigate().to("https://dineshvelhal.github.io/testautomation-playground/forms.html");

        waitTillWebElementAvailable(15, form_Validations, "LANDING PAGE");

        //scroll the field to be visible (if outside of vision portal)
        js.executeScript("arguments[0].scrollIntoView(true);", form_Validations);

    }


    public void clickSubmitFormButton() {

        try {

            //action.moveToElement(button_SubmitForm).click().build().perform();
            //button_SubmitForm.click();
            js.executeScript("arguments[0].click();", button_SubmitForm);

            logger.info("SUBMIT FORM button was clicked.");

            waitTillElementDetectedByProperty(15, "//input[contains (@placeholder, 'City')]/ancestor::form[1]", "class", "was-validated", "Updated 'FORM WITH VALIDATIONS'");
            Thread.sleep(2000);

        } catch (Exception e) {

            logger.error("Unable to click on 'SUBMIT FORM' button: " + e.getMessage());

        }
    }


    public void verifyEachErrorText(String City, String State, String Zip) {

        boolean nonFilledInput = false;

        try {

            String borderColor_City = input_City.getCssValue("border-color");
            System.out.println("Border color of the CITY input field is: " + borderColor_City);

            if (borderColor_City.equals("rgb(255, 0, 57)")) {// if the border is red

                logger.warn("CITY field has not been filled in! It will be filled in automatically.");

                input_City.clear();
                //input_FirstName.sendKeys(FirstName);
                js.executeScript("arguments[0].value='" + City + "';", input_City);
                logger.info("CITY input field was SUCCESSFULLY filled with the value '" + City.toUpperCase() + "'!");
                Thread.sleep(1000);

                nonFilledInput = true;

            } else {

                logger.warn("CITY field has been already filled in with the value of '" + input_City.getAttribute("value").toUpperCase() + "'.");

            }


            String borderColor_State = input_State.getCssValue("border-color");
            System.out.println("Border color of the STATE input field is: " + borderColor_State);

            if (borderColor_State.equals("rgb(255, 0, 57)")) {// if the border is red

                logger.warn("STATE field has not been filled in! It will be filled in automatically.");

                input_State.clear();
                //input_FirstName.sendKeys(FirstName);
                js.executeScript("arguments[0].value='" + State + "';", input_State);
                logger.info("STATE input field was SUCCESSFULLY filled with the value '" + State.toUpperCase() + "'!");
                Thread.sleep(1000);

                nonFilledInput = true;

            } else {

                logger.warn("STATE field has been already filled in with the value of '" + input_State.getAttribute("value").toUpperCase() + "'.");

            }


            String borderColor_Zip = input_Zip.getCssValue("border-color");
            System.out.println("Border color of the ZIP input field is: " + borderColor_Zip);

            if (borderColor_State.equals("rgb(255, 0, 57)")) {// if the border is red

                logger.warn("ZIP field has not been filled in! It will be filled in automatically.");

                input_Zip.clear();
                //input_Zip.sendKeys(Zip);
                js.executeScript("arguments[0].value='" + Zip + "';", input_Zip);
                logger.info("ZIP input field was SUCCESSFULLY filled with the value '" + Zip.toUpperCase() + "'!");
                Thread.sleep(1000);

                nonFilledInput = true;

            } else {

                logger.warn("ZIP field has been already filled in with the value of '" + input_Zip.getAttribute("value").toUpperCase() + "'.");

            }


            String color_CheckBoxLabel = checkBox_Agree.getCssValue("color");
            System.out.println("Color of the label text in check-box AGREE WITH TERMS AND CONDITIONS is: " + color_CheckBoxLabel);

            if (color_CheckBoxLabel.equals("rgba(255, 0, 57, 1)")) {// if the border is red

                logger.warn("The check-box AGREE WITH TERMS AND CONDITIONS has not been selected! It will be selected automatically.");

                js.executeScript("arguments[0].click();", checkBox_Agree);
                logger.info("The check-box AGREE WITH TERMS AND CONDITIONS has been selected.");
                Thread.sleep(1000);

                nonFilledInput = true;

            } else {

                logger.warn("The check-box AGREE WITH TERMS AND CONDITIONS has already been selected.");

            }

            Thread.sleep(2000);


            if (nonFilledInput == true) {//if at least 1 field was left empty, then click SUBMIT button again

                js.executeScript("arguments[0].click();", button_SubmitForm);

                logger.info("SUBMIT FORM button was clicked.");

                waitTillElementDetectedByProperty(15, "//input[contains (@placeholder, 'City')]/ancestor::form[1]", "class", "needs-validation", "Updated 'FORM WITH VALIDATIONS'");
                Thread.sleep(2000);
            }

        } catch (Exception e) {

            logger.error("Error while verifying validation texts: " + e.getMessage());

        }

    }

}
