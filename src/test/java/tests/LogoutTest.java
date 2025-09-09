package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.LogoutPage;
import utils.ConfigReader;


public class LogoutTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private LogoutPage logoutPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    @Test(priority = 1)
    public void testLogoutFunctionality() throws InterruptedException {
        // Login with standard user
        loginPage.setUsername(ConfigReader.get("username"));
        loginPage.setPassword(ConfigReader.get("password"));

        loginPage.clickLogin();
        productPage = new ProductPage(driver);
        Thread.sleep(2000);
        logoutPage.logout();
        Thread.sleep(2000);

        Assert.assertTrue(logoutPage.isLogoutSuccessful(), "Logout was not successful!");
    }
}
