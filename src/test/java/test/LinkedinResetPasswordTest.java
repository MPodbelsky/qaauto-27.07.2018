package test;

import page.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinResetPasswordTest extends BaseTest {

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
        LinkedinResetPasswordIsDonePage linkedinResetPasswordIsDonePage = linkedinSetNewPasswordPage.enterNewPassword(userPass);
        Assert.assertTrue(linkedinResetPasswordIsDonePage.isLoaded(), "Password is not reset");
        LinkedinHomePage linkedinHomePage = linkedinResetPasswordIsDonePage.clickReturnToLoginPage();
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Linkedin home page is not loaded");
        }
}