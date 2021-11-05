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

public class LocatorsAdvanced {

    private WebDriver driver;
    private List<WebElement> list = new ArrayList<>();

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_locators.html");
    }

    @Test
    public void test01_findLocator1() {
        WebElement locator1 = driver.findElement(By.id("locator_id"));
        list.add(locator1);
    }

    @Test
    public void test02_findLocator2() {
        WebElement locator2 = driver.findElement(By.name("locator_name"));
        list.add(locator2);
    }

    @Test
    public void test03_findLocator3() {
        WebElement locator3 = driver.findElement(By.tagName("p"));
        list.add(locator3);
    }

    @Test
    public void test05_findLocator4() {
        WebElement locator4 = driver.findElement(By.className("locator_class"));
        list.add(locator4);
    }

    @Test
    public void test05_findLocator5() {
        WebElement locator5 = driver.findElement(By.linkText("myLocator(5)"));
        list.add(locator5);
    }

    @Test
    public void test05_findLocator6() {
        WebElement locator6 = driver.findElement(By.partialLinkText("locator (6)"));
        list.add(locator6);
    }

    @Test
    public void test05_findLocator7() {
        WebElement locator7 = driver.findElement(By.xpath("//input[@myname='selenium']"));
        list.add(locator7);
    }

    @Test
    public void test08_findLocator8() {
        WebElement locator8 = driver.findElement(By.cssSelector("button[class='btn btn-2'"));
        list.add(locator8);
    }

    @Test
    public void test09_printElements() {
        for (WebElement element : list) {
            System.out.println("Element: " + element);
        }
    }

    //Bonus Section
    @Test
    public void test10_printContent() {
        for (int i = 0; i < list.size(); i++) {

            if (i == 6) {
                System.out.println("Element " + (i + 1) + " " + list.get(i).getAttribute("value"));

            } else {
                System.out.println("Element " + (i + 1) + " " + list.get(i).getText());

            }

        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
