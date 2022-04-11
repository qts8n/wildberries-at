package wb.pages;

import io.qameta.allure.Step;
import main.WildberriesElement;
import org.openqa.selenium.*;
import wb.elements.CatalogItem;
import wb.elements.QuickViewModal;

public class HomePage extends WildberriesElement {
    protected By goodsUl = By.className("goods__list");
    protected By quickViewModal = By.cssSelector(".i-popup-same-part-kt.j-product-popup.shown");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Scroll catalog into view")
    private WebElement getCatalogIntoView() {
        WebElement ul = instance.findElement(goodsUl);
        ((JavascriptExecutor) instance).executeScript("arguments[0].scrollIntoView(true)", ul);
        return getAfterVisibilityBy(goodsUl);
    }

    public CatalogItem getFirstCatalogItem() {
        WebElement visibleUl = getCatalogIntoView();
        WebElement firstItem = visibleUl.findElement(By.cssSelector("li:first-child"));
        return new CatalogItem(instance, firstItem);
    }

    @Step("Show Quick View modal")
    public QuickViewModal getQuickViewModal() {
        WebElement modal = getAfterVisibilityBy(quickViewModal);
        return new QuickViewModal(instance, modal);
    }
}
