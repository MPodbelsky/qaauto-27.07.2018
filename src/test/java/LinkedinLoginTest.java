import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
    @Test //annotation
    public void successfulLoginTest() throws InterruptedException {
        WebDriver browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        WebElement userEmailField = browser.findElement(By.xpath("//input[@class='login-email']")) ;
        WebElement userPasswordField = browser.findElement(By.xpath("//input[@class='login-password']")) ;
        WebElement singInButton = browser.findElement(By.xpath("//input[@class='login submit-button']"));
        userEmailField.sendKeys("mathewsw1648@gmail.com");
        userPasswordField.sendKeys("G147852369");
        singInButton.click();
        Thread.sleep(3000);
        Assert.assertEquals(browser.getCurrentUrl(),"https://www.linkedin.com/feed/","Incorrect URL!");
        Assert.assertEquals(browser.getTitle(),"LinkedIn","Incorrect Title");
        WebElement launchpadTitle = browser.findElement(By.xpath("//*[@class = 'launchpad__title launchpad__title--is-open Sans-21px-black-85%-dense pb5 fl']"));
        Assert.assertEquals(launchpadTitle.getText(),"Ilya, начните использовать LinkedIn, выполнив 3 простых шага:","Launchpad title has incorrect message!");
        browser.close();
    }
    @Test //annotation
    public void negativeLoginTest() throws InterruptedException {
        WebDriver browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        WebElement userEmailField = browser.findElement(By.xpath("//input[@class='login-email']")) ;
        WebElement userPasswordField = browser.findElement(By.xpath("//input[@class='login-password']")) ;
        WebElement singInButton = browser.findElement(By.xpath("//input[@class='login submit-button']"));
        userEmailField.sendKeys("Test@gmail.com");
        userPasswordField.sendKeys("456789123");
        singInButton.click();
        Thread.sleep(3000);
        WebElement alertBox = browser.findElement(By.xpath("//*[@role='alert']"));
        Assert.assertEquals(alertBox.getText(),"При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.","Alert box has incorrect message!");
        browser.close();
    }

}
