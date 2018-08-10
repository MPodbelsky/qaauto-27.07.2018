import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
    WebDriver browser;

    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
    }
    @AfterMethod
    public void afterMethod(){
        browser.close();
    }

    @Test //annotation
    public void successfulLoginTest() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new  LinkedinLoginPage(browser);
        linkedinLoginPage.logIn("mathewsw1648@gmail.com","G147852369");
        Thread.sleep(3000);
        Assert.assertEquals(browser.getCurrentUrl(), "https://www.linkedin.com/feed/", "Incorrect URL!");
        Assert.assertEquals(browser.getTitle(), "LinkedIn", "Incorrect Title");
        WebElement profileDropdown = browser.findElement(By.xpath("//*[@id='profile-nav-item']"));
        Assert.assertTrue(profileDropdown.isDisplayed(), "No profile dropdown on home page");
    }
    @Test //annotation
    public void negativeLoginTest() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(browser);
        linkedinLoginPage.logIn("Test@gmail.com","gfdhhrh");
        Thread.sleep(3000);
        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(),"При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.","Alert box has incorrect message!");
    }

}
