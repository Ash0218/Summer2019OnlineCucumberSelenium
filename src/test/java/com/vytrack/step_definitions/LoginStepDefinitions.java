package com.vytrack.step_definitions; // 121719

import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginStepDefinitions {
    // write code here that turns the phrase above into concrete actions

    LoginPage loginPage = new LoginPage();
    // created login page object

    @Given("user is one the landing page")
    public void user_is_one_the_landing_page() {
        System.out.println("I amd on the login page");
        Driver.get().get(ConfigurationReader.getProperty("url"));
        // open browser
    }

    @Then("user logs in as store manager")
    public void user_logs_in_as_store_manager() {
        System.out.println("Login as store manager");
        String userName = ConfigurationReader.getProperty("user_name");
        // user_name is from Configuration.properties
        String password = ConfigurationReader.getProperty("password");
        // password is from Configuration.properties
        // in java, we use camel case (ex: userName) for naming variables.

        loginPage.login(userName, password);
        // login
    }
    // Add user verifies that "Dashboard" page subtitle is displayed
    // any String in "" will become a parameter for step definition method.
    // "Dashboard" -> changed to {string}
    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        System.out.println(string);
        loginPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.wait(2);
        Assert.assertEquals(string, loginPage.getPageSubTitle());
        System.out.println("Verifying page subtitle: " + string);
    }

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        System.out.println("I am on the login page");
    }

    @Then("user logs in as driver")
    public void user_logs_in_as_driver() {
        System.out.println("Login as a driver");
    }

    @Then("user logs in as sales manager")
    public void user_logs_in_as_sales_manager() {
        System.out.println("Login as a sales manager");
    }

    // Then user enters "storemanager85" username and "wrong" password
    //  Anything in "" is a parameter -> becomes {string} in here
    // String string = {string} user name, String string2 = {stirng} password
    //  It follows the order from left to right.
    @Then("user enters {string} username and {string} password")
    public void user_enters_username_and_password(String string, String string2) {
        System.out.println("Login with "+ string +" username and " + string2 + " password.");
    }

    @Then("user verifies that {string} message is displayed")
    public void user_verifies_that_message_is_displayed(String string) {
        System.out.println("Verified that warning message is displayed: "+string);
    }

}
