import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinEnterNewPassPage extends BasePage {

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public LinkedinEnterNewPassPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return resetPasswordSubmitButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset-submit");
    }
    public LinkedinLoginPage clickReturnToLoginPage(){
        resetPasswordSubmitButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return new LinkedinLoginPage(browser);
    }
}
