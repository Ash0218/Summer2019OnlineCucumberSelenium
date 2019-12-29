package com.vytrack.step_definitions; // 121719

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginStepDefinitions {

    @Given("user is one the landing page")
    public void user_is_one_the_landing_page() {
        System.out.println("I amd on the login page");
    }

    @Then("user logs in as store manager")
    public void user_logs_in_as_store_manager() {
        System.out.println("Login as store manager");
    }
    // Add user verifies that "Dashboard" page subtitle is displayed
    // any String in "" will become a parameter for step definition method.
    // "Dashboard" -> changed to {string}
    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        System.out.println("Verifying page subtitle: " + string);
    }
}
