package stepDefinitions;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class ProductSteps extends BaseTest {
    private LoginPage loginPage;
    private ProductPage productPage;

    @Given("user is logged in with {string} and {string}")
    public void user_is_logged_in_with_and(String username, String password) {
        setUp();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();
        productPage = new ProductPage(driver);
    }

    @Then("the product page title should be {string}")
    public void the_product_page_title_should_be(String title) {
        Assert.assertEquals(productPage.getTitleText(), title, "Product page title mismatch!");
    }
    @Then("the product page should display {string} products")
    public void the_product_page_should_display_products(String expectedCount) {
        int actualCount = productPage.getProductCount();
        Assert.assertEquals(actualCount, Integer.parseInt(expectedCount), "Product count mismatch!");
    }

}
