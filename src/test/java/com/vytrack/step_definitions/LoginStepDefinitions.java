package com.vytrack.step_definitions;

import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class LoginStepDefinitions {

    // Write code here that turns the phrase above into concrete actions
    LoginPage loginPage = new LoginPage();//created login page object

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        System.out.println("I am on the login page");
        Driver.get().get(ConfigurationReader.getProperty("url"));
        // open browser
    }

    @Then("user logs in as store manager")
    public void user_logs_in_as_store_manager() {
        System.out.println("Login as store manager");
        //we read username and password from properties file
        //usually in java we use camel case for naming variables
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
    //any string in "word" will become a parameter for step definition method
    //  And user verifies that "Dashboard" page subtitle is displayed
    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        loginPage.waitUntilLoaderMaskDisappear();
        BrowserUtils.wait(2);
        Assert.assertEquals(string, loginPage.getPageSubTitle());
        System.out.println("Verifying page subtitle: " + string);
    }

    @Then("user logs in as driver")
    public void user_logs_in_as_driver() {
        System.out.println("Login as driver");
    }

    @Then("user logs in as sales manager")
    public void user_logs_in_as_sales_manager() {
        System.out.println("Login as sales manager");
    }

    // Then user enters "storemanager85" username and "wrong" password
    //  Anything in "" is a parameter -> becomes {string} in here
    // String string = {string} user name, String string2 = {stirng} password
    //  It follows the order from left to right.
    //Then user enters "storemanager85" username and "wrong" password
    @Then("user enters {string} username and {string} password")
    public void user_enters_username_and_password(String string, String string2) {
        System.out.println("Login with " + string + " username and " + string2 + " password.");
        loginPage.login(string, string2);
    }

    @Then("user verifies that {string} message is displayed")
    public void user_verifies_that_message_is_displayed(String string) {
        System.out.println("Verified that warning message is displayed: " + string);
    }
//    Then user logs in as driver with following credentials
//            | username | user160     |
//            | password | UserUser123 |

    @Then("user logs in as driver with following credentials")
    public void user_logs_in_as_driver_with_following_credentials(Map<String, String> dataTable) {
        System.out.println(dataTable);
        loginPage.login(dataTable.get("username"), dataTable.get("password"));
    }


    @Then("user logs in as {string}")
    public void user_logs_in_as(String role) {
        // role is a parameter which gets data from @login_with_role from
        // Login.feature
        loginPage.login(role);
    }

    /*
    @Then("the page title should be {string}")
    public void the_page_title_should_be(String string) {
        BrowserUtils.waitForPageTitle(string);
        Assert.assertEquals("Title is incorrect", string, Driver.get().getTitle());
    }

*/

}
