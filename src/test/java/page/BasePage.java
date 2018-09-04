package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * Page Object class for BasePaje.
 */
public abstract class BasePage {
    WebDriver browser;
    protected static GMailService gMailService = new GMailService();


    /**
     * Method wich has got current page title.
     * @return Current page title.
     */
    public String getCurrentPageTitle(){
        return browser.getTitle();
    }

    /**
     * Method wich has got current page url.
     * @return Current page url.
     */
    public String getCurrentPageUrl(){
        return browser.getCurrentUrl();
    }

    /**
     * Abstract method isLoaded for each page.
     */
    public abstract boolean isLoaded();

    /**
     * Method waitUntilElementIsVisible is waiting until element becomes visible.
     * @param webElement - element that should becomes visible.
     * @param timeOutInSeconds - timeout for webelement waiting.
     * @return visible webElement.
     */
    public WebElement waitUntilElementIsVisible (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(browser, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }
}