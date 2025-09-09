package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

public class UITest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setUpTest() {
    	driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
        
        // username & password from config.properties
        loginPage.setUsername(ConfigReader.get("username"));
        loginPage.setPassword(ConfigReader.get("password"));
        loginPage.clickLogin();

        productPage = new ProductPage(driver);
    }


    //Logout from burger menu
    @Test(priority = 1)
    public void testLogoutFromBurgerMenu() {
        productPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "User was not redirected to login page!");
    }

    // Navigate to About from burger menu
    @Test(priority = 2)
    public void testNavigateToAbout() {
        productPage.clickAbout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs.com"),
                "About link did not redirect to Sauce Labs site!");
    }


    // Verify footer links and icons
    @Test(priority = 3)
    public void testFooterLinksAndIcons() throws InterruptedException {
        Assert.assertTrue(productPage.isTwitterIconVisible(), "Twitter icon not visible!");
        Assert.assertTrue(productPage.isFacebookIconVisible(), "Facebook icon not visible!");
        Assert.assertTrue(productPage.isLinkedInIconVisible(), "LinkedIn icon not visible!");
        Assert.assertTrue(productPage.getFooterText().contains("Sauce Labs"),
                "Footer text is incorrect!");
        
        Thread.sleep(3000);
    }
}
