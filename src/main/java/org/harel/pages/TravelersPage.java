package org.harel.pages;

import org.openqa.selenium.WebDriver;

public class TravelersPage extends BasePage {

    public TravelersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTravelersPageOpened(String expectedPageTitle) {
        return getPageTitle().contains(expectedPageTitle);
    }
}
