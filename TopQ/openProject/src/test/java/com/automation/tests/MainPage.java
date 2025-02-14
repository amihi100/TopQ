package com.automation.tests;

import com.automation.infra.LogUtils;
import com.automation.infra.WaitUtils;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class MainPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By initialSignInButton = By.linkText("Sign In");

    private void clickElement(By locator) {
        LogUtils.info("Clicking initial Sign In button");
        wait.waitForElementClickable(driver.findElement(locator)).click();
    }

    public void navigateToMainPage(String baseUrl) {
        LogUtils.info("Opening website: " + baseUrl);
        driver.get(baseUrl);
    }

    public LoginPage clickSignIn() {
        clickElement(initialSignInButton);
        return new LoginPage(driver, wait);
    }
}