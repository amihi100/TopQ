package com.automation.infra;

import com.aventstack.extentreports.Status;
import org.testng.Assert;

import static com.automation.infra.ExtentManager.test;

public class AssertUtils {
    public static void assertContains(String actual, String expected, String successMessage, String failureMessage) {
        try {
            Assert.assertTrue(actual.contains(expected));
            LogUtils.info(successMessage);
            test.log(Status.PASS, successMessage);

        } catch (AssertionError e) {
            LogUtils.error(failureMessage);
            test.log(Status.FAIL, failureMessage);
            throw e;
        }
    }

    public static void assertTrue(boolean condition, String successMessage, String failureMessage) {
        try {
            Assert.assertTrue(condition);
            LogUtils.info(successMessage);
            test.log(Status.PASS, successMessage);
        } catch (AssertionError e) {
            LogUtils.error(failureMessage);
            test.log(Status.FAIL, failureMessage);
            throw e;
        }
    }
} 