package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSetNewPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public LinkedinSetNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resetPasswordSubmitButton, 10);
    }

    public boolean isLoaded() {
        return resetPasswordSubmitButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }

    public LinkedinEnterNewPassPage enterNewPassword(String newPass) {
        newPasswordField.sendKeys(newPass);
        confirmPasswordField.sendKeys(newPass);
        resetPasswordSubmitButton.click();
        return new LinkedinEnterNewPassPage(browser);
    }
}
