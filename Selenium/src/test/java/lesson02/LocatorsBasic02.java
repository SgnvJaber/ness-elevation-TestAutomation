package lesson02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LocatorsBasic02 {
    private WebDriver driver;
    private List<WebElement> list=new ArrayList<>();
    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");
    }

    @Test
    public void test01_verify_logo() {
        WebElement logo = driver.findElement(By.className("central-featured-logo"));
        list.add(logo);
    }

    @Test
    public void test02_verify_searchBar() {
        WebElement searchBar = driver.findElement(By.id("searchInput"));
        list.add(searchBar);
    }

    @Test
    public void test03_verify_searchLanguage() {
        WebElement searchLanguage = driver.findElement(By.id("searchLanguage"));
        list.add(searchLanguage);
    }
    @Test
    public void test04_verify_footerSideBar() {
        WebElement footerSideBar = driver.findElement(By.className("footer-sidebar-content"));
        list.add(footerSideBar);
    }
    @Test
    public void test05_printElements() {
       for(int i= list.size()-1;i>=0;i--)
       {
           System.out.println("Element: "+list.get(i));
       }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
