import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

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
        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "mathewsw1648@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        int startIndex = message.lastIndexOf("https://www.linkedin.com/e/");
        int endIndex = message.lastIndexOf(";>[text]</a>");
        String newUrl = String.valueOf(new char[startIndex - endIndex]);
        System.out.println("Content: " + message);
        System.out.println("NEW URL!!! "+ newUrl);
        browser.get(newUrl);
        return new LinkedinSetNewPasswordPage(browser);
    }
}