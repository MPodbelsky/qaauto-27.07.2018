package page;

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
        waitUntilElementIsVisible(resetPasswordSubmitButton, 10);
    }

    public boolean isLoaded() {
        return resetPasswordSubmitButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset-submit");
    }
    public LinkedinLoginPage clickReturnToLoginPage(){
        resetPasswordSubmitButton.click();
        return new LinkedinLoginPage(browser);
    }
}
