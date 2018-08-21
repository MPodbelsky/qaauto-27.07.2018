import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinHomePage extends BasePage {
    @FindBy (xpath = "//*[@id='profile-nav-item']")
    private WebElement profileNavidationItem;
    @FindBy (xpath = "//input[@role='combobox']")
    private WebElement inputSearchField;
    @FindBy (xpath = "//button[@class='search-typeahead-v2__button typeahead-icon']")
    private WebElement searchButton;

    public LinkedinHomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser,this);
    }
    public boolean isLoaded() {
        return profileNavidationItem.isDisplayed() && getCurrentPageTitle().contains("LinkedIn") && getCurrentPageUrl().contains("/feed/");
    }
    public LinkedinSearchPage searchTermEnter(String searchTerm){
        inputSearchField.sendKeys(searchTerm);
        searchButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinSearchPage(browser);
    }
}