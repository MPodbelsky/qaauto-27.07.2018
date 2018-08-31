package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class LinkedinSearchPage extends BasePage {
    @FindBy (xpath = "//li[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> searchResults;

    @FindBy (xpath = "//h3[contains(@class,'search-results__total')]")
    private WebElement searchResultsTotal;

    public LinkedinSearchPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
        waitUntilElementIsVisible(searchResultsTotal,10);
    }
    public boolean isLoaded() {
        return searchResultsTotal.isDisplayed()
                && getCurrentPageTitle().contains("| Поиск | LinkedIn")
                && getCurrentPageUrl().contains("/search/results/");
    }
    public int getSearchResultsCount(){
        ((JavascriptExecutor)browser).executeScript("scroll(0,1000)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return searchResults.size();
    }
    public boolean isContainsSearchTerm() {
        ((JavascriptExecutor) browser).executeScript("scroll(0,1000)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
