package com.vytrack.step_definitions; // 121919

import com.vytrack.pages.LoginPage;
import io.cucumber.java.en.*;
// you can put * if there are many things to import

public class TopMenuStepDefinitions {
    LoginPage loginPage = new LoginPage(); // 2

    @Then("user navigates to {string} then to {string}") // 1
    // user navigates to "Dashboards" then to "Manage Dashboards"
    public void user_navigates_to_then_to(String module, String submodule) { // 1
        // copied from the result of running CucumberRunner.java
        // Change string -> module, string2 -> submodule

        loginPage.navigateTo(module, submodule); // 3


    }

}
