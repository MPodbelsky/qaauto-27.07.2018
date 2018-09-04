package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object class for LinkedinSearchPage.
 */
public class LinkedinSearchPage extends BasePage {
    @FindBy (xpath = "//li[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> searchResults;

    @FindBy (xpath = "//h3[contains(@class,'search-results__total')]")
    private WebElement searchResultsTotal;

    /**
     * Constructor of LinkedinSearchPage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinSearchPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(searchResultsTotal,10);
    }

    /**
     * Method isLoaded for LinkedinSearchPage.
     * @return true if LinkedinSearchPage is loaded.
     */
    public boolean isLoaded() {
        return searchResultsTotal.isDisplayed()
                && getCurrentPageTitle().contains("| Поиск | LinkedIn")
                && getCurrentPageUrl().contains("/search/results/");
    }

    /**
     * Method which gets size of searchRasults list.
     * @return size of searchResult list.
     */
    public int getSearchResultsCount(){
        return searchResults.size();
    }

    /**
     * //TODO
     * @return
     */
    public List<String> getSearchResultsList() {
        List<String> searchResultList = new ArrayList<String>();
        for (WebElement searchResult: searchResults){
            ((JavascriptExecutor)browser).executeScript("arguments[0].scrollIntoView();", searchResult);
            searchResultList.add(searchResult.getText());
        }
        return searchResultList;
    }
}
