package com.automation.tests;

import com.automation.infra.WaitUtils;
import com.automation.infra.LogUtils;
import com.automation.infra.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected WaitUtils wait;
    protected ExtentTest test;

    @BeforeClass
    public void setUp() {
        if (driver == null) {
            try {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                this.wait = new WaitUtils(driver);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

            } catch (Exception e) {
                LogUtils.info("Error during driver setup: " + e.getMessage());
                throw e;
            }
        }

        if (this.wait == null) {
            this.wait = new WaitUtils(driver);
        }

        test = ExtentManager.createTest(getClass().getSimpleName());
        test.log(Status.INFO, "Test setup completed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
            } catch (Exception e) {
                System.out.println("Error during driver quit: " + e.getMessage());
            }
        }
        killChromeProcesses();

    }

    @AfterSuite
    public void finalizeReports() throws InterruptedException {
        test.log(Status.INFO, "Suite execution completed.");
        Thread.sleep(3000);
        closeDriver();
        ExtentManager.getInstance().flush();


        try {
            Thread.sleep(3000);
            clearDriverCache();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            test.log(Status.FAIL, "Interrupted while waiting to clear cache");
        }
    }

    private void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                test.log(Status.INFO, "Error while closing driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    private void clearDriverCache() {
        try {
            WebDriverManager.chromedriver().clearDriverCache();
        } catch (Exception e) {
            test.log(Status.INFO, "Could not clear driver cache: " + e.getMessage());

        }
    }

    public WebDriver getDriver() {
        return driver;
    }
    public static void killChromeProcesses() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
            } else {
                Runtime.getRuntime().exec("pkill -f chrome");
                Runtime.getRuntime().exec("pkill -f chromedriver");
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Failed to kill Chrome processes: " + e.getMessage());
        }
    }
}