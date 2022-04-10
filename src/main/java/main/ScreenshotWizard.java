package main;

import org.openqa.selenium.*;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;

class ScreenshotWizard {
    private static final String MIME = "image/jpg";

    private String filename;

    ScreenshotWizard(String name) {
        filename = name;
    }

    private static byte[] takeScreenshot(WebDriver instance) {
        return ((TakesScreenshot) instance).getScreenshotAs(OutputType.BYTES);
    }

    private void addAttachment(byte[] shot) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        String[] mimeParts = MIME.split("/");
        String ext = mimeParts[mimeParts.length - 1];
        lifecycle.addAttachment(filename, MIME, ext, shot);
    }

    void screenshotPage(WebDriver instance) {
        addAttachment(takeScreenshot(instance));
    }
}
