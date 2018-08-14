import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginPage {
    private WebDriver browser;
    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement singInButton;

    public LinkedinLoginPage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }
    private void initElements(){
        userEmailField = browser.findElement(By.xpath("//input[@id='login-email']"));
        userPasswordField = browser.findElement(By.xpath("//input[@id='login-password']"));
        singInButton = browser.findElement(By.xpath("//input[@id='login-submit']"));
    }
    public String getCurrentPageTitle(){
        return browser.getTitle();
    }
    public boolean isPageLoaded(){
        return singInButton.isDisplayed() && getCurrentPageTitle().contains("LinkedIn: Войти или зарегистрироваться");
    }
    public void logIn(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}