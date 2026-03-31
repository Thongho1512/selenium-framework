package tests;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDDTTest extends BaseTest {
    @DataProvider(name = "smokeData")
    public Object[][] getSmokeData() {
        return ExcelReader.getData("src/test/resources/testdata/login_data.xlsx", "SmokeCases");
    }

    @DataProvider(name = "negativeData")
    public Object[][] getNegativeData() {
        return ExcelReader.getData("src/test/resources/testdata/login_data.xlsx", "NegativeCases");
    }

    @DataProvider(name = "boundaryData")
    public Object[][] getBoundaryData() {
        return ExcelReader.getData("src/test/resources/testdata/login_data.xlsx", "BoundaryCases");
    }

    @Test(dataProvider = "smokeData", groups = {"smoke", "regression"})
    public void testSmokeLoginFromExcel(String username, String password, String expectedUrl, String description) {
        System.out.println("Test case: " + description);
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage invPage = loginPage.login(username, password);
        Assert.assertTrue(invPage.isLoaded(), "Không vào được trang inventory khi mong muốn thành công");
    }

    @Test(dataProvider = "negativeData", groups = {"regression"})
    public void testNegativeLoginFromExcel(String username, String password, String expectedError, String description) {
        System.out.println("Test case: " + description);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedError, "Thông báo lỗi không khớp");
    }

    @Test(dataProvider = "boundaryData", groups = {"regression"})
    public void testBoundaryLoginFromExcel(String username, String password, String expectedError, String description) {
        System.out.println("Test case: " + description);
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedError, "Thông báo lỗi không khớp cho trường hợp ngoại lệ");
    }
}
