package org.harel.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    private WebDriverWait getWait() {
        return getWait(DEFAULT_TIMEOUT);
    }

    protected WebElement waitForVisible(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForVisible(By locator) {
        return waitForVisible(locator, DEFAULT_TIMEOUT);
    }


    protected WebElement waitForClickable(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return waitForClickable(locator, DEFAULT_TIMEOUT);
    }


    protected boolean waitForDisappear(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForDisappear(By locator) {
        return waitForDisappear(locator, DEFAULT_TIMEOUT);
    }

    protected boolean waitForInvisibilityOf(By locator, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected boolean waitForInvisibilityOf(By locator) {
        return waitForInvisibilityOf(locator, DEFAULT_TIMEOUT);
    }


    protected void click(By locator, int timeoutInSeconds) {
        WebElement element = waitForClickable(locator, timeoutInSeconds);
        element.click();
    }

    protected void click(By locator) {
        click(locator, DEFAULT_TIMEOUT);
    }

    protected void sendKeys(By locator, String text, int timeoutInSeconds) {
        WebElement element = waitForVisible(locator, timeoutInSeconds);
        element.clear();
        element.sendKeys(text);
    }

    protected void sendKeys(By locator, String text) {
        sendKeys(locator, text, DEFAULT_TIMEOUT);
    }

    protected String getText(By locator, int timeoutInSeconds) {
        return waitForVisible(locator, timeoutInSeconds).getText();
    }

    protected String getText(By locator) {
        return getText(locator, DEFAULT_TIMEOUT);
    }

    protected boolean isDisplayed(By locator, int timeoutInSeconds) {
        try {
            return waitForVisible(locator, timeoutInSeconds).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean isDisplayed(By locator) {
        return isDisplayed(locator, DEFAULT_TIMEOUT);
    }

    protected void jsClick(By locator, int timeoutInSeconds) {
        WebElement element = waitForVisible(locator, timeoutInSeconds);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void jsClick(By locator) {
        jsClick(locator, DEFAULT_TIMEOUT);
    }

    protected void scrollTo(By locator, int timeoutInSeconds) {
        WebElement element = waitForVisible(locator, timeoutInSeconds);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void scrollTo(By locator) {
        scrollTo(locator, DEFAULT_TIMEOUT);
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void refreshPage() {
        driver.navigate().refresh();
    }
}
