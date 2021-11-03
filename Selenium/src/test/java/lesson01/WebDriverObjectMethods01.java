package lesson01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriverObjectMethods01 {
    private final String expectedTitleName = "IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";
    private final String expectedUrl = "https://www.imdb.com/";
    private WebDriver driver;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com/");
    }

    @Test
        public void connectToImdb() {
        driver.navigate().refresh();
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        // System.out.println("Title is: "+title);
        // System.out.println("Url is: "+url);
        if (expectedTitleName.equals(title)) {
            System.out.println("Title Test Passed!");
        } else {
            System.out.println("Title Test Failed...");

        }
        if (expectedUrl.equals(url)) {
            System.out.println("Url Test Passed!");
        } else {
            System.out.println("Url Test Failed...");

        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
