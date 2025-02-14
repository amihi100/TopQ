package com.automation.tests;

import com.automation.infra.LogUtils;
import com.automation.infra.WaitUtils;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
public class MyPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By projectMenu = By.id("projects-menu");
    private final By demoProjectOption = By.cssSelector("span[data-test-selector=\"op-header-project-select--item-title\"]");

    private void clickElement(By locator, String actionMessage) {
        LogUtils.info(actionMessage);
        wait.waitForElementClickable(driver.findElement(locator)).click();
    }


    public void clickProjectMenu() {
        clickElement(projectMenu, "Clicking project menu");
    }

    public void selectDemoProject() {
        clickElement(demoProjectOption, "Selecting demo project option");
    }

}