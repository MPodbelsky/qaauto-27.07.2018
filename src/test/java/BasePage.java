import org.openqa.selenium.WebDriver;
import util.GMailService;

public abstract class BasePage {
    WebDriver browser;
    protected GMailService gMailService;
    protected String newUrl;
    protected static String linkPassword;

    public String getCurrentPageTitle(){
        return browser.getTitle();
    }
    public String getCurrentPageUrl(){
        return browser.getCurrentUrl();
    }
    public abstract boolean isLoaded();
}