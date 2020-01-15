package com.vytrack.step_definitions; // 122019

import com.vytrack.pages.CalendarEventsPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class CalendarEventsStepDefinitions {

 /*
    And user Verifies that column names are displayed
            |TITLE			    |
            |CALENDAR		    |
            |START			    |
            |END			    |
            |RECURRENT		    |
            |RECURRENCE		    |
            |INVITATION STATUS  |

    This data in |  | will be in dataTable of #3
*/

 CalendarEventsPage calendarEventsPage = new CalendarEventsPage(); // 5


 @Then("user navigates to “Activities” then to “Calendar Events”") //1
    public void user_navigates_to_Activities_then_to_Calendar_Events() { // 1

    }

    @Then("user Verifies that column names are displayed") // 1
   //  public void user_Verifies_that_column_names_are_displayed(io.cucumber.datatable.DataTable dataTable) { // 2
        public void user_Verifies_that_column_names_are_displayed(List<String> dataTable) { // 3
        // instead of #2, use #3
        System.out.println(dataTable); // 4
        calendarEventsPage.waitUntilLoaderMaskDisappear(); // 7
        BrowserUtils.wait(3); // 8
        Assert.assertEquals(dataTable, calendarEventsPage.getColumnNames()); // 6

        }


}
