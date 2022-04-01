package com.qa.ui.pages;

import com.qa.ui.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WebDriverWrapper extends BaseTest {
    public WebDriver driver;
    private static final int TIMEOUT = 40;

    public WebDriverWrapper(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public WebElement findElementByClickAbility(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }

    public WebElement findElementByVisibility(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public Boolean findElementByInvisibility(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        Boolean isInVisibility = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return isInVisibility;
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    // Fluent wait
    public void click(WebElement element) {
        element.click();
    }

    public void click(By locator) {
        WebElement element = findElementByClickAbility(locator);
        element.click();
    }

    public void fill(By locator, String text) {
        WebElement element = findElementByClickAbility(locator);
        element.clear();
        element.sendKeys(text);
    }
}
