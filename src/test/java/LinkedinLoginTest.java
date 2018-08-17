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
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.logInReturnLinkedinHomePage(userEmeil, userPass);
        Assert.assertTrue(linkedinHomePage.isLoaded(),"HomePage is loaded");
    }
                                     //NEGATIVE TESTS
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
        linkedinLoginPage.logInReturnLinkedInLoginPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginPage.isLoaded(),"User is not on Login page");
    }
    @DataProvider
    public Object[][] invalidDataFieldsCombination() {
        return new Object[][]{
                {"a", "a", "Слишком короткий текст (минимальная длина – 3 симв., введено – 1 симв.).", "Пароль должен содержать не менее 6 символов."},
                {"<mathewsw1648@gmail.com>@gmail.com","G147852369","Укажите действительный адрес эл. почты.",""},
                {"mathewsw1648@gmail.com","g147852369","","Это неверный пароль. Повторите попытку или измените пароль."},
                {"mathewsw1648@gmail.co","G147852369","Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.",""},
                {"zxczvvvdvfvdvdvfdvdfvdvdfvvregwer4hryhjhg4wgrgegegergegergefvsrtbtyrukilo;likujhgfedfguioiuhygfd147852369vtjukimusdcdcvsd@gmai.com", "G147852369", "Слишком длинный текст: максимальная длина – 128 симв., введено 130 симв.", ""}
        };
    }
    @Test(dataProvider = "invalidDataFieldsCombination")
    public void validateUserEmailAndPassword (String userEmail, String userPass, String userEmailErrorText, String userPasswordErrorText){
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.logInReturnLoginSubmitPage(userEmail, userPass);
        Assert.assertTrue(linkedinLoginSubmitPage.isLoaded(), "User is not on LoginSubmit page");
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertBoxText(),"При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.","Alert box has incorrect message!");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailValidationText(),userEmailErrorText,"Login error field wrong");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordValidationText(),userPasswordErrorText,"Password error field is wrong");
    }
}