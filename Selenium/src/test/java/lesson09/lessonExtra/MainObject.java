package lesson09.lessonExtra;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainObject {
    private WebDriver driver;
    private LocateSvgPage page;


    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/my-list.html");
        page = PageFactory.initElements(driver, LocateSvgPage.class);
    }
    @Test
    public void test01_verifyLoginPage() {
        System.out.println(page.getVSize());
        Assert.assertEquals(page.getVSize(),2);

    }
    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
