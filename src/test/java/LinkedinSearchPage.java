import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinkedinSearchPage extends BasePage {
    @FindBy (xpath = "//ul[@class='search-results__list list-style-none']/li[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> searchResults;
    @FindBy (xpath = "//button[@class='search-filters-bar__all-filters button-tertiary-medium-muted flex-shrink-zero mr3']")
    private WebElement searchFilterButton;
    public LinkedinSearchPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }
    public boolean isLoaded() {
        return searchFilterButton.isDisplayed() && getCurrentPageTitle().contains("| Поиск | LinkedIn") && getCurrentPageUrl().contains("/results/");
    }
    public boolean isCountResultTen(){
        ((JavascriptExecutor)browser).executeScript("scroll(0,1000)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (searchResults.size() == 10){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isContainsSearchTerm(){
        ((JavascriptExecutor)browser).executeScript("scroll(0,1000)");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement searchResult:searchResults){
            String searchResultText = searchResult.getText();
            if (searchResultText.contains("HR")){
                return true;
            }
        }
        return isContainsSearchTerm();
    }
}
