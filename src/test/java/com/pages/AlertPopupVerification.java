package com.pages;

import init.settings.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ax.generic.Waitings.waitTillAlertIsDisplayed;
import static ax.generic.Waitings.waitTillWebElementAvailable;

public class AlertPopupVerification extends PageObject {


    //Locating iframe
    @FindBy(xpath = "//iframe[contains (@src, 'alert/simple-alert.html')]")
    private WebElement iFrame;

    //Locating "Display Alert" button
    @FindBy(xpath = "//button[contains (text(), 'Click the button to display an alert box')]")
    private WebElement button_DisplayAlert;





    //Constructor
    public AlertPopupVerification(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Logger logger = Logger.getLogger( AlertPopupVerification.class);

    JavascriptExecutor js = (JavascriptExecutor) driver;

    // creating object of Actions class
    Actions action = new Actions(driver);


    public void openSecondTestPage() throws Exception {

        driver.manage().window().maximize();

        driver.navigate().to("https://www.way2automation.com/way2auto_jquery/alert.php#load_box");

        driver.switchTo().frame(iFrame);

        waitTillWebElementAvailable (15, button_DisplayAlert, "LANDING PAGE");
        driver.switchTo().defaultContent();

    }


    public void clickDisplayAlertButton() {

        try {

            driver.switchTo().frame(iFrame);

            //action.moveToElement(button_DisplayAlert).click().build().perform();
            //button_DisplayAlert.click();
            js.executeScript("arguments[0].click();", button_DisplayAlert);

            logger.info("DISPLAY ALERT button was clicked.");

            waitTillAlertIsDisplayed();
            Thread.sleep(2000);


       }catch(Exception e){

           logger.error("Unable to click on 'DISPLAY ALERT' button: " + e.getMessage());

        }
    }


    public void verifyAlertTextAndCloseAlertPopup(){

        try{

            Alert alert = driver.switchTo().alert();

            String alertText = alert.getText();
            System.out.println("ALERT POPUP has the following text: " + alertText);

           if (alertText.contains("I am an alert box!")){//if the ALERT popup text is successfully verified, then close the Alert popup

               logger.info("The text on the Alert Popup was SUCCESSFULLY verified!");

               alert.accept();

               logger.info("OK button was clicked on the Alert Popup.");

           }else{
               logger.error("Text verification on ALERT popup has FAILED i.e. EXPECTED text is 'I am an alert box!', but the ACTUAL text is '" +alertText+"'.");
           }

           driver.switchTo().defaultContent();

        }catch(Exception e){
            logger.error("Unable to retrieve the text from the ALERT POPUP: " + e.getMessage());
        }
    }

}
