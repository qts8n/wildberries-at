package main;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class StepScreenshoter extends TestListenerAdapter implements StepLifecycleListener {
    private void takeStepScreenshot(String filename) {
        ScreenshotWizard wizard = new ScreenshotWizard(filename);
        wizard.screenshotPage(WildberriesElement.getInstance());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeStepScreenshot("Failure");
    }

    @Override
    public void afterStepStart(final StepResult result) {
        takeStepScreenshot("Start");
    }

    @Override
    public void beforeStepStop(final StepResult result) {
        takeStepScreenshot("Stop");
    }
}
