package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

    private WebDriver driver;

    private By burgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    public void openBurgerMenu() {
        driver.findElement(burgerMenu).click();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    //Combined Action 
    public void logout() {
        openBurgerMenu();
        clickLogout();
    }

    //Verification
    public boolean isLogoutSuccessful() {
        return driver.findElement(By.id("login-button")).isDisplayed();
    }
}
