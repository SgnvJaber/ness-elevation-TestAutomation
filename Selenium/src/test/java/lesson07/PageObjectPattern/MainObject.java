package lesson07.PageObjectPattern;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainObject {
    private WebDriver driver;
    private LoginPage login;
    private FormPage form;
    private ClickPage click_page;

    private final String username = "selenium";
    private final String password = "webdriver";
    private final String occupation = "QA";
    private final String age = "25";
    private final String location = "Abu Ghosh";

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/webdriveradvance.html");
        login = PageFactory.initElements(driver, LoginPage.class);
        form = PageFactory.initElements(driver, FormPage.class);
        click_page = PageFactory.initElements(driver, ClickPage.class);

    }
    @Test
    public void test01_verifyLoginPage() {
        login.sign_in(username, password);
        form.fillForm(occupation, age, location);
        Assert.assertTrue(click_page.isDisplayed());
    }
    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
