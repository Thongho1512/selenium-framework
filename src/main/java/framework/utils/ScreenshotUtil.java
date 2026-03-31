package framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import framework.config.ConfigReader;

public class ScreenshotUtil {
    public static String capture(WebDriver driver, String testName) {
        if (driver == null) return "";
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String dirPath = ConfigReader.getInstance().getScreenshotPath();
            
            Path directory = Paths.get(dirPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File target = new File(dirPath + fileName);
            Files.copy(source.toPath(), target.toPath());
            
            return target.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Lỗi chụp ảnh màn hình: " + e.getMessage());
            return "";
        }
    }
}
