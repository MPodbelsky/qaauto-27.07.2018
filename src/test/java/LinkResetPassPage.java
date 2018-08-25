import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkResetPassPage extends BasePage {

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public LinkResetPassPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return resetPasswordSubmitButton.isDisplayed() && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }

    public ResetPasswordIsDonePage newPassReturnResetPasswordIsDonePage(String newPass) {
        newPasswordField.sendKeys(newPass);
        confirmPasswordField.sendKeys(newPass);
        resetPasswordSubmitButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return new ResetPasswordIsDonePage(browser);
    }
}