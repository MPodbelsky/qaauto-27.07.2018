package test;

import page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinResetPasswordTest {
    private WebDriver browser;
    private LinkedinLoginPage linkedinLoginPage;
    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }
    @AfterMethod
    public void afterMethod(){
        browser.close();
    }

    @DataProvider
    public Object[][] loginAndNewPassword() {
        return new Object[][]{
                {"mathewsw1648@gmail.com", "G147852369"},
        };
    }

    @Test(dataProvider = "loginAndNewPassword")
    public void successfulResetPasswordTest (String userEmail, String userPass) {
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "InkedinLoginPage is not loaded");
        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage = linkedinLoginPage.clickForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(), "RequestPasswordResetPage is not loaded");
        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRequestPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(), "PasswordRessetSubmitPage is not loaded");
        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage = linkedinPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(), "page.LinkedinSetNewPasswordPage is not loaded");
        LinkedinResetPasswordIsDonePage LinkedinResetPasswordIsDonePage = linkedinSetNewPasswordPage.enterNewPassword(userPass);
        Assert.assertTrue(LinkedinResetPasswordIsDonePage.isLoaded(), "Password is not reset");
        LinkedinResetPasswordIsDonePage.clickReturnToLoginPage();
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on LoginSubmit page");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.logInReturnLinkedinHomePage(userEmail, userPass);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Linkedin home page is not loaded");
        }
}