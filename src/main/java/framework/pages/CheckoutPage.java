package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = ".complete-header")
    private WebElement completeHeader;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isElementVisible(By.id("first-name"));
    }

    public CheckoutPage fillInfo(String firstName, String lastName, String postalCode) {
        waitAndType(firstNameField, firstName);
        waitAndType(lastNameField, lastName);
        waitAndType(postalCodeField, postalCode);
        jsClick(continueButton);
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("step-two"));
        return this;
    }

    public void finishCheckout() {
        jsClick(finishButton);
    }

    public boolean isCheckoutComplete() {
        return isElementVisible(By.cssSelector(".complete-header"));
    }
}
