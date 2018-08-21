import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinSearchTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;
    @BeforeMethod
    public void beforeMethod(){
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }
    @AfterMethod
    public void afterMethod(){
        browser.close();
    }
    @Test
    public void successfulLogin(){
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on LoginSubmit page");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.logInReturnLinkedinHomePage("mathewsw1648@gmail.com","G147852369");
        Assert.assertTrue(linkedinHomePage.isLoaded(),"HomePage is not loaded");
        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.searchTermEnter("HR");
        Assert.assertTrue(linkedinSearchPage.isLoaded(),"User is not on SearchPage page");
        Assert.assertTrue(linkedinSearchPage.isCountResultTen(),"Not 10 results");
        Assert.assertTrue(linkedinSearchPage.isContainsSearchTerm(),"No HR");
    }
}
