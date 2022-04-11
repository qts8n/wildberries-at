package main;

import wb.elements.CatalogItem;
import wb.pages.CatalogPage;
import wb.pages.HomePage;
import org.testng.annotations.Test;
import wb.elements.QuickViewModal;

import static org.assertj.core.api.Assertions.assertThat;


public class CatalogTest extends WildberriesTest {
    private static final String CART_BTN_BEFORE_TEXT = "Добавить в корзину";
    private static final String CART_BTN_AFTER_TEXT = "Перейти в корзину";

    @Test
    public void quickViewInfo() {
        HomePage homePage = new HomePage(ffDriver);
        CatalogItem catalogItem = homePage.getFirstCatalogItem();
        logger.info("Extracting item properties");
        String itemTitle = catalogItem.getTitle();
        logger.info(itemTitle);
        int itemPrice = catalogItem.getPrice();
        logger.info(String.valueOf(itemPrice));

        catalogItem.locateQuickViewBtn().click();
        QuickViewModal modal = homePage.getQuickViewModal();

        logger.info("Extracting modal properties");
        String modalTitle = modal.getTitle();
        logger.info(modalTitle);
        int modalPrice = modal.getPrice();
        logger.info(String.valueOf(modalPrice));

        assertThat(itemTitle).isEqualToIgnoringWhitespace(modalTitle);
        assertThat(itemPrice).isEqualTo(modalPrice);
    }

    @Test
    public void addToCart() {
        HomePage homePage = new HomePage(ffDriver);
        CatalogItem catalogItem = homePage.getFirstCatalogItem();
        CatalogPage catalogPage = catalogItem.openItemPage();
        String cartTextBefore = catalogPage.getCartBtnText();
        int cartItemsBefore = homePage.getCartItemsFromHeader();

        int cartItemsAfter = catalogPage.addToCart();
        String cartTextAfter = catalogPage.getCartBtnText();

        assertThat(cartTextBefore).isEqualToIgnoringWhitespace(CART_BTN_BEFORE_TEXT);
        assertThat(cartTextAfter).isEqualToIgnoringWhitespace(CART_BTN_AFTER_TEXT);
        assertThat(cartItemsAfter - cartItemsBefore).isEqualTo(1);
    }
}
