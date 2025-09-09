package base;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;


import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    //runs once before any test methods
    @BeforeClass
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
