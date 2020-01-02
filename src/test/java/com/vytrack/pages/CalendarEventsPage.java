package com.vytrack.pages; // 120319

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CalendarEventsPage extends BasePage{ // 1
    // add extends BasePage

    @FindBy(css = "[title='Create Calendar event']") // 3
    public WebElement createCalendarEvent; // 2

    public void clickToCreateCalendarEvent(){ // 4
        BrowserUtils.waitForVisibility(createCalendarEvent, 5); // 6
        BrowserUtils.waitForClickablility(createCalendarEvent, 5); // 7
        createCalendarEvent.click(); // 5
    }
}
