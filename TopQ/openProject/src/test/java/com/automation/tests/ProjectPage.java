package com.automation.tests;

import com.automation.infra.LogUtils;
import com.automation.infra.WaitUtils;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class ProjectPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By createOptionsButton = By.cssSelector("li.op-app-menu--item.op-app-menu--item_has-dropdown");
    private final By createTaskOptionsDropDown = By.cssSelector("a.op-menu--item-action[href='/projects/demo-project/work_packages/new?type=1']");
    private final By inputTextTaskName = By.cssSelector("input#wp-new-inline-edit--field-subject");
    private final By saveTaskButton = By.cssSelector("button#work-packages--edit-actions-save");
    private final By recentlyCreated = By.xpath("//span[normalize-space()='Recently created']");

    private void clickElement(By locator, String actionMessage) {
        LogUtils.info(actionMessage);
        wait.waitForElementClickable(driver.findElement(locator)).click();
    }

    private void enterText(By locator, String text, String actionMessage) {
        LogUtils.info(actionMessage);
        wait.waitForElementClickable(driver.findElement(locator)).sendKeys(text);
    }

    public void clickCreateOptionsButton() {
        clickElement(createOptionsButton, "Clicking create options button");
    }

    public void clickCreateTaskOptionsButton() {
        clickElement(createTaskOptionsDropDown, "Clicking create task button");
    }

    public void insertInputTextTaskName(String taskName) {
        enterText(inputTextTaskName, taskName, "Entering task name: " + taskName);
    }

    public void clickSaveTaskButton() {
        clickElement(saveTaskButton, "Clicking save task button");
    }

    public void clickRecentlyCreated() {
        clickElement(recentlyCreated, "Clicking recently created");
    }

    public boolean isTaskCreated(String taskName) {
        try {
            String cssSelector = String.format("span[title='%s']", taskName);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement taskElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
            return taskElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}