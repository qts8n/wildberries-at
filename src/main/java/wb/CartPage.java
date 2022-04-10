package wb;

import main.WildberriesElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends WildberriesElement {
    protected By cartItemList = By.className("basket-form__content");
    protected By cartTotalPriceDiv = By.className("b-top__total");

    public CartPage(WebDriver context) {
        super(context);
    }

    public CartItem getFirstCartItem() {
        WebElement cartForms = getAfterVisibilityBy(cartItemList);
        WebElement firstItem = cartForms.findElement(By.cssSelector("div:first-child"));
        return new CartItem(instance, firstItem);
    }

    public int getPrice() {
        return getPriceOrNumFromElement(instance.findElement(cartTotalPriceDiv));
    }
}
