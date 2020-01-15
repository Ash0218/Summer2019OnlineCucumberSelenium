package com.vytrack.runners; // 121719      one


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) // 1
@CucumberOptions( // 2
        features = "src/test/resources/features", // 3
        // added , after create LoginStepDefinitions class
        // path to features
        // After #3, right click features package -> copy path (path to features folder)
        //  cut all things before src

  //      features = "src/test/resources/features/activities/CalendarEvents.feature", // 11

            glue = "com/vytrack/step_definitions", // 4
        // path to step_definitions
        /* After #4,
        I amd on the login page
        Login as store manager
        Verifying page subtitle: Dashboard
         */

            dryRun = false, // 5
         //   tags = "@store_manager", // 6
            // put @store_manager in "", then only store manager part will
            //  be executed.

        //    dryRun = true, // 12

           //     tags = "@smoke_test", // 8
        // replaced #6 to #8.
        // #8 was added after creating SmokeTest.feature in feature
        //  file. Then, change #5 to True (it was false before). Then
        //  click run (on the bottom of this page).

         //   tags = "@calendar_events", // 9
         //       tags = "", // 10

         //   tags = "@driver_with_data_table", // 13

            tags = "@login_with_role", // 14

            plugin = {"html:target/default-cucumber-reports",
                    "json:target/cucumber.json"} // 7
            // added "json:target/cucumber.json" on 121919



        // Run -> it shows all the steps we need (step definition)
        /*
        There were undefined steps. You can implement missing steps with the snippets below:

        @Given("user is one the landing page")
        public void user_is_one_the_landing_page() {
            // Write code here that turns the phrase above into concrete actions
            throw new cucumber.api.PendingException();
        }

        @Then("user logs in as store manager")
        public void user_logs_in_as_store_manager() {
            // Write code here that turns the phrase above into concrete actions
            throw new cucumber.api.PendingException();
        }

        @Then("user verifies that {string} page subtitle is displayed")
        public void user_verifies_that_page_subtitle_is_displayed(String string) {
            // Write code here that turns the phrase above into concrete actions
            throw new cucumber.api.PendingException();
        }
         */
)
public class CucumberRunner {
    // After #1, Run -> WARNING: No features found at classpath:com/vytrack/runners
    // b.c there is nothing yet



}
