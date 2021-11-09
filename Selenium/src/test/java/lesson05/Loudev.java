package lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Loudev {
    WebDriver driver;
    @BeforeClass
    public void openBrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://loudev.com/");
    }
    @Test
    public void test01()
    {
        Support.verifyElements(driver);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
