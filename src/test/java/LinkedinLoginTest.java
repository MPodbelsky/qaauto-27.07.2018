import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
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
    @Test //annotation
    public void successfulLoginTest() {
        linkedinLoginPage.logIn("mathewsw1648@gmail.com","G147852369");
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(),"HomePage is loaded");
    }
    @Test //annotation
    public void negativeLoginTest() {
        linkedinLoginPage.logIn("Test@gmail.com","gfdhhrh");
        LinkedinLoginSubmitPage LinkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(LinkedinLoginSubmitPage.getAlertBoxText(),"При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.","Alert box has incorrect message!");
    }
}