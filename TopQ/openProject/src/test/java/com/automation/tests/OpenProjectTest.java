package com.automation.tests;

import com.automation.infra.AssertUtils;
import com.automation.infra.Configuration;
import com.automation.infra.LogUtils;
import com.automation.infra.WaitUtils;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.automation.infra.WaitUtils.waitForPageLoad;

public class OpenProjectTest extends BaseTest {

    @Test(priority = 1, description = "Verify user login")
    public void testLoginToOpenProject() {
        LogUtils.info("Starting login test");
        driver = getDriver();
        
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.navigateToMainPage(Configuration.getBaseUrl());
        LoginPage loginPage = mainPage.clickSignIn();
        loginPage.enterProjectName(Configuration.getProjectName())
                .clickProjectSignIn()
                .enterCredentials(Configuration.getUsername(), Configuration.getPassword())
                .clickLoginButton();

        waitForPageLoad(driver);
        wait.waitForUrlContains("/my/page");

        String currentUrl = driver.getCurrentUrl();
        AssertUtils.assertContains(
                currentUrl,
                Configuration.getProjectName() + ".openproject.com",
                "URL contains project name of: " + Configuration.getProjectName(),
                "URL does not contain project name"
        );
    }

    @Test(priority = 2, dependsOnMethods = "testLoginToOpenProject", description = "Verify creating a new task")
    public void testCreateTask() throws InterruptedException {
        test.log(Status.INFO, "Starting task creation test");
        driver = getDriver();
        WaitUtils waitUtils = new WaitUtils(driver);

        MyPage myPage = new MyPage(driver, waitUtils);
        ProjectPage projectSteps = new ProjectPage(driver, waitUtils);
        String randomTask = Configuration.getTaskName() + ((int) (Math.random() * 1_000_000));

        waitForPageLoad(driver);
        myPage.clickProjectMenu();
        myPage.selectDemoProject();
        wait.waitForUrlContains("/projects");
        projectSteps.clickCreateOptionsButton();
        projectSteps.clickCreateTaskOptionsButton();
        projectSteps.insertInputTextTaskName(randomTask);
        projectSteps.clickSaveTaskButton();
        projectSteps.clickRecentlyCreated();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        boolean taskExist = projectSteps.isTaskCreated(randomTask);
        AssertUtils.assertTrue(taskExist,
                randomTask + " task created successfully.",
                randomTask + " task was not created.");
    }
}