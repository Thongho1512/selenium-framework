package tests;

import framework.base.BaseTest;
import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test
    public void testCartItemCount() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage invPage = loginPage.login(
                framework.config.ConfigReader.getInstance().getUsername(),
                framework.config.ConfigReader.getInstance().getPassword()
        );
        CartPage cartPage = invPage.addFirstItemToCart().goToCart();
        
        Assert.assertEquals(cartPage.getItemCount(), 1, "Số lượng item trong giỏ không đúng");
    }

    @Test
    public void testRemoveItem() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage invPage = loginPage.login(
                framework.config.ConfigReader.getInstance().getUsername(),
                framework.config.ConfigReader.getInstance().getPassword()
        );
        CartPage cartPage = invPage.addFirstItemToCart().goToCart();
        
        cartPage.removeFirstItem();
        Assert.assertEquals(cartPage.getItemCount(), 0, "Item chưa được xóa khỏi giỏ");
    }
}
