package org.example;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Utils {
    private static int timeout = 30;
    private static int polling_sec = 5;

    public static WebElement fluentWaitVisible(WebDriver driver, By locator, long timeoutInSeconds, long pollingInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingInSeconds))
                .ignoring(NoSuchElementException.class);
        
        WebElement element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)).get(0);

        return element;
    }

    public static WebElement fluentWait(WebDriver driver, By locator, long timeoutInSeconds, long pollingInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingInSeconds))
                .ignoring(NoSuchElementException.class);
        
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        return element;
    }

    public static WebElement fluentWait(WebDriver driver, By locator) {
        return fluentWait(driver, locator, timeout, polling_sec);
    }

    public static void fluentWaitForClickable(WebDriver driver, By locator, long timeoutInSeconds, long pollingInSeconds) {
        WebElement element = fluentWait(driver, locator, timeoutInSeconds, pollingInSeconds);
        
        element.click();
    }

    public static void fluentWaitForClickable(WebDriver driver, By locator) {
        fluentWaitForClickable(driver, locator, timeout, polling_sec);
    }
}
