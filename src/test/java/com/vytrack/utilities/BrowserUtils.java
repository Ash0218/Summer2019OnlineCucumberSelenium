package com.vytrack.utilities; // two



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class BrowserUtils {

    // It will be used to pause our execution. Just provide number of seconds
    //  as a parameter.
    public static void wait(int seconds){ // 1
        try { // 3
            Thread.sleep(1000*seconds); // 2
        } catch (InterruptedException e) { // 3
            // Added try & catch by using option + enter: #3,4 automatically created
            e.printStackTrace();  // 4
        }
    }
    
    /*
    Wait for element not to be stale.
     */
    public static void waitForStaleElement(WebElement element){ // 5
        int y = 0; // 6
        while (y <= 15){ // 7
            try {  // 8
                element.isDisplayed(); // 9
                break; // 10
            } catch (StaleElementReferenceException st){ // 11
                y++; // 12
                try { // 13
                    Thread.sleep(200); // 14
                } catch (InterruptedException e) { // 15
                    e.printStackTrace(); // 16
                }
            }
            break; // 17
        }
    }
    /*
    Waits for the provided element to be visible on the page.
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec){ // 18
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeToWaitInSec); // 19
        return wait.until(ExpectedConditions.visibilityOf(element)); // 20
    }

    // clicks on element which is using JavaScript
    public static void clickWithJS(WebElement element) {// 21
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element); // 22
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].click();", element); // 23
    }

    /*
    Waits for provided element to be clickable
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) { // 24
        WebDriverWait wait = new WebDriverWait(Driver.get(), timeout);  // 25
        return wait.until(ExpectedConditions.elementToBeClickable(element)); // 26
    }

    /*
    takes screenshot: Whenever you call this method, it takes
     screenshot and returns location of the screenshot.
    take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name)  { // 28

        // name the screenshot with the current date time to avoid duplicate name.
        // String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 29
        String date = df.format(new Date()); // 30

        // TakeScreenshot -> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) Driver.get(); // 31
        File source = ts.getScreenshotAs(OutputType.FILE); // 31
        // where screenshot will be stored

        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png"; // 31
        // System.getProperty("user.dir") -> returns path to the project as a String

    //    String target2 = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + ".png"; // 44
        // If it doesn't take screenshot, remove date and time part from #31.
        //  And use #44

        File finalDestination = new File(target); // 32

        // save the screenshot to the path given
        try{ // 33
            FileUtils.copyFile(source, finalDestination); // 34
        } catch (IOException e) { // 34
            e.printStackTrace(); // 34
        }

        return target; // 37

    }
/*
    public static void main(String[] args) { // 38
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm"); // 39
        // of it can be "MMM-dd-yyyy HH:mm:ss" -> Jan-01-2020 14:24:22

        String date = df.format(new Date()); // 40
        System.out.println(date); // 41
        // -> 01/01/2020 14:24
    }
*/
/*
    public static void main(String[] args) { // 42
        System.out.println(System.getProperty("user.dir")); // 43
        // -> /Applications/IntelliJ IDEA CE.app/Summer2019OnlineTestNGSeleniumProject
        //  it shows current location.
        // "user.dir" is from #31
    }
*/

    /**
     * Wait 15 seconds with polling interval of 200 milliseconds then click
     *
     * @param webElement of element
     */
    public static void clickWithWait(WebElement webElement) {
        Wait wait = new FluentWait<>(Driver.get())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(800))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(WebDriverException.class);
        WebElement element = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> webElement);
        try {
            element.click();
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Wait for proper page title
     *
     * @param pageTitle
     */
    public static void waitForPageTitle(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }


    /**
     * This method will convert list of WebElements into list of string
     *
     * @param listOfWebElements
     * @return list of strings
     */
    public static List<String> getListOfString(List<WebElement> listOfWebElements) {
        List<String> listOfStrings = new ArrayList<>();
        for (WebElement element : listOfWebElements) {
            String value = element.getText().trim();
            //if there is no text
            //do not add this blank text into list
            if (value.length() > 0) {
                listOfStrings.add(value);
            }
        }
        return listOfStrings;
    }




}
