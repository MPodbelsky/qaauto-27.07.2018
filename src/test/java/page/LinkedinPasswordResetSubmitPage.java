package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public LinkedinPasswordResetSubmitPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resendLinkButton,10);
    }
    public boolean isLoaded() {
        return resendLinkButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/");
    }
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "mathewsw1648@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = BasePage.gMailService.waitMessage(messageSubject, messageTo, messageFrom, 190);
        System.out.println("Content: " + message);
        String resetPasswordLink = StringUtils.substringBetween(message,
                "Чтобы изменить пароль в LinkedIn, нажмите <a href=\"<a href=&quot;",
                "&quot>[text]</a>").replace("amp;","");
        System.out.println(resetPasswordLink);
        browser.get(resetPasswordLink);
        return new LinkedinSetNewPasswordPage(browser);
    }
}