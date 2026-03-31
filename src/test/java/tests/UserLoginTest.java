package tests;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.JsonReader;
import framework.utils.UserData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class UserLoginTest extends BaseTest {
    @DataProvider(name = "jsonUsers")
    public Object[][] getUsersFromJson() throws IOException {
        List<UserData> users = JsonReader.readUsers("src/test/resources/testdata/users.json");
        return users.stream()
                .map(u -> new Object[]{u.username, u.password, u.expectSuccess, u.description})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "jsonUsers", groups = {"regression"})
    public void testLoginFromJson(String username, String password, boolean expectSuccess, String description) {
        System.out.println("Test case: " + description);
        LoginPage loginPage = new LoginPage(getDriver());
        
        if (expectSuccess) {
            InventoryPage invPage = loginPage.login(username, password);
            Assert.assertTrue(invPage.isLoaded(), "Không đăng nhập được khi expectSuccess=true");
        } else {
            loginPage.loginExpectingFailure(username, password);
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Không có thông báo lỗi khi expectSuccess=false");
        }
    }
}
