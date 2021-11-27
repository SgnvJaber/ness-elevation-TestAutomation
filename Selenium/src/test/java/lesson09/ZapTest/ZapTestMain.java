package lesson09.ZapTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class ZapTestMain {
    private WebDriver driver;
    private LoginPage login;
    private BookPage book;
    private Reservations reservations;
    private final String expectedUsername = "test";
    private final String expectedPassword = "demo";
    private final String url = "http://demo.zaptest.com/login";
    private final String expectedExpiredDateResult = "NO RESULTS ARE FOUND";
    private final String expectedFlight = "UA1029";

    @BeforeClass
    public void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        login = PageFactory.initElements(driver, LoginPage.class);
        book = PageFactory.initElements(driver, BookPage.class);
        reservations = PageFactory.initElements(driver, Reservations.class);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test01_verifyCredentials() {
        SoftAssert check = new SoftAssert();
        check.assertEquals(login.getUsername(), expectedUsername);
        check.assertEquals(login.getPassword(), expectedPassword);
        check.assertAll();
    }

    @Test
    public void test02_verifyExpiredDateResult() {
        login.signIn(login.getUsername(), login.getPassword());
        book.fromCity("New York");
        book.toCity("Los Angeles");
        book.selectDate("22", "11", "2021");
        book.passengersSelect(3);
        book.search();
        Assert.assertEquals(book.getSearchResultStatus(), expectedExpiredDateResult);
    }

    @Test
    public void test03_verifyValidDateResult() {
        login.signIn(login.getUsername(), login.getPassword());
        book.fromCity("New York");
        book.toCity("Los Angeles");
        book.selectDate("30", "11", "2025");
        book.passengersSelect(3);
        book.search();
        book.printFlightsList();
        Assert.assertTrue(book.containsFlight(expectedFlight));

    }


    @Test
    public void test03_verifyBookSubmit() {
        login.signIn(login.getUsername(), login.getPassword());
        book.fromCity("New York");
        book.toCity("Los Angeles");
        book.selectDate("30", "11", "2025");
        book.passengersSelect(3);
        book.search();
        book.bookFlight(expectedFlight);
        Assert.assertTrue(book.getSubmitResult().contains("is booked"));
    }

    @Test
    public void test04_verifyReservation() {
        login.signIn(login.getUsername(), login.getPassword());
        book.fromCity("New York");
        book.toCity("Los Angeles");
        book.selectDate("30", "11", "2025");
        book.passengersSelect(3);
        book.search();
        book.bookFlight(expectedFlight);
        book.navigateToReservation();
        Assert.assertTrue(reservations.containsFlight(expectedFlight));
    }

    @Test
    public void test05_verifyCancel() {
        login.signIn(login.getUsername(), login.getPassword());
        book.fromCity("New York");
        book.toCity("Los Angeles");
        book.selectDate("30", "11", "2025");
        book.passengersSelect(3);
        book.search();
        book.bookFlight(expectedFlight);
        book.navigateToReservation();
        reservations.cancelFlight(expectedFlight);
        Assert.assertFalse(reservations.containsFlight(expectedFlight));
    }

    @Test
    public void test06_verifyDateCurrent() {
        login.signIn(login.getUsername(), login.getPassword());
        book.fromCity("Atlanta");
        book.toCity("New York");
        book.search();
        Assert.assertEquals(book.numberOfFlights(), 2);
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
