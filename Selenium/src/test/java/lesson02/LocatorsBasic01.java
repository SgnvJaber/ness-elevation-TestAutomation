package lesson02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorsBasic01 {
    private WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/");
    }

    @Test
    public void test01_verify_logo() {
        WebElement logo = driver.findElement(By.id("selenium_logo"));
        System.out.println("Logo: " + logo);
    }

    @Test
    public void test02_verify_numberOfLinks() {
        List<WebElement> links= driver.findElements(By.tagName("a"));
        System.out.println("Links number: " + links.size());
    }

    @Test
    public void test03_verify_numberOfLinksWithSeleniumUpper() {
        List<WebElement> SeleniumlinksUpper = driver.findElements(By.partialLinkText("Selenium"));
        System.out.println("Links with Selenium Upper: "+ SeleniumlinksUpper.size());
    }
    @Test
    public void test04_verify_numberOfLinksWithSeleniumLower() {
        List<WebElement> SeleniumlinksLower = driver.findElements(By.partialLinkText("selenium"));
        System.out.println("Links with Selenium Lower: "+ SeleniumlinksLower.size());
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
