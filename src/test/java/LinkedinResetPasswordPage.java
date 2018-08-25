import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinResetPasswordPage extends BasePage{

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userNameField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public LinkedinResetPasswordPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
    public boolean isLoaded() {
        return userNameField.isDisplayed() && getCurrentPageTitle().contains("Изменить пароль | LinkedIn")&& getCurrentPageUrl().contains("/uas/request-password-reset");
    }

    public RequestResetPasswordPage resetPassReturnRequestResetPasswordPage(String userName) {
        userNameField.sendKeys(userName);
        resetPasswordSubmitButton.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return new RequestResetPasswordPage(browser);
    }
}
