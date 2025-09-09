package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 🔹 Common Locators
    private By title = By.className("title");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By cartIcon = By.id("shopping_cart_container");

    private By productItems = By.className("inventory_item");

    // 🔹 Burger menu & sidebar
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By aboutLink = By.id("about_sidebar_link");

    private By menuPanel = By.className("bm-menu");

    // 🔹 Footer links
    private By twitterIcon = By.cssSelector("a[href*='twitter']");
    private By facebookIcon = By.cssSelector("a[href*='facebook']");
    private By linkedInIcon = By.cssSelector("a[href*='linkedin']");
    private By footerText = By.className("footer_copy");

    // 🔹 Backpack-specific locators
    private By backpackPrice = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']");
    private By backpackImage = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//img");
    private By backpackButton = By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // 🔹 Page Actions
    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    public List<WebElement> getAllProductNames() {
        return driver.findElements(productNames);
    }

    public List<WebElement> getAllProductPrices() {
        return driver.findElements(productPrices);
    }
    
    public void addProductByName(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button")).click();
    }

    public void clickOnProduct(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']")).click();
    }


    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }


    // ---------- Backpack specific ----------
    public String getBackpackPrice() {
        return driver.findElement(backpackPrice).getText();
    }

    public boolean isBackpackImageVisible() {
        return driver.findElement(backpackImage).isDisplayed();
    }

    public String getBackpackButtonText() {
        return driver.findElement(backpackButton).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackButton).click();
    }

    public void openBurgerMenu() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        btn.click();

        //  Wait until the sidebar menu is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuPanel));
    }

    public void clickLogout() {
        openBurgerMenu();
        WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    public void clickAbout() {
        openBurgerMenu();
        WebElement about = wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        about.click();   
    }

    
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }
    
    
    // ---------- Footer ----------
    public boolean isTwitterIconVisible() {
        return driver.findElement(twitterIcon).isDisplayed();
    }

    public boolean isFacebookIconVisible() {
        return driver.findElement(facebookIcon).isDisplayed();
    }

    public boolean isLinkedInIconVisible() {
        return driver.findElement(linkedInIcon).isDisplayed();
    }

    public String getFooterText() {
        return driver.findElement(footerText).getText();
    }

}















//public void addProductByName(String productName) {
//List<WebElement> products = driver.findElements(productNames);
//for (WebElement product : products) {
//  if (product.getText().equalsIgnoreCase(productName)) {
//      product.findElement(By.xpath("./ancestor::div[@class='inventory_item']//button")).click();
//      break;
//  }
//}
//}
//
//
//public void clickOnProduct(String productName) {
//List<WebElement> products = driver.findElements(productNames);
//for (WebElement product : products) {
//  if (product.getText().equalsIgnoreCase(productName)) {
//      product.click();
//      break;
//  }
//}
//}
