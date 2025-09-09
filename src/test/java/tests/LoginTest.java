package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;
import utils.ScreenShot;


public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUpTest() {
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testValidLoginStandardUser() throws InterruptedException {
        loginPage.setUsername(ConfigReader.get("username"));
        loginPage.setPassword(ConfigReader.get("password"));
     // Screenshot before login click
        ScreenShot.takeScreenshot(driver, "LoginTest", "BeforeLoginClick");

        Thread.sleep(2000);
        loginPage.clickLogin();
        productPage = new ProductPage(driver);
        
        Assert.assertEquals(productPage.getTitleText(), "Products");
    }

    @Test(priority = 2)
    public void testLockedOutUser() throws InterruptedException {
        loginPage.setUsername("locked_out_user");
        loginPage.setPassword("secret_sauce");

        Thread.sleep(2000);
        loginPage.clickLogin();
     // Screenshot after error
        ScreenShot.takeScreenshot(driver, "LoginTest", "LockedOut_ErrorDisplayed");

        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorText().contains("locked out"));
    }

    @Test(priority = 3)
    public void testInvalidCredentials() throws InterruptedException {
        loginPage.setUsername("invalid_user");
        loginPage.setPassword("wrong_pass");
     // Screenshot before login attempt
        ScreenShot.takeScreenshot(driver, "LoginTest", "Invalid_BeforeLogin");


        Thread.sleep(2000);
        loginPage.clickLogin();
        Thread.sleep(2000);
        
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorText().contains("do not match"));
    }

    @Test(priority = 4)
    public void testLogoutFunctionality() throws InterruptedException {
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        Thread.sleep(2000);
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
     // Screenshot after login before logout
        ScreenShot.takeScreenshot(driver, "LoginTest", "BeforeLogout");


        Thread.sleep(2000);
        productPage.clickLogout();
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }
}
