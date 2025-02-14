package com.automation.tests;

import com.automation.infra.LogUtils;
import com.automation.infra.WaitUtils;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class LoginPage  {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By projectNameInput = By.id("signin-input");
    private final By projectSignInButton = By.xpath("//*[@id=\"signin\"]/input");
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//*[@id='login-form']/form/div[3]/input");

    private void clickElement(By locator, String actionMessage) {
        LogUtils.info(actionMessage);
        wait.waitForElementClickable(driver.findElement(locator)).click();
    }

    private void enterText(By locator, String text, String actionMessage) {
        LogUtils.info(actionMessage);
        wait.waitForElementClickable(driver.findElement(locator)).sendKeys(text);
    }

    public LoginPage enterProjectName(String projectName) {
        enterText(projectNameInput, projectName, "Entering project name: " + projectName);
        return this;
    }

    public LoginPage clickProjectSignIn() {
        clickElement(projectSignInButton, "Clicking project Sign In button");
        return this;
    }

    public LoginPage enterCredentials(String username, String password) {
        enterText(usernameInput, username, "Entering username: " + username);
        enterText(passwordInput, password, "Entering password: " + password.replaceAll(".", "*"));
        return this;
    }

    public void clickLoginButton() {
        clickElement(loginButton, "Clicking login button");
    }
}