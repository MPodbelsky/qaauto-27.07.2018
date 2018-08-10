import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinHomePage {
    WebDriver browser;
    WebElement profileNavidationItem;
    public LinkedinHomePage(WebDriver browser) {
        this.browser = browser;
        initElements();
    }
    public void initElements(){
        profileNavidationItem = browser.findElement(By.xpath("//*[@id='profile-nav-item']"));
    }
    public boolean isProfileNavigationItemDisplayed(){
      profileNavidationItem.isDisplayed();
        return true;
    }

}
