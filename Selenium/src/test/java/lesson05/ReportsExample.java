package lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class ReportsExample {
    private WebDriver driver;

    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://atidcollege.co.il/avengers/exercise/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test(priority = 0, description = "Login with IronMan.")
    @Description("Test Description: IronMan.")
    public void Test1_IronMan() {
        driver.findElement(By.id("iron_man")).click();
        login("IronMan", "IronManRocks");
        System.out.println("Title is: " + driver.getTitle());
        System.out.println("URL is: " + driver.getCurrentUrl());
        verifyScore("7.9");
    }

    @Test(priority = 1, description = "Login with CaptainAmerica.")
    @Description("Test Description: CaptainAmerica.")
    public void Test2_CaptainAmerica() {
        driver.navigate().to("http://atidcollege.co.il/avengers/exercise/");
        driver.findElement(By.id("captain_america")).click();
        login("CaptainAmerica", "CaptainAmericaRocks");
        System.out.println("Title is: " + driver.getTitle());
        System.out.println("URL is: " + driver.getCurrentUrl());
        verifyScore("6.9");
    }

    @Test(priority = 2, description = "Login with TheHulk.")
    @Description("Test Description: TheHulk.")
    public void Test3_TheHulk() {
        driver.navigate().to("http://atidcollege.co.il/avengers/exercise/");
        driver.findElement(By.id("the_hulk")).click();
        login("TheHulk", "TheHulkRocks");
        System.out.println("Title is: " + driver.getTitle());
        System.out.println("URL is: " + driver.getCurrentUrl());
        //actual is:6.6
        verifyScore("kuku");
    }

    @Step("Fill in with Credentials.")
    public void login(String user, String pass) {
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("submit")).click();
    }

    @Step("Verify Movie Score")
    public void verifyScore(String expected) {
        try {
            Assert.assertEquals(driver.findElement(By.xpath("//span[@class='AggregateRatingButton__RatingScore-sc-1ll29m0-1 iTLWoV']")).getText(), expected);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
