package framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    /**
     * Chờ cho đến khi element có thể click và click.
     * Tránh ElementNotInteractableException.
     * @param element WebElement cần click
     */
    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Chờ cho element xuất hiện, xóa nội dung cũ và gõ text mới.
     * @param element WebElement cần nhập liệu
     * @param text Dữ liệu cần nhập
     */
    protected void waitAndType(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Lấy text của element sau khi loại bỏ khoảng trắng ở đầu/cuối.
     * @param element WebElement cần lấy text
     * @return Chuỗi text đã trim
     */
    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
    }

    /**
     * Kiểm tra element có hiển thị hay không (dùng try-catch để không crack test nếu không tìm thấy)
     * Xử lý lỗi StaleElementReferenceException khi DOM bị render lại
     * @param locator By locator
     * @return true nếu hiển thị, false nếu không
     */
    protected boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Cuộn trang đến phần tử bằng JavascriptExecutor
     * Sử dụng khi phần tử nằm ngoài viewport
     * @param element WebElement mục tiêu
     */
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Chờ cho đến khi trang load hoàn tất (readyState = complete)
     */
    protected void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Lấy giá trị attribute của một thẻ html
     * @param element WebElement mục tiêu
     * @param attr Tên attribute
     * @return Giá trị của attribute
     */
    protected String getAttribute(WebElement element, String attr) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(attr);
    }
}
