Feature: Login
  As user, I want to be able to login into vytrack
  under different roles with username and password

  # any step that was implemented before, can be reused
  # If test step has yellow background, it means thtat
  #  it doesn't have implementation yet. clcik command
  #  + option + L to organize code, save like in java.
  # window: ctrl + alt + L
  # I had somewhere from 2 to 25 scenarios in every
  #  feature file.
  # By passing parameters/ strings in "" we can reuse test
  #  steps.

  Scenario: Login as store manager
    Given user is one the landing page
    Then user logs in as store manager
    And user verifies that "Dashboard" page subtitle is displayed


  Scenario: Login as driver
    Given user is on the login page
    Then user logs in as driver
    And user verifies that "Dashboard" page subtitle is displayed


  Scenario:  Login as sales manager
    Given user is on the login page
    Then user logs in as sales manager
    And user verifies that "Dashboard" page subtitle is displayed



