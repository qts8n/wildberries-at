package wb.elements;

import io.qameta.allure.Step;
import main.WildberriesElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartItem extends WildberriesElement {
    public static final long ITEM_TIMEOUT = 1000;

    protected By itemPriceDiv = By.cssSelector(".list-item__price>.list-item__price-new");
    protected By inputNumberDiv = By.className("count__input-number");
    protected By plusBtn = By.className("count__plus");

    private final WebElement item;

    public CartItem(WebDriver context, WebElement itemElement) {
        super(context);
        item = itemElement;
    }

    public int getPrice() {
        return getPriceOrNumFromElement(item.findElement(itemPriceDiv));
    }

    private WebElement getInputDiv() {
        return item.findElement(inputNumberDiv);
    }

    public int getItemNumber() {
        WebElement inputDiv = getInputDiv();
        WebElement input = inputDiv.findElement(By.tagName("input"));
        try {
            return Integer.parseInt(input.getAttribute("value"));
        } catch (NumberFormatException err) {
            return 0;
        }
    }

    public void waitOutPriceAnimations() throws InterruptedException {
        // Skips price change animation
        Thread.sleep(ITEM_TIMEOUT);
    }

    @Step("Increase item number")
    public void increaseItemNumber() throws InterruptedException {
        WebElement inputDiv = getInputDiv();
        inputDiv.findElement(plusBtn).click();
        waitOutPriceAnimations(); // Wait for the prices' animation to end
    }
}
