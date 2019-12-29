package com.vytrack.step_definitions; // 121719

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

    @Before // 6
    // Must use @Before (io.cucumber.java)

    public void setup(){ // 1
        System.out.println("Test setup!"); // 2
    }

    @After // 7
    // Must use @After (io.cucumber.java)

    public void teardown(){ // 3
        System.out.println("Clean up"); // 4
        System.out.println("Test completed!"); // 5
    }
}
