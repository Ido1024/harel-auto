package org.harel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DestinationPage extends BasePage {

    private final By canadaOptionButton = By.cssSelector("div[data-hrl-bo='canada']");
    private final By nextButton = By.cssSelector("button[data-hrl-bo='wizard-next-button']");
    private final By loader = By.cssSelector("div[data-hrl-bo='loader']");

    public DestinationPage(WebDriver driver) {
        super(driver);
    }

    public void clickCanadaOption() {
        click(canadaOptionButton);
    }

    public void clickNextButton() {
        waitForVisible(nextButton);
        waitForClickable(nextButton);
        click(nextButton);
    }

    public void waitForLoaderToDisappear() {
        waitForInvisibilityOf(loader);
    }
}
