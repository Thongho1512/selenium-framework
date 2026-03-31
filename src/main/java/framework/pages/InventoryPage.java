package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InventoryPage extends BasePage {
    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;
    
    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;
    
    @FindBy(css = ".inventory_item button")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".inventory_item")
    private List<WebElement> inventoryItems;

    public InventoryPage(WebDriver driver) { 
        super(driver); 
    }

    public boolean isLoaded() {
        return isElementVisible(By.cssSelector(".inventory_list"));
    }

    public InventoryPage addFirstItemToCart() {
        WebElement btn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        btn.click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        return this;
    }
    
    public InventoryPage addItemByName(String name) {
        for (WebElement item : inventoryItems) {
            if (item.findElement(By.cssSelector(".inventory_item_name")).getText().equals(name)) {
                waitAndClick(item.findElement(By.tagName("button")));
                break;
            }
        }
        return this;
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(getText(cartBadge));
        } catch (Exception e) {
            return 0;
        }
    }

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public CartPage goToCart() {
        waitAndClick(cartLink);
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("cart.html"));
        return new CartPage(driver);
    }
}
