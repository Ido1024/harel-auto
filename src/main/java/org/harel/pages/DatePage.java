package org.harel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatePage extends BasePage {

    private YearMonth monthCurrentlyOnRight;

    public DatePage(WebDriver driver) {
        super(driver);
    }

    public final By startDateField = By.cssSelector("div[data-hrl-bo='bo-textField-startDateInput']");
    public final By endDateField = By.cssSelector("div[data-hrl-bo='bo-textField-endDateInput']");
    public final By prevMonthArrow =
            By.cssSelector("button[data-hrl-bo='arrow-back']:not([data-hide='true'])");
    public final By nextMonthArrow =
            By.cssSelector("button[data-hrl-bo='arrow-forward']:not([data-hide='true'])");
    private final By nextButton = By.cssSelector("button[data-hrl-bo='wizard-next-button']");
    private final By totalDaysLocator = By.cssSelector("span[data-hrl-bo='total-days']");

    public By dayButton(String yyyyMMdd) {
        return By.cssSelector("button[data-hrl-bo='" + yyyyMMdd + "']");
    }

    public void selectTripDates(int daysToStart, int daysToEnd) {
        LocalDate today = LocalDate.now();
        monthCurrentlyOnRight = YearMonth.from(today);

        LocalDate startDate = today.plusDays(daysToStart);
        LocalDate endDate = startDate.plusDays(daysToEnd);

        click(startDateField);
        selectDay(startDate);
        click(endDateField);
        selectDay(endDate);
    }

    private void selectDay(LocalDate targetDate) {
        YearMonth targetMonth = YearMonth.from(targetDate);

        long monthsDiff = ChronoUnit.MONTHS.between(monthCurrentlyOnRight, targetMonth);

        if (monthsDiff > 1) {
            long clicksNeeded = monthsDiff - 1;
            for (int i = 0; i < clicksNeeded; i++) {
                click(nextMonthArrow);
                monthCurrentlyOnRight = monthCurrentlyOnRight.plusMonths(1);
            }
        }
        String formattedDate = targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        click(dayButton(formattedDate));
    }

    public String getTotalDaysText() {
        return getText(totalDaysLocator);
    }

    public boolean verifyTotalDaysContains(int expectedDays) {
        String actualText = getTotalDaysText();
        return actualText.contains(String.valueOf(expectedDays));
    }

    public void clickNextButton() {
        click(nextButton);
    }
}