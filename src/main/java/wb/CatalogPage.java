package wb;

import io.qameta.allure.Step;
import main.WildberriesElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CatalogPage extends WildberriesElement {
    protected By cartBtn = By.className("same-part-kt__btn-wrap");

    public CatalogPage(WebDriver context) {
        super(context);
    }

    public String getCartBtnText() {
        return getAfterVisibilityBy(cartBtn).getText();
    }

    @Step("Add item to the cart")
    public int addToCart() {
        Actions actions = new Actions(instance);
        actions.moveToElement(getAfterVisibilityBy(cartBtn)); // Triggering hover
        actions.click().build().perform();
        return getCartItemsFromHeader();
    }
}
