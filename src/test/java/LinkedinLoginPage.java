import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginPage extends BasePage{
    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;
    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;
    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement singInButton;

    public LinkedinLoginPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
    public boolean isLoaded(){
        return singInButton.isDisplayed() && getCurrentPageTitle().contains("LinkedIn: Войти или зарегистрироваться");
    }
    public LinkedinLoginSubmitPage logInReturnLoginSubmitPage(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinLoginSubmitPage(browser);
    }
    public LinkedinHomePage logInReturnLinkedinHomePage (String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinHomePage(browser);
    }
    public LinkedinLoginPage logInReturnLinkedInLoginPage(String userEmail, String userPass){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPass);
        singInButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinLoginPage(browser);
    }
}