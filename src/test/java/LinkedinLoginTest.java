import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
    WebDriver browser;
    LinkedinLoginPage linkedinLoginPage;
    @BeforeMethod
    public void beforeMethod() {
        browser = new FirefoxDriver();
        browser.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(browser);
    }
    @AfterMethod
    public void afterMethod(){
        browser.close();
    }
    @DataProvider
    public Object[][] validFieldsCombination() {
        return new Object[][]{
                { "mathewsw1648@gmail.com", "G147852369" },
                { "MATHEWSW1648@gmail.com", "G147852369" },
            };
    }
    @Test(dataProvider = "validFieldsCombination") //annotation
    public void successfulLoginTest(String userEmeil, String userPass) {
        linkedinLoginPage.logIn(userEmeil, userPass);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(browser);
        Assert.assertTrue(linkedinHomePage.isLoaded(),"HomePage is loaded");
    }
                                     //NEGATIVE TESTS
    @Test //annotation
    public void negativeLoginTest() {
        linkedinLoginPage.logIn("Test@gmail.com","gfdhhrh");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),"При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.","Alert box has incorrect message!");
    }
    @Test //annotation
    public void wrongSymbolsLogin() {
        linkedinLoginPage.logIn("<mathewsw1648@gmail.com>@gmail.com","G147852369");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(),"Укажите действительный адрес эл. почты.","Login Error has incorrect message!");
    }
    @Test //annotation
    public void lowerCasePassword() {
        linkedinLoginPage.logIn("mathewsw1648@gmail.com","g147852369");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText()," Это неверный пароль. Повторите попытку или ","Password has incorrect message!");
    }
    @DataProvider
    public Object[][] emptyFieldsCombination() {
        return new Object[][]{
                { "", "" },
                { "", "P@ssword123" },
                { "someone@domain.com", "" }
        };
    }
    @Test(dataProvider = "emptyFieldsCombination") //annotation
    public void validateEmptyUserEmailAndUserPass(String userEmail, String userPass) {
        linkedinLoginPage.logIn(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"User is not on Login page");
    }
    @Test
    public void validateShortUserEmailAndPassword (){
        linkedinLoginPage.logIn("a","a");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(browser);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page");
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),"При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.","Alert box has incorrect message!");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(),"Слишком короткий текст (минимальная длина – 3 симв., введено – 1 симв.).","Login error field wrong");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(),"Пароль должен содержать не менее 6 символов.","Password error field is wrong");
    }
}