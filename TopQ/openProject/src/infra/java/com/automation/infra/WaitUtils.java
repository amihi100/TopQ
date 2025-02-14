package com.automation.infra;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUtils {
    private final WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 30;

    public WaitUtils(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.getWaitTimeoutSeconds()));
    }

    public WebElement waitForElementClickable(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForUrlContains(String fraction) {
        if (fraction == null || fraction.isEmpty()) {
            throw new IllegalArgumentException("URL fraction cannot be null or empty");
        }
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    public static void waitForPageLoad(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance cannot be null");
        }
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

}