package com.automation.testSute;

import org.testng.annotations.Test;

public class TestSuite {

    @Test
    public void loginTest() {
        System.out.println("Running LoginTest");
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void createProjectTest() {
        System.out.println("Running CreateProjectTest");
    }
}