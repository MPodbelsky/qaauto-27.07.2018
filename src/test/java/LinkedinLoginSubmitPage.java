import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage {
    private WebDriver browser;
    private WebElement alertBox;
    private WebElement userPasswordValidationText;
    private WebElement userEmailValidationText;

    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }
    private void initElements(){
        alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        userPasswordValidationText = browser.findElement(By.xpath("//span[@id='session_password-login-error']"));
        userEmailValidationText = browser.findElement(By.xpath("//span[@id='session_key-login-error']"));
    }
    public String getAlertBoxText(){
        return alertBox.getText();
    }
    public String getUserPasswordValidationText(){
       return userPasswordValidationText.getText();
    }
    public String getUserEmailValidationText(){
        return userEmailValidationText.getText();
    }
    public String getCurrentPageTitle(){
        return browser.getTitle();
    }
    public String getCurrentPageUrl(){
        return browser.getCurrentUrl();
    }
    public boolean isLoaded() {
        return alertBox.isDisplayed() && getCurrentPageTitle().contains("Войти в LinkedIn") && getCurrentPageUrl().contains("/uas/login-submit");
    }
}