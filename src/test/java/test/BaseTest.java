package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LinkedinLoginPage;

public class BaseTest {
    private WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;

    @Parameters({"browserName","evnUrl"})
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browserName,
                             @Optional("https://www.linkedin.com/")String evnUrl){
        if (browserName.toLowerCase().equals("firefox")) {
            browser = new FirefoxDriver();
        }
        if (browserName.toLowerCase().equals("chrome")){
            browser = new ChromeDriver();
        }else {
            try {
                throw new Exception("browserName" + " is not supported");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        browser.get(evnUrl);
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }
    @AfterMethod
    public void afterMethod(){
        browser.close();
    }
}
