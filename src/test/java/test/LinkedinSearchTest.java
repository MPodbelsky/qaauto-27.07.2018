package test;

import page.LinkedinHomePage;
import page.LinkedinLoginPage;
import page.LinkedinSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinSearchTest {
    private WebDriver browser;
    private LinkedinLoginPage linkedinLoginPage;
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

    /**
     * Verify successfful search
     *
     * - Login
     * - Search
     */
    @Test
    public void basicSearchTest(){
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on LoginSubmit page");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.logInReturnLinkedinHomePage("mathewsw1648@gmail.com","B147852369");
        Assert.assertTrue(linkedinHomePage.isLoaded(),"HomePage is not loaded");
        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search("HR");
        Assert.assertTrue(linkedinSearchPage.isLoaded(),"Search page is not loaded");
        Assert.assertEquals(linkedinSearchPage.getSearchResultsCount(),10,"Search results count is wrong");
        Assert.assertTrue(linkedinSearchPage.isContainsSearchTerm(),"Search results is wrong");
    }
}
