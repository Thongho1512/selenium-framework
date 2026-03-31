package framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions autoFirefoxOptions = new FirefoxOptions();
                if ("true".equalsIgnoreCase(System.getenv("CI"))) {
                    autoFirefoxOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(autoFirefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions autoEdgeOptions = new EdgeOptions();
                if ("true".equalsIgnoreCase(System.getenv("CI"))) {
                    autoEdgeOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(autoEdgeOptions);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions autoChromeOptions = new ChromeOptions();
                if ("true".equalsIgnoreCase(System.getenv("CI"))) {
                    autoChromeOptions.addArguments("--headless=new");
                    autoChromeOptions.addArguments("--disable-gpu");
                    autoChromeOptions.addArguments("--window-size=1920,1080");
                }
                driver = new ChromeDriver(autoChromeOptions);
                break;
        }
        return driver;
    }
}
