package init.settings;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class SeleniumSetUp {

    public Logger logger = Logger.getLogger(SeleniumSetUp.class);

    public static WebDriver driver;


    @BeforeClass

    @Parameters({"browser"})
    public static void setUp(String browser) throws Exception {


        // If the browser is Chrome, then do this
        if (browser.equalsIgnoreCase("chrome")) {
            File driverFile = new File(".\\src\\chrome_driver\\chromedriver.exe");

            System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());

            //Start Chrome browser with a specific extension
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("disable-notifications");

            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            desiredCapabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);


            driver = new ChromeDriver(desiredCapabilities);
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
    }

    @AfterClass
    public void cleanUp() {

        driver.manage().deleteAllCookies();

    }


    @AfterSuite
    public static void tearDown() {

        try {

            // It will return the parent window name as a String
            String parent = driver.getWindowHandle();
            System.out.println("PARENT TAB: " + parent);
            System.out.println("Name of the PARENT TAB: " + driver.switchTo().window(parent).getTitle());
            String parentTab_name = driver.switchTo().window(parent).getTitle();

            // This will return the number of windows opened by Webdriver and will return Set of St//rings
            Set<String> s1 = driver.getWindowHandles();
            System.out.println("Number of currently opened Web-Browser tabs: " + s1.size());

            // Now we will iterate using Iterator
            Iterator<String> I1 = s1.iterator();

            while (I1.hasNext()) {

                String child_window = I1.next();


                // Here we will compare if parent window is not equal to child window then we will close
                if (!parent.equals(child_window)) {
                    driver.switchTo().window(child_window);

                    String childTab_name = driver.switchTo().window(child_window).getTitle();
                    System.out.println("Name of the CHILD TAB: " + childTab_name);

                    driver.close();
                    System.out.println("CHILD TAB closed : " + childTab_name);
                }

            }

            //Close the Parent TAB
            driver.switchTo().window(parent);
            driver.close();
            System.out.println("PARENT TAB closed : " + parentTab_name);

        } catch (Exception e) {
            System.out.println("Error while closing tabs: " + e.getMessage());
        }
    }
}