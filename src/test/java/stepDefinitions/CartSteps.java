package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.ProductPage;
import pages.CartPage;

public class CartSteps {

    WebDriver driver = DriverFactory.getDriver();
    ProductPage productPage = new ProductPage(driver);
    CartPage cartPage = new CartPage(driver);

    @When("user adds a product to the cart")
    public void user_adds_a_product_to_the_cart() {
        productPage.addProductByName("Sauce Labs Backpack");  
    }

    @When("user clicks on the cart icon")
    public void user_clicks_on_the_cart_icon() {
        productPage.clickCartIcon();
    }

    @Then("cart page should be displayed")
    public void cart_page_should_be_displayed() {
        cartPage.verifyCartPageIsDisplayed();
    }
}
