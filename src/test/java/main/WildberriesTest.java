package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Listeners(StepScreenshoter.class)
public class WildberriesTest {
    protected static final int TIMEOUT_DEFAULT = 10;

    WbConfig config;

    Logger logger;

    WebDriver ffDriver;

    @BeforeMethod
    protected void testInit() {
        String projectPath = System.getProperty("user.dir");
        config = new WbConfig(WbConfig.INSTANCE);
        System.setProperty("webdriver.gecko.driver", projectPath + config.get(WbConfig.Key.GECKO_PATH));
        logger = Logger.getLogger(getClass().getName());
        ffDriver = new FirefoxDriver();
        ffDriver.get(config.get(WbConfig.Key.HOSTNAME));
        ffDriver.manage().timeouts().implicitlyWait(TIMEOUT_DEFAULT, TimeUnit.SECONDS);
    }

    @AfterMethod
    protected void testFinalize(ITestResult testResult) {
        ffDriver.close();
    }
}
