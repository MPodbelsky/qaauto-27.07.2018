import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestResetPasswordPage extends BasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement sendAgainButton;

    public RequestResetPasswordPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
    public boolean isLoaded() {
        return sendAgainButton.isDisplayed() && getCurrentPageTitle().contains("Проверьте, получили ли вы сообщение эл. почты со ссылкой для изменения пароля") && getCurrentPageUrl().contains("/checkpoint/");
    }
    public GmailLoginPage logInReturnGmailLoginPage(){
        browser.get("https://mail.google.com");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return new GmailLoginPage(browser);
    }
}
