package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinHomePage extends BasePage {
    @FindBy (xpath = "//*[@id='profile-nav-item']")
    private WebElement profileNavidationItem;

    @FindBy (xpath = "//input[@placeholder='Поиск' and @role='combobox']")
    private WebElement searchField;

    public LinkedinHomePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser,this);
        waitUntilElementIsVisible(profileNavidationItem,10);
    }
    public boolean isLoaded() {
        return profileNavidationItem.isDisplayed()
                && getCurrentPageTitle().contains("LinkedIn")
                && getCurrentPageUrl().contains("/feed/");
    }
    public LinkedinSearchPage search(String searchTerm){
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new LinkedinSearchPage(browser);
    }
}