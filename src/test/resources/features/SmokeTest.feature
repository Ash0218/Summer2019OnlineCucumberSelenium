@smoke_test # if you put @smoke_test above Feature, then all
    # Scenario will have @smoke_test sign.
Feature: Smoke test

  Background: open login page and login as store manager
    # Background is a common step for one particular feature file.
    Given user is on the login page
    Then user logs in as store manager
    #Given and Then are applied to all Scenarios.

  Scenario: Verify dashboard page
    And user verifies that "Dashboard" page subtitle is displayed

    Scenario: Verify Manage Dashboards page
      And user navigates to "Dashboards" then to "Manage Dashboards"
      Then user verifies that "All Manage Dashboards" page subtitle is displayed
      # syntax such as Then, When, And does not matter -> you can put
      #  anything.

