package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

public abstract class BasePage {
    WebDriver browser;
    protected static GMailService gMailService = new GMailService();


    public String getCurrentPageTitle(){
        return browser.getTitle();
    }
    public String getCurrentPageUrl(){
        return browser.getCurrentUrl();
    }
    public abstract boolean isLoaded();
    public WebElement waitUntilElementIsVisible (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(browser, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }
}