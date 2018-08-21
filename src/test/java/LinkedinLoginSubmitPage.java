import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginSubmitPage extends BasePage{
    @FindBy(xpath = "//*[@role='alert']")
    private WebElement alertBox;

    @FindBy (xpath = "//span[@id='session_password-login-error']")
    private WebElement userPasswordValidationText;

    @FindBy (xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailValidationText;

    public LinkedinLoginSubmitPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser,this);
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
    public boolean isLoaded() {
        return alertBox.isDisplayed() && getCurrentPageTitle().contains("Войти в LinkedIn") && getCurrentPageUrl().contains("/uas/login-submit");
    }
}