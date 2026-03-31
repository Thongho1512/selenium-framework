package tests;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testLoginSuccess() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isLoaded(), "Trang inventory chưa load");
    }

    @Test
    public void testLoginFailure() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("standard_user", "wrong_pass");
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Cố ý sai để test pipeline");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
