package lesson01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebDriverExample {
    @Test
    public void test01() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        driver.manage().window().maximize();
        System.out.println("Title is: " + driver.getTitle());
        System.out.println("Url is: " + driver.getCurrentUrl());
        //driver.close(); //close browser
       //  driver.quit(); //close tab



    }
}
