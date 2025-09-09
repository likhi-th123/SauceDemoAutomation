package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import utils.ConfigReader;
import utils.ScreenShot;


public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUpTest() throws InterruptedException {
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);

        loginPage.setUsername(ConfigReader.get("username"));
        loginPage.setPassword(ConfigReader.get("password"));
        Thread.sleep(2000);
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
        Thread.sleep(2000);
        cartPage = new CartPage(driver);
    }

    //cartpage is displayed
    @Test(priority = 1)
    public void testCartPageDisplayed() throws InterruptedException {
        productPage.addProductByName("Sauce Labs Backpack");
        Thread.sleep(2000);

        productPage.clickCartIcon();
        Thread.sleep(2000);
     // Screenshot after navigating to cart
        ScreenShot.takeScreenshot(driver, "CartTest", "CartPageDisplayed");

        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page was not displayed!");
        Assert.assertEquals(cartPage.getCartTitle(), "Your Cart", "Cart title mismatch!");
    }

    @Test(priority = 2)
    public void testContinueShoppingFromCart() throws InterruptedException {
        productPage.addProductByName("Sauce Labs Backpack");
        Thread.sleep(2000);
        productPage.clickCartIcon();

        cartPage.clickContinueShopping();
        Thread.sleep(2000);
        ScreenShot.takeScreenshot(driver, "CartTest", "AfterContinueShopping");

        Assert.assertEquals(productPage.getTitleText(), "Products", 
                "Did not return to Products page after Continue Shopping!");
    }

    @Test(priority = 3)
    public void testProceedToCheckout() throws InterruptedException {
        productPage.addProductByName("Sauce Labs Backpack");
        Thread.sleep(2000);
        productPage.clickCartIcon();

        cartPage.clickCheckout();
        Thread.sleep(2000);
        ScreenShot.takeScreenshot(driver, "CartTest", "AfterCheckout");

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),
                "Did not navigate to checkout page!");
    }
}
