package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class for LinkedinHomePage.
 */
public class LinkedinHomePage extends BasePage {
    @FindBy (xpath = "//*[@id='profile-nav-item']")
    private WebElement profileNavidationItem;

    @FindBy (xpath = "//input[@placeholder='Поиск' and @role='combobox']")
    private WebElement searchField;

    /**
     * Constructor of LinkedinHomePage class.
     * @param browser - WebDriver instance from test.
     */
    public LinkedinHomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser,this);
        waitUntilElementIsVisible(profileNavidationItem,10);
    }

    /**
     * Method isLoaded for LinkedinHomePage.
     * @return true if LinkedinHomePage is loaded.
     */
    public boolean isLoaded() {
        return profileNavidationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }

    /**
     * Method which entered searchTerm into searchField.
     * @param searchTerm - search word.
     * @return LinkedinSearchPage after enter click.
     */
    public LinkedinSearchPage search(String searchTerm){
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new LinkedinSearchPage(browser);
    }
}