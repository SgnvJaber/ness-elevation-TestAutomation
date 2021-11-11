package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataDrivenTestingProviderNotSameClass {
    private WebDriver driver;
    private List<WebElement> list = new ArrayList<>();
    private WebDriverWait wait;
    private final int waitTime = 10;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    @Test(dataProviderClass = lesson06.ExternalProvider.class, dataProvider = "data-provider")
    public void test01_verify_searchBar(String input, String expected) {
        driver.findElement(By.id("searchInput")).sendKeys(input);
        driver.findElement(By.xpath("//input[@id='searchButton']")).click();
        Assert.assertEquals(driver.findElement(By.id("firstHeading")).getText(), expected);
        driver.navigate().back();

    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}