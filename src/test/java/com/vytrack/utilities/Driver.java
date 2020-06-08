package com.vytrack.utilities; // 112619

import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

public class Driver {

    // “ThreadLocal” covers your driver and data and clone it, so it can
    // This is for parallel testing
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver() {

    }

    public static WebDriver get() {
        //if this thread doesn't have a web driver yet - create it and add to pool
        if (driverPool.get() == null) {
            // whenever you add driver, it will duplicate and add to pool
            System.out.println("TRYING TO CREATE DRIVER");
            // this line will tell which browser should open based on the value from properties file
            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigurationReader.getProperty("browser") : browserParamFromEnv;
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your OS doesn't support Safari");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
                case "remote_chrome":
                    try {
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.setCapability("platform", Platform.ANY);
                        driverPool.set(new RemoteWebDriver(new URL("http://ec2-52-90-36-62.compute-1.amazonaws.com:4444/wd/hub"), chromeOptions));
                            // or use this url: http://ec2-54-166-190-92.compute-1.amazonaws.com:4444/wd/hub
                            // or http://localhost:4444/wd/hub
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote_firefox":
                    try {
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                        driverPool.set(new RemoteWebDriver(new URL("http://ec2-54-166-190-92.compute-1.amazonaws.com:4444/wd/hub"), desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "mobile_chrome":
                    try {
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
                        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
                        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
                        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    throw new RuntimeException("Invalid browser name!");
            }

        }
        //return corresponded to thread id webdriver object
        return driverPool.get();
    }

    public static void close() {
        driverPool.get().quit();
        driverPool.remove();
    }






/*
 Before using parallel testing, we used this method:

    private static WebDriver driver; // 1
    // instead of WebDriver, you can use RemoteWebDriver

    // you cannot do this if constructor is private:
    //  Driver obj = new Driver()  -> wrong
    private Driver(){ // 2
    }

    // if switch statement complains on String parameter, change java
    //  version to 7+ -> better at least 8. (click shift twice ->
    //  type java compiler)
    public static WebDriver get(){ // 3
        if (driverPool == null){ // 4
        // if webdriver object was not created yet,

            String browser = ConfigurationReader.getProperty("browser"); // 5
            // create webdriver object based on browser value from properties
            //  file.

                switch (browser){ // 6
                    case "chrome": // 7
                        WebDriverManager.chromedriver().setup(); // 8
                        driver = new ChromeDriver(); // 9
                        break; // 10
                    case "chrome_headless": // 22
                        WebDriverManager.chromedriver().setup(); // 23
                        ChromeOptions options = new ChromeOptions(); // 24
                        // to configure chrome browser for tests

                        options.setHeadless(true); // 25
                        // to run tests without interface, set to true

                        driver = new ChromeDriver(options); // 25
                        // use this instead of #9

                        break; // 25

                    case "firefox": // 12
                        WebDriverManager.firefoxdriver().setup(); // 13
                        driver = new FirefoxDriver(); // 14
                        break; // 15

                    default: // 16
                        // if browser type is wrong, stop tests immediately and
                        //  throw exception, no browser will be opened.
                        throw new RuntimeException("Wrong browser type"); // 17
            }
        }
        return driver; // 11
        // if driver object was created, you can use it.
    }

    public static void close(){ // 18
        // if driver still exist,
        if (driver != null){ // 19
            // close all browser,
            driver.quit(); // 20
            // destroy driver object, ready for gc
            driver = null; // 21
        }
    }

 */
}
