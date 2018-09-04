package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * Page Object class for LinkedinSearchPage.
 */
public class LinkedinSearchPage extends BasePage {
    @FindBy (xpath = "//li[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> searchResults;

    @FindBy (xpath = "//h3[contains(@class,'search-results__total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//button[@class='search-result__actions--primary button-secondary-medium m5']")
    private WebElement createContactButton;

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
        ((JavascriptExecutor)browser).executeScript("scroll(0,1000)");
        waitUntilElementIsVisible(createContactButton,10);
        return searchResults.size();
    }

    /**
     * Method isContainsSearchTerm verifies that each search result of search result list contains searchTerm.
     * @return true is each result contains searchTerm.
     */
    public boolean isContainsSearchTerm() {
        ((JavascriptExecutor) browser).executeScript("scroll(0,1000)");
        waitUntilElementIsVisible(createContactButton,10);
        boolean a = false;
        for (WebElement searchResult : searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.contains("HR")) {
                a = true;
            }
        }
        return a;
    }
}
