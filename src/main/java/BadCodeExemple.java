import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import static java.lang.Thread.sleep;


public class BadCodeExemple {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello world!!!");
        WebDriver browser = new FirefoxDriver();
        browser.get("https://www.google.com/");
        WebElement queryField = browser.findElement(By.name("q"));
        queryField.sendKeys("Selenium");
        queryField.sendKeys(Keys.ENTER);
        sleep(3000);
        //verify that results contains 10 elements
        List<WebElement> searchResults = browser.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        if (searchResults.size()== 10){
        System.out.println("Results count is correct: "+searchResults.size());
        }
        else {
            System.out.println("Results count is incorrect "+searchResults.size());
        }
        //verify that each result item contains saerchterm
        //for each searchResult in searchResult list
        for (WebElement searchResult: searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.toLowerCase().contains("selenium")){
                System.out.println("Searchterm found.");
            }
            else {
                System.out.println("Searchterm not found.");
            }
        }
        browser.close();
    }
}