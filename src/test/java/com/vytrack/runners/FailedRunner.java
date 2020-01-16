package com.vytrack.runners; // 123019


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue = "com/vytrack/step_definitions",
        plugin = {
                "html:target/rerun-default-cucumber-reports",
        }
) // we don't need "tags=" and "dryRun"

        public class FailedRunner {
}
