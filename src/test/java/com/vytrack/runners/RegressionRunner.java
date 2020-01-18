package com.vytrack.runners; // 010319


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/activities",
                "src/test/resources/features/fleet"
                // Instead of using tags like other runners, I added
                //  2 locations to test Activities and Fleet modules.
        },
        glue = "com/vytrack/step_definitions",
        dryRun = false,
        plugin = {"html:target/default-cucumber-reports",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        }
)

public class RegressionRunner {

}
