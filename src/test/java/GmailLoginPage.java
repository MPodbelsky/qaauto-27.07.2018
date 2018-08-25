import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailLoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement emailField;

    @FindBy(xpath = "//div[@id='identifierNext']")
    private WebElement buttonNext;

    public GmailLoginPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public boolean isLoaded() {
        return emailField.isDisplayed() && getCurrentPageTitle().contains("Gmail") && getCurrentPageUrl().contains("google");
    }

    public LinkResetPassPage logInReturnLinkPassPage(String userEmail) {
        emailField.sendKeys(userEmail);
        buttonNext.click();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return new LinkResetPassPage(browser);
    }
}
