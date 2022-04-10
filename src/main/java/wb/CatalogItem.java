package wb;

import io.qameta.allure.Step;
import main.WildberriesElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CatalogItem extends WildberriesElement {
    private final WebElement item;

    protected By priceSpan = By.className("goods-card__price-now");
    protected By titleSpan = By.className("goods-card__description");
    protected By quickViewBtn = By.className("goods-card__preview-btn");
    protected By pageContainer = By.className("product-detail");

    public CatalogItem(WebDriver context, WebElement itemElement) {
        super(context);
        item = itemElement;
    }

    @Step("Locate Quick View button")
    public WebElement locateQuickViewBtn() {
        Actions actions = new Actions(instance);
        actions.moveToElement(item).build().perform(); // Triggering hover
        return getAfterVisibilityOf(item.findElement(quickViewBtn));
    }

    @Step("Open catalog item page")
    public CatalogPage openItemPage() {
        item.click();
        getAfterVisibilityBy(pageContainer);
        return new CatalogPage(instance);
    }

    public int getPrice() {
        return getPriceOrNumFromElement(item.findElement(priceSpan));
    }

    public String getTitle() {
        String title = item.findElement(titleSpan).getText().trim();
        String[] split = title.split("/", 2);
        return split[0];
    }
}
