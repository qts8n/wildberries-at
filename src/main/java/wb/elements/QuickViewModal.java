package wb.elements;

import main.WildberriesElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuickViewModal extends WildberriesElement {
    private final WebElement modal;

    protected By titleHeader = By.className("same-part-kt__header");
    protected By priceSpan = By.className("price-block__final-price");

    public QuickViewModal(WebDriver context, WebElement modalElement) {
        super(context);
        modal = modalElement;
    }

    public String getTitle() {
        String title = modal.findElement(titleHeader).getText().trim();
        String[] split = title.split("/", 2);
        return split[0];
    }

    public int getPrice() {
        return getPriceOrNumFromElement(modal.findElement(priceSpan));
    }
}
