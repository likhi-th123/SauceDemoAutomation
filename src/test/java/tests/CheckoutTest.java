package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ConfigReader;
import utils.ScreenShot;


public class CheckoutTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpTest() throws InterruptedException {
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);

        loginPage.setUsername(ConfigReader.get("username"));
        loginPage.setPassword(ConfigReader.get("password"));
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
        productPage.addProductByName("Sauce Labs Backpack");
        Thread.sleep(2000);
        productPage.clickCartIcon();

        cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        checkoutPage = new CheckoutPage(driver);
    }

    @Test(priority = 11)
    public void testCompleteCheckoutValidData() throws InterruptedException {
        checkoutPage.enterDetails("John", "Doe", "560001");
       
        Thread.sleep(2000);
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        Thread.sleep(2000);
        // Screenshot after finishing checkout
        ScreenShot.takeScreenshot(driver, "CheckoutTest", "AfterClickFinish");

        Assert.assertTrue(checkoutPage.isOrderComplete(), "Order was not completed!");
    }

    @Test(priority = 12)
    public void testOrderSummaryValidation() throws InterruptedException {
        checkoutPage.enterDetails("John", "Doe", "560001");
        
        Thread.sleep(2000);
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getOrderSummaryProduct(), "Sauce Labs Backpack",
                "Product missing in order summary!");
        Thread.sleep(2000);
        ScreenShot.takeScreenshot(driver, "CheckoutTest", "OrderSummary_AfterContinue");

        Assert.assertEquals(checkoutPage.getOrderSummaryProduct(), "Sauce Labs Backpack",
                "Product missing in order summary!");
        Assert.assertTrue(checkoutPage.isPriceDisplayed(), "Price or tax not displayed!");
    }
    
    @Test(priority = 13)
    public void testOrderConfirmationPage() throws InterruptedException {
        checkoutPage.enterDetails("likhi", "v", "560001");
        
        Thread.sleep(2000);
        checkoutPage.clickContinue();
  
        checkoutPage.clickFinish();
        Thread.sleep(2000);
        ScreenShot.takeScreenshot(driver, "CheckoutTest", "OrderConfirmation_AfterFinish");

        Assert.assertEquals(checkoutPage.getOrderCompleteText(), "Thank you for your order!");
    }
}
