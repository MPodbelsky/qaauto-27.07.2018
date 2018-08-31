import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinPasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public LinkedinPasswordResetSubmitPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
    public boolean isLoaded() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resendLinkButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/");
    }
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        System.out.println(LinkedinRequestPasswordResetPage.linkPassword);
        browser.get(linkPassword);
        return new LinkedinSetNewPasswordPage(browser);
    }
}