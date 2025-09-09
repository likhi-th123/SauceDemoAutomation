package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//checkoutpage
public class CheckoutPage {
    private WebDriver driver;

    // --- Locators ---
    private By cartIcon = By.className("shopping_cart_link");
    private By checkoutBtn = By.id("checkout");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By orderCompleteMsg = By.className("complete-header");
    private By productNameLabel = By.className("inventory_item_name");
    private By errorMsg = By.cssSelector("h3[data-test='error']");
    private By priceLabel = By.className("summary_subtotal_label"); // price/tax summary


    // --- Constructor ---
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Actions ---
    public void addProductToCart(String productName) {
        // Locator for product add-to-cart button (dynamic, depends on product name)
        By productButton = By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button");

        driver.findElement(productButton).click();   // Add product
        driver.findElement(cartIcon).click();        // Open cart
        driver.findElement(checkoutBtn).click();     // Click checkout
    }

    public void enterDetails(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }
    
 // Check if checkout page is loaded
    public boolean isCheckoutPageDisplayed() {
        return driver.findElements(firstNameInput).size() > 0;
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public void clickFinish() {
        driver.findElement(finishBtn).click();
    }

    public boolean isOrderComplete() {
        return driver.findElements(orderCompleteMsg).size() > 0;
    }

    public String getOrderCompleteText() {
        return driver.findElement(orderCompleteMsg).getText();
    }

   
    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }

    public String getOrderSummaryProduct() {
        return driver.findElement(productNameLabel).getText();
    }

    public boolean isPriceDisplayed() {
        return driver.findElements(priceLabel).size() > 0;
    }
}
