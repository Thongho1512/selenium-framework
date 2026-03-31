package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css = ".cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart_button")
    private List<WebElement> removeButtons;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        if (!isElementVisible(By.cssSelector(".cart_item"))) {
            return 0;
        }
        return cartItems.size();
    }

    public CartPage removeFirstItem() {
        if (isElementVisible(By.cssSelector(".cart_button"))) {
            WebElement btn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(By.cssSelector(".cart_button")));
            btn.click();
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cart_button")));
        }
        return this;
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        if (isElementVisible(By.cssSelector(".cart_item"))) {
            for (WebElement item : cartItems) {
                names.add(getText(item.findElement(By.cssSelector(".inventory_item_name"))));
            }
        }
        return names;
    }

    public CheckoutPage goToCheckout() {
        waitAndClick(checkoutButton);
        return new CheckoutPage(driver);
    }
}
