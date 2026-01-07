package org.harel.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage class providing common Selenium WebDriver actions with Allure reporting.
 * All methods are annotated with @Step for automatic Allure step tracking.
 * Supports fluent interface pattern for method chaining where applicable.
 */
public class BasePage {

    protected WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Formats a locator to a human-readable string for Allure step descriptions.
     * 
     * @param locator The Selenium By locator
     * @return Human-readable locator string
     */
    private String formatLocator(By locator) {
        String locatorStr = locator.toString();
        // Extract the meaningful part after "By."
        if (locatorStr.contains(": ")) {
            return locatorStr.substring(locatorStr.indexOf(": ") + 2);
        }
        return locatorStr;
    }

    private WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    private WebDriverWait getWait() {
        return getWait(DEFAULT_TIMEOUT);
    }

    @Step("Waiting for element to be visible: [{locator}]")
    protected WebElement waitForVisible(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Waiting for element to be visible: [{locator}]")
    protected WebElement waitForVisible(By locator) {
        return waitForVisible(locator, DEFAULT_TIMEOUT);
    }

    @Step("Waiting for element to be clickable: [{locator}]")
    protected WebElement waitForClickable(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Step("Waiting for element to be clickable: [{locator}]")
    protected WebElement waitForClickable(By locator) {
        return waitForClickable(locator, DEFAULT_TIMEOUT);
    }

    @Step("Waiting for element to disappear: [{locator}]")
    protected boolean waitForDisappear(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Step("Waiting for element to disappear: [{locator}]")
    protected boolean waitForDisappear(By locator) {
        return waitForDisappear(locator, DEFAULT_TIMEOUT);
    }

    @Step("Waiting for element invisibility: [{locator}]")
    protected boolean waitForInvisibilityOf(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    @Step("Waiting for element invisibility: [{locator}]")
    protected boolean waitForInvisibilityOf(By locator) {
        return waitForInvisibilityOf(locator, DEFAULT_TIMEOUT);
    }

    @Step("Clicking on element: [{locator}]")
    protected BasePage click(By locator, int timeoutInSeconds) {
        WebElement element = waitForClickable(locator, timeoutInSeconds);
        element.click();
        return this;
    }

    @Step("Clicking on element: [{locator}]")
    protected BasePage click(By locator) {
        return click(locator, DEFAULT_TIMEOUT);
    }

    @Step("Typing '{text}' into element: [{locator}]")
    protected BasePage sendKeys(By locator, String text, int timeoutInSeconds) {
        WebElement element = waitForVisible(locator, timeoutInSeconds);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    @Step("Typing '{text}' into element: [{locator}]")
    protected BasePage sendKeys(By locator, String text) {
        return sendKeys(locator, text, DEFAULT_TIMEOUT);
    }

    @Step("Getting text from element: [{locator}]")
    protected String getText(By locator, int timeoutInSeconds) {
        return waitForVisible(locator, timeoutInSeconds).getText();
    }

    @Step("Getting text from element: [{locator}]")
    protected String getText(By locator) {
        return getText(locator, DEFAULT_TIMEOUT);
    }

    @Step("Checking if element is displayed: [{locator}]")
    protected boolean isDisplayed(By locator, int timeoutInSeconds) {
        try {
            return waitForVisible(locator, timeoutInSeconds).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Checking if element is displayed: [{locator}]")
    protected boolean isDisplayed(By locator) {
        return isDisplayed(locator, DEFAULT_TIMEOUT);
    }

    @Step("JavaScript clicking on element: [{locator}]")
    protected BasePage jsClick(By locator, int timeoutInSeconds) {
        WebElement element = waitForVisible(locator, timeoutInSeconds);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return this;
    }

    @Step("JavaScript clicking on element: [{locator}]")
    protected BasePage jsClick(By locator) {
        return jsClick(locator, DEFAULT_TIMEOUT);
    }

    @Step("Scrolling to element: [{locator}]")
    protected BasePage scrollTo(By locator, int timeoutInSeconds) {
        WebElement element = waitForVisible(locator, timeoutInSeconds);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    @Step("Scrolling to element: [{locator}]")
    protected BasePage scrollTo(By locator) {
        return scrollTo(locator, DEFAULT_TIMEOUT);
    }

    @Step("Getting page title")
    protected String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Getting current URL")
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Refreshing page")
    protected BasePage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    /**
     * Generic method to get attribute value from element.
     * 
     * @param locator Element locator
     * @param attributeName Attribute name to retrieve
     * @return Attribute value
     */
    @Step("Getting attribute '{attributeName}' from element: [{locator}]")
    protected String getAttribute(By locator, String attributeName) {
        return waitForVisible(locator).getAttribute(attributeName);
    }

    /**
     * Generic method to get attribute value from element with timeout.
     * 
     * @param locator Element locator
     * @param attributeName Attribute name to retrieve
     * @param timeoutInSeconds Timeout in seconds
     * @return Attribute value
     */
    @Step("Getting attribute '{attributeName}' from element: [{locator}]")
    protected String getAttribute(By locator, String attributeName, int timeoutInSeconds) {
        return waitForVisible(locator, timeoutInSeconds).getAttribute(attributeName);
    }

    /**
     * Clears the input field.
     * 
     * @param locator Element locator
     * @return BasePage instance for fluent interface
     */
    @Step("Clearing element: [{locator}]")
    protected BasePage clear(By locator) {
        waitForVisible(locator).clear();
        return this;
    }

    /**
     * Clears the input field with timeout.
     * 
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     * @return BasePage instance for fluent interface
     */
    @Step("Clearing element: [{locator}]")
    protected BasePage clear(By locator, int timeoutInSeconds) {
        waitForVisible(locator, timeoutInSeconds).clear();
        return this;
    }

    /**
     * Checks if element is enabled.
     * 
     * @param locator Element locator
     * @return true if enabled, false otherwise
     */
    @Step("Checking if element is enabled: [{locator}]")
    protected boolean isEnabled(By locator) {
        return waitForVisible(locator).isEnabled();
    }

    /**
     * Checks if element is enabled with timeout.
     * 
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     * @return true if enabled, false otherwise
     */
    @Step("Checking if element is enabled: [{locator}]")
    protected boolean isEnabled(By locator, int timeoutInSeconds) {
        return waitForVisible(locator, timeoutInSeconds).isEnabled();
    }

    /**
     * Checks if element is selected (for checkboxes, radio buttons).
     * 
     * @param locator Element locator
     * @return true if selected, false otherwise
     */
    @Step("Checking if element is selected: [{locator}]")
    protected boolean isSelected(By locator) {
        return waitForVisible(locator).isSelected();
    }

    /**
     * Checks if element is selected with timeout.
     * 
     * @param locator Element locator
     * @param timeoutInSeconds Timeout in seconds
     * @return true if selected, false otherwise
     */
    @Step("Checking if element is selected: [{locator}]")
    protected boolean isSelected(By locator, int timeoutInSeconds) {
        return waitForVisible(locator, timeoutInSeconds).isSelected();
    }
}
