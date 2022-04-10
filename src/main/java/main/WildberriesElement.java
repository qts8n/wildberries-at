package main;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wb.CartPage;

public class WildberriesElement {
    protected static final int TIMEOUT_DEFAULT = 5;

    protected By body = By.tagName("body");
    protected By cartHeaderBtn = By.className("navbar-pc__icon--basket");
    protected By headerNotifySpan = By.className("navbar-pc__notify");
    protected By cartContainer = By.className("basket-page__blocks-wrap");

    protected static WebDriver instance;

    public WildberriesElement(WebDriver context) {
        instance = context;
    }

    static WebDriver getInstance() {
        return instance;
    }

    protected WebElement getAfterVisibilityBy(By by) {
        return (new WebDriverWait(instance, TIMEOUT_DEFAULT))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement getAfterVisibilityOf(WebElement el) {
        return (new WebDriverWait(instance, TIMEOUT_DEFAULT))
                .until(ExpectedConditions.visibilityOf(el));
    }

    protected int getPriceOrNumFromElement(WebElement webElement){
        String price = webElement.getText().replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException error) {
            return 0;
        }
    }

    public int getCartItemsFromHeader() {
        try {
            WebElement cartBtn = getAfterVisibilityBy(cartHeaderBtn);
            return getPriceOrNumFromElement(cartBtn.findElement(headerNotifySpan));
        } catch (NoSuchElementException err) {
            return 0;
        }
    }

    @Step("Open the cart page")
    public CartPage openCartPage() {
        WebElement cartBtn = getAfterVisibilityBy(cartHeaderBtn);
        cartBtn.click();
        getAfterVisibilityBy(cartContainer);
        return new CartPage(instance);
    }
}
