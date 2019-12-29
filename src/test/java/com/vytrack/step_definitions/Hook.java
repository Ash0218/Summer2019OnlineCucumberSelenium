package com.vytrack.step_definitions; // 121719


import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

    @Before // 6
    // Must use @Before (io.cucumber.java)

    public void setup(){ // 1
        System.out.println("################################"); // 8
        System.out.println("Test setup!"); // 2

    }

    @After // 7
    // Must use @After (io.cucumber.java)

    public void teardown(Scenario scenario){ // 3
        // Scenario must from (io.cucumber.core.api)

        if (scenario.isFailed()){ // 10
            System.out.println("Test failed"); // 11
        } else { // 12
            // moved #4,5 into #12 else { }
            // If scenario is failed, then "Test failed" will be printed out.
            //  If not, then #4,5 will be printed out

            System.out.println("Clean up"); // 4
            System.out.println("Test completed!"); // 5
        }
        System.out.println("################################"); // 9

    }
}
