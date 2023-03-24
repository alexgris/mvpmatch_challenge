package ax.generic;

import init.settings.SeleniumSetUp;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Waitings extends SeleniumSetUp {

    public static Logger logger = Logger.getLogger(Waitings.class);


    public static void waitTillDescendentElementsAvailable(int maxSeconds, String XPath, String PageName) throws Exception {
        long startTime = System.nanoTime();
        int secondsCounter = 0;
        int elementListSize = 0;

        for (int i = 1; i <= maxSeconds; i++) {

            //Select the parent web element
            List<WebElement> elm = driver.findElements(By.xpath(XPath));

            if (elm.size() == 0) { //wait 1 second if no elements were found
                Thread.sleep(1000);
                secondsCounter++;

                if (secondsCounter == maxSeconds) {//stop waiting when maximum allowed time is exceeded
                    logger.info("Timed out on " + PageName + " after " + maxSeconds + " seconds");
                    break;
                }

            } else {
                if (elm.size() != elementListSize) { //if more elements were found after waiting yet another 1 second then continue waiting till no more new elements found
                    Thread.sleep(1000);
                    elementListSize = elm.size();
                    secondsCounter++;

                    if (secondsCounter == maxSeconds) {
                        logger.info("Timed out on " + PageName + " after " + maxSeconds + " seconds");
                        break;
                    }
                } else {
                    long endTime = System.nanoTime();
                    double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS) / 1000.0;

                    logger.info("TOTAL TIME that the " + PageName + " page was loading: " + elapsedTimeInSeconds + " seconds.");

                    return;
                }
            }

        }
        throw new RuntimeException("Unable to implement waiting algorithm in the 'waitTillDescendentElementsAvailable' method!");
    }


    public static void waitTillElementDetectedByProperty(int maxSeconds, String XPath, String elmntAttribute, String elmAttributeValue, String PageName) throws Exception {
        long startTime = System.nanoTime();
        int secondsCounter = 0;
        boolean statusField = false;

        for (int i = 1; i <= maxSeconds; i++) {

            //Select the parent web element
            WebElement elm = driver.findElement(By.xpath(XPath));
            String statusIndicator = elm.getAttribute(elmntAttribute);
            System.out.println("ELEMENT STATUS after UPDATE: " + statusIndicator);

            //if detected value of the web-element's attribute is not the same as the expected one then wait 1 second more
            if (!statusIndicator.contains(elmAttributeValue)) {
                Thread.sleep(1000);
                secondsCounter++;

                if (secondsCounter == maxSeconds) {
                    logger.info("Timed out on " + PageName + " after " + maxSeconds + " seconds");
                    break;
                }
            } else {
                long endTime = System.nanoTime();
                double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS) / 1000.0;

                logger.info("TOTAL TIME that the " + PageName + " web-element was loading: " + elapsedTimeInSeconds + " seconds.");

                return;
            }
        }
        throw new RuntimeException("Unable to implement waiting algorithm in the 'waitTillDescendentElementsAvailable' method!");
    }


    public static void waitTillWebElementAvailable(int maxSeconds, WebElement el, String PageName) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, maxSeconds); // seconds

        long startTime = System.nanoTime();

        try {

            wait.until(ExpectedConditions.visibilityOf(el));

            long endTime = System.nanoTime();
            double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS) / 1000.0;

            logger.info("TOTAL TIME that " + PageName + " page was loading: " + elapsedTimeInSeconds + " seconds.");
            return;


        } catch (NoSuchElementException e) {
            logger.error(PageName + " page has timed out after " + maxSeconds + " seconds because specified WebElement was not detected: " + e);

        } catch (TimeoutException e) {
            logger.error(PageName + " page has timed out after " + maxSeconds + " seconds: " + e);

        }

        throw new RuntimeException("Unable to implement waiting algorithm in the 'waitTillWebElementsAvailable' method!");
    }


    public static void waitForExtAjaxIsReadyState(int maxSeconds, String PageName) throws Exception {
        boolean is_ajax_complete = false;
        long startTime = System.nanoTime();

        for (int i = 1; i <= maxSeconds; i++) {

            is_ajax_complete = (boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (is_ajax_complete) {

                long endTime = System.nanoTime();
                double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS) / 1000.0;
                logger.info("TOTAL TIME that the " + PageName + " page was loading: " + elapsedTimeInSeconds + " seconds.");

                return;
            }
            Thread.sleep(1000);
        }
        throw new RuntimeException("Timed out on " + PageName + " after " + maxSeconds + " seconds");

    }


    public static void waitTillAlertIsDisplayed() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);

            long startTime = System.nanoTime();

            wait.until(ExpectedConditions.alertIsPresent());

            long endTime = System.nanoTime();
            double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS) / 1000.0;

            logger.info("TOTAL TIME that the ALERT POPUP was loading is : " + elapsedTimeInSeconds + " seconds.");

        } catch (Exception e) {

            logger.error("Timed out on ALERT POPUP due to the following error: " + e.getMessage());

        }

    }

}