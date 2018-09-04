package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinPasswordResetSubmitPage.
 */
public class LinkedinPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    /**
     * Constructor of LinkedinPasswordResetSubmitPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinPasswordResetSubmitPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resendLinkButton,10);
    }

    /**
     * Method isLoaded for LinkedinPasswordResetSubmitPage.
     * @return true if LinkedinPasswordResetSubmitPage is loaded.
     */
    public boolean isLoaded() {
        return resendLinkButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/");
    }

    /**
     * Method which takes reset password link from email and send to browser.
     * @return LinkedinSetNewPasswordPage if link is correct.
     */
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "mathewsw1648@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = BasePage.gMailService.waitMessage(messageSubject, messageTo, messageFrom, 190);
        System.out.println("Content: " + message);
        String resetPasswordLink = StringUtils.substringBetween(message,
                "Чтобы изменить пароль в LinkedIn, нажмите <a href=\"",
                "\" style").replace("amp;","");
        System.out.println(resetPasswordLink);
        browser.get(resetPasswordLink);
        return new LinkedinSetNewPasswordPage(browser);
    }
}