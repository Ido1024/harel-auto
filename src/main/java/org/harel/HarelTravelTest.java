package org.harel;

import org.harel.infra.SeleniumUtils;
import org.harel.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertTrue;

/**
 * TestNG test that opens the Harel Travel Policy page.
 */
public class HarelTravelTest {

    private SeleniumUtils seleniumUtils;
    private WebDriver driver;
    private static final String URL = "https://digital.harel-group.co.il/travel-policy";
    private static final String TRAVELERS_PAGE_TITLE =
            "מילוי פרטי הנוסעים | רכישת ביטוח נסיעות לחו\"ל";

    @BeforeMethod
    public void setUp() {
        seleniumUtils = new SeleniumUtils();
        seleniumUtils.initDriver();
        driver = seleniumUtils.getDriver();
    }

    @Test
    public void openTravelPolicyPage() {
        HomePage homepage = new HomePage(driver);
        DestinationPage destinationPage = new DestinationPage(driver);
        DatePage datePage = new DatePage(driver);
        TravelersPage travelersPage = new TravelersPage(driver);

        int daysToStart = 7, daysToEnd = 29, expectedDays = 30;

        driver.get(URL);
        homepage.clickFirstTimePurchaseButton();
        destinationPage.clickCanadaOption();
        destinationPage.waitForLoaderToDisappear();
        destinationPage.clickNextButton();
        datePage.selectTripDates(daysToStart, daysToEnd);
        assertTrue("Total days does not contain 30. Found: " + datePage.getTotalDaysText(),
                datePage.verifyTotalDaysContains(expectedDays));
        datePage.clickNextButton();
        assertTrue("Travelers page was not opened", travelersPage.isTravelersPageOpened(TRAVELERS_PAGE_TITLE));
    }

    @AfterMethod
    public void tearDown() {
        seleniumUtils.quitDriver();
    }
}