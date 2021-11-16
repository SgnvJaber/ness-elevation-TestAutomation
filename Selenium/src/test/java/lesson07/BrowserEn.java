package lesson07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserEn {
    private WebDriver driver;
    private List<WebElement> list = new ArrayList<>();
    private WebDriverWait wait;
    private final int waitTime = 10;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-GB");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/maps");
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

    }

    @Test
    public void test01() {
        System.out.println("hello");
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
