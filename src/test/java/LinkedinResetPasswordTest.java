import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinResetPasswordTest {
        WebDriver browser;
        LinkedinLoginPage linkedinLoginPage;
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
    public Object[][] loginAndNewPasswordFields() {
        return new Object[][]{
                {"mathewsw1648@gmail.com", "M147852369"},
        };
    }

    @Test(dataProvider = "loginAndNewPasswordFields")
    public void linkedinResetPasswordTest (String userEmail, String userPass){
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on LoginSubmit page");
        LinkedinResetPasswordPage linkedinResetPasswordPage = linkedinLoginPage.logInReturnLinkedinResetPasswordPage();
        Assert.assertTrue(linkedinResetPasswordPage.isLoaded(), "Reset password page is not loaded");
        RequestResetPasswordPage requestResetPasswordPage = linkedinResetPasswordPage.resetPassReturnRequestResetPasswordPage(userEmail);
        Assert.assertTrue(requestResetPasswordPage.isLoaded(), "Request reset password page is not loaded");
        GmailLoginPage gmailLoginPage = requestResetPasswordPage.logInReturnGmailLoginPage();
        Assert.assertTrue(gmailLoginPage.isLoaded(), "Gmail login page is not loaded");
        LinkResetPassPage linkResetPassPage = gmailLoginPage.logInReturnLinkPassPage(userEmail);
        Assert.assertTrue(linkResetPassPage.isLoaded(), "Reset pass page is not loaded");
        ResetPasswordIsDonePage resetPasswordIsDonePage = linkResetPassPage.newPassReturnResetPasswordIsDonePage(userPass);
        Assert.assertTrue(resetPasswordIsDonePage.isLoaded(), "Password is not reset");
        LinkedinLoginPage linkedinLoginPage = resetPasswordIsDonePage.buttonClickReturnLinkedinLoginPage();
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on LoginSubmit page");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.logInReturnLinkedinHomePage(userEmail, userPass);
        Assert.assertTrue(linkedinHomePage.isLoaded(), "Linkedin home page is not loaded");
        }
}