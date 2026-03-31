package tests;

import framework.base.BaseTest;
import framework.pages.CartPage;
import framework.pages.CheckoutPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class CheckoutTest extends BaseTest {
    @Test
    public void testCheckoutSuccess() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage invPage = loginPage.login("standard_user", "secret_sauce");
        CartPage cartPage = invPage.addFirstItemToCart().goToCart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        
        Assert.assertTrue(checkoutPage.isLoaded(), "Trang checkout chưa load");

        Map<String, String> data = TestDataFactory.randomCheckoutData();
        checkoutPage.fillInfo(data.get("firstName"), data.get("lastName"), data.get("postalCode"));
        checkoutPage.finishCheckout();
        
        Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Quá trình checkout chưa hoàn tất");
    }
}
