package lesson09.ZapTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class ZapTestMain {
    private WebDriver driver;
    private LoginPage login;
    private BookPage book;
    private final String expectedUsername = "test";
    private final String expectedPassword = "demo";


    private final String url = "http://demo.zaptest.com/login";

    @BeforeClass
    public void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        login = PageFactory.initElements(driver, LoginPage.class);
        book = PageFactory.initElements(driver, BookPage.class);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test01_verifyCredentials() {
        SoftAssert check=new SoftAssert();
        check.assertEquals(login.getUsername(),expectedUsername);
        check.assertEquals(login.getPassword(),expectedPassword);
        check.assertAll();
    }

    @Test
    public void test02_verifySearchButton() {
        login.signIn(login.getUsername(), login.getPassword());
        book.fromCity("New York");
        book.toCity("Los Angeles");
        book.selectDate("22","11","2021");
        book.passengersSelect(3);
        book.search();

    }

    @Test
    public void test03_verifyData() {

    }


    @AfterClass
    public void closeBrowser() {
        //driver.quit();
    }
}
