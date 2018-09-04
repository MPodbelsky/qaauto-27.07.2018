package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinSetNewPasswordPage.
 */
public class LinkedinSetNewPasswordPage extends BasePage {

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    /**
     * Constructor of LinkedinSetNewPasswordPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinSetNewPasswordPage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(resetPasswordSubmitButton, 10);
    }

    /**
     * Method isLoaded for LinkedinSetNewPasswordPage.
     * @return true if LinkedinSearchPage is loaded.
     */
    public boolean isLoaded() {
        return resetPasswordSubmitButton.isDisplayed()
                && getCurrentPageUrl().contains("/checkpoint/rp/password-reset");
    }

    /**
     * Method enterNewPassword type new password into newPassword and confirmPassword fields.
     * @param newPass - new password.
     * @return LinkedinResetPasswordIsDonePage.
     */
    public LinkedinResetPasswordIsDonePage enterNewPassword(String newPass) {
        newPasswordField.sendKeys(newPass);
        confirmPasswordField.sendKeys(newPass);
        resetPasswordSubmitButton.click();
        return new LinkedinResetPasswordIsDonePage(browser);
    }
}
