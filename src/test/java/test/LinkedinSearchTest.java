package test;

import page.LinkedinHomePage;
import page.LinkedinSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LinkedinSearchTest extends BaseTest {
    /**
     * Verify successful search
     *
     * - Login
     * - Search
     */
    @Test
    public void basicSearchTest(){
        Assert.assertTrue(linkedinLoginPage.isLoaded(), "User is not on LoginSubmit page");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login("mathewsw1648@gmail.com","G147852369");
        Assert.assertTrue(linkedinHomePage.isLoaded(),"HomePage is not loaded");
        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search("HR");
        Assert.assertTrue(linkedinSearchPage.isLoaded(),"Search page is not loaded");
        String serchTerm = "hr";
        List<String> searchResults = linkedinSearchPage.getSearchResultsList();
        for (String searchResult: searchResults) {
            Assert.assertTrue(searchResult.toLowerCase().contains(serchTerm),"searchTerm" + "not found in:  \n"+ searchResult);
        }
        Assert.assertEquals(linkedinSearchPage.getSearchResultsCount(),10,"Search results count is wrong");
    }
}