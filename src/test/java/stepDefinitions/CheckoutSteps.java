package stepDefinitions;

import base.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CheckoutPage;

public class CheckoutSteps {

    WebDriver driver = DriverFactory.getDriver();
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Given("user adds {string} to the cart")
    public void user_adds_to_the_cart(String productName) {
        checkoutPage.addProductToCart(productName);
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String lastName, String postalCode) {
        checkoutPage.enterDetails(firstName, lastName, postalCode);
    }

    @When("clicks continue")
    public void clicks_continue() {
        checkoutPage.clickContinue();
    }

    @When("clicks finish")
    public void clicks_finish() {
        checkoutPage.clickFinish();
    }

    @Then("order success message {string} should be displayed")
    public void order_success_message_should_be_displayed(String expectedMessage) {
        Assert.assertEquals(checkoutPage.getOrderCompleteText(), expectedMessage, "Order success message mismatch!");
    }

    @Then("order summary should display product {string}")
    public void order_summary_should_display_product(String expectedProduct) {
        Assert.assertEquals(checkoutPage.getOrderSummaryProduct(), expectedProduct, "Product in order summary does not match!");
    }
    @Then("price summary should be displayed")
    public void price_summary_should_be_displayed() {
        Assert.assertTrue(checkoutPage.isPriceDisplayed(), "Price summary not displayed!");
    }

}
