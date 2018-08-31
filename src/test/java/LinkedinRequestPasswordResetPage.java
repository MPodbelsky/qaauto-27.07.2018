import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinRequestPasswordResetPage extends BasePage{

    @FindBy(xpath = "//input[@id='username']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public LinkedinRequestPasswordResetPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
    public boolean isLoaded() {
        return findAccountButton.isDisplayed()
                && getCurrentPageTitle().contains("Изменить пароль | LinkedIn")
                && getCurrentPageUrl().contains("/uas/request-password-reset");
    }

    public LinkedinPasswordResetSubmitPage findAccount(String userName) {
        gMailService = new GMailService(userName, "");
        gMailService.connect();
        userEmailField.sendKeys(userName);
        findAccountButton.click();
        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "mathewsw1648@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        int startIndex = message.indexOf("Чтобы изменить пароль в LinkedIn, нажмите <a href=\"");
        int endIndex = message.indexOf("\" style= ");
        linkPassword =  newUrl.substring(startIndex, endIndex);
        System.out.println("Content: " + message);
        linkPassword = newUrl.substring(newUrl.indexOf("https://www.linkedin.com/e/"), newUrl.indexOf(" "));
        return new LinkedinPasswordResetSubmitPage(browser);
    }
}
