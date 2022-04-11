package main;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import wb.elements.CartItem;
import wb.elements.CatalogItem;
import wb.pages.CartPage;
import wb.pages.CatalogPage;
import wb.pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends WildberriesTest {
    private static final long INITIAL_TIMEOUT = 1000;

    @Test
    @Parameters("increaseValue")
    public void increaseItemNumber(int increaseValue) throws InterruptedException {
        HomePage homePage = new HomePage(ffDriver);
        CatalogItem catalogItem = homePage.getFirstCatalogItem();
        CatalogPage catalogPage = catalogItem.openItemPage();
        catalogPage.addToCart();
        CartPage cartPage = homePage.openCartPage();
        CartItem cartItem = cartPage.getFirstCartItem();

        Thread.sleep(INITIAL_TIMEOUT);

        int initialTotalPrice = cartPage.getPrice();
        int initialTotalNumber = cartPage.getCartItemsFromHeader();
        int initialPrice = cartItem.getPrice();
        int initialNumber = cartItem.getItemNumber();

        assertThat(initialTotalPrice).isGreaterThan(0);
        assertThat(initialTotalNumber).isGreaterThan(0);
        assertThat(initialPrice).isGreaterThan(0);
        assertThat(initialNumber).isGreaterThan(0);

        for (int i = 0; i < increaseValue; i++) {
            cartItem.increaseItemNumber();
        }

        int finalTotalPrice = cartPage.getPrice();
        int finalTotalNumber = cartPage.getCartItemsFromHeader();
        int finalPrice = cartItem.getPrice();
        int finalNumber = cartItem.getItemNumber();

        assertThat(finalNumber - initialNumber).isEqualTo(increaseValue);
        assertThat(finalTotalNumber - initialTotalNumber).isEqualTo(increaseValue);
        assertThat(finalPrice - initialPrice).isEqualTo(finalTotalPrice - initialTotalPrice);
    }
}
