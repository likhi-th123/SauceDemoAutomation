package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;
import utils.ScreenShot;


public class ProductTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUpTest() throws InterruptedException {
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);

        loginPage.setUsername(ConfigReader.get("username"));
        loginPage.setPassword(ConfigReader.get("password"));
        Thread.sleep(2000);
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
    }

    @Test
    public void testProductListingPage() throws InterruptedException {
       
        String actualTitle = productPage.getTitleText();
        Thread.sleep(2000);
        Assert.assertEquals(actualTitle, "Products");
        ScreenShot.takeScreenshot(driver, "ProductTest", "AfterProductListingCheck");
        
    }

    @Test
    public void testAddProductToCart() throws InterruptedException {
        
        productPage.addProductByName("Sauce Labs Backpack");
        Thread.sleep(2000);
        Assert.assertEquals(productPage.getBackpackButtonText(), "Remove");
        ScreenShot.takeScreenshot(driver, "ProductTest", "AfterAddProduct");
        
    }

    @Test
    public void testClickProductDetailsPage() throws InterruptedException {
        
        productPage.clickOnProduct("Sauce Labs Backpack");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"));
        ScreenShot.takeScreenshot(driver, "ProductTest", "AfterProductDetailsClick");
        
    }

    @Test
    public void testNavigateToCartPage() throws InterruptedException {
       
        productPage.clickCartIcon();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
        ScreenShot.takeScreenshot(driver, "ProductTest", "AfterNavigateCart");

    }
}
