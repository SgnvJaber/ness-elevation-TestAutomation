package lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(AutomationListeners.class)
public class ErrorHandlingWithListener {
    private WebDriver driver;
    private final int waitTime = 5;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");

    }

    @Test
    public void test01_verifyRemoveButtonWithTryAndCatch() throws InterruptedException {
        try {
            WebElement button = driver.findElement(By.xpath("//button[text()='Remove']"));
            button.click();
            Thread.sleep(waitTime * 1000);
            WebElement checkbox = driver.findElement(By.xpath("//div[@id='checkbox']"));
        } catch (Exception e) {
            System.out.println("Test Passed! Element is indeed gone!");
        }
    }

    @Test
    public void test02_verifyRemoveCheckBox() throws InterruptedException {
        driver.navigate().refresh();
        WebElement button = driver.findElement(By.xpath("//button[text()='Remove']"));
        button.click();
        Thread.sleep(waitTime * 1000);
        if (driver.findElements(By.xpath("//div[@id='checkbox']")).size() != 0) {
            System.out.println("Test Failed!");
        } else {
            System.out.println("Test Passed! Element is indeed gone!");
        }
    }

    @Test
    public void test03_verifyRemoveWithListener() throws InterruptedException {
        driver.navigate().refresh();
        WebElement button = driver.findElement(By.xpath("//button[text()='Remove']"));
        button.click();
        Thread.sleep(waitTime * 1000);
        WebElement checkbox = driver.findElement(By.xpath("//div[@id='checkbox']"));
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
