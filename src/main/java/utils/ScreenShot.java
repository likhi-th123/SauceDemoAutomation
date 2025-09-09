package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

    public static String takeScreenshot(WebDriver driver, String testClassName, String screenshotName) {
        // todays date folder (yyyyMMdd)
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // folder path: screenshots/date/testClassName/
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/" + today + "/" + testClassName + "/";
        new File(screenshotDir).mkdirs();

        // final file path 
        String filePath = screenshotDir + screenshotName + ".png";
        File destFile = new File(filePath);

        if (destFile.exists()) {
            return filePath;
        }

        // capture and save
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}













