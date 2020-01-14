package com.vytrack.pages; // 120219

// according to page object model design, we have to create corresponded
//  page class for each page of application.
// Login page = login page class
// Every page class will store webelements and methods related to that page.

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage{
    // After create BasePage (in pages), added "extends BasePage"

    @FindBy(id = "prependedInput") // 4
    // this line will initialize web element

    public WebElement userNameInput; // 3

    @FindBy(id = "prependedInput2") // 6
    // without @FindBy, web element will be null

    public WebElement passwordInput; // 5

    @FindBy(id = "_submit") // 11
    public WebElement loginButton; // 10

    @FindBy(css = "[class='alert alert-error']") // 13
    public WebElement warningMessage; // 12

    public LoginPage(){ // 1
        PageFactory.initElements(Driver.get(), this); // 2
        // it's mandatory if you want to use @FindBy annotation.
        // this -> current class (LoginPage here)
        // Driver.get() returns webdriver object.
    }

    /*
    Reusable login method
    Call this method and provide username and password as a parameter
     to login.
     */
    public void login(String userName, String password){ // 7
        userNameInput.sendKeys(userName); // 8
        passwordInput.sendKeys(password, Keys.ENTER); // 9
        // Keys.Enter -> it's like clicking Login button in the website.
    }

    public void login(){ // 14
        login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password")); // 14
    }

    public void login(String role){ // 14
        String userName = "";
        String password = ConfigurationReader.getProperty("password");

        switch (role) {
            case "driver":
                userName = ConfigurationReader.getProperty("driver.username");
                break;
            case "store manager":
                userName = ConfigurationReader.getProperty("store.manager.username");
                break;
            case "sales manager":
                userName = ConfigurationReader.getProperty("sales.manager.username");
                break;
            default:
                throw new RuntimeException("Invalid role!");
        }
        login(userName, password);
    }






}
