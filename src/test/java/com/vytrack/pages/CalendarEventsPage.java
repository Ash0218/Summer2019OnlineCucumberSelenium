package com.vytrack.pages; // 120319

import com.vytrack.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CalendarEventsPage extends BasePage{ // 1
    // add extends BasePage

    @FindBy(css = "[title='Create Calendar event']") // 3
    public WebElement createCalendarEvent; // 2

    @FindBy(css = "span[class='grid-header-cell__label']") // 9
    public List<WebElement> columnNames; // 8

    @FindBy(css = "button[class*='btn dropdown-toggle']") // 13
    public WebElement viewPerPageToggle; // 12

    @FindBy(css = "[class*='btn-group'] [class='dropdown-menu pull-right'] li") // 15
    public List<WebElement> viewPerPageOptions; // 14

    public void clickToCreateCalendarEvent(){ // 4
        BrowserUtils.waitForVisibility(createCalendarEvent, 5); // 6
        BrowserUtils.waitForClickablility(createCalendarEvent, 5); // 7
        createCalendarEvent.click(); // 5
    }

    public List<String> getColumnNames(){ // 10
        return BrowserUtils.getListOfString(columnNames); // 11
    }

    public List<String> getViewPerPageOptions(){ // 16
        BrowserUtils.waitForVisibility(viewPerPageToggle, 10); // 17
        BrowserUtils.clickWithWait(viewPerPageToggle); // 18
        return BrowserUtils.getListOfString(viewPerPageOptions); // 19

    }
}
