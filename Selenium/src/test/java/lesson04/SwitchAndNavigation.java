package lesson04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class SwitchAndNavigation {
    private WebDriver driver;
    private WebElement output;
    private final String alert_pressed = "Alert is gone.";
    private final String prompt = "Saed Jaber";
    private final String expectedFrameText = "This is an IFrame !";
    private final String expectedTapText = "This is a new tab";

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_switch_navigation.html");
    }

    @Test
    public void test01_getOutputField() {
        output = driver.findElement(By.xpath("//span[@id='output']"));
    }

    @Test
    public void test01_verifyShowAlert() {
        WebElement alert = driver.findElement(By.xpath("//input[@id='btnAlert']"));
        alert.click();
        Alert popup = driver.switchTo().alert();
        System.out.println(popup.getText());
        popup.accept();
        Assert.assertEquals(output.getText(), alert_pressed);
    }

    @Test
    public void test02_verifyShowPrompt() {
        WebElement alert = driver.findElement(By.xpath("//input[@id='btnPrompt']"));
        alert.click();
        Alert popup = driver.switchTo().alert();
        System.out.println(popup.getText());
        popup.sendKeys(prompt);
        popup.accept();
        Assert.assertEquals(output.getText(), prompt);
    }

    @Test
    public void test03_verifyIFrame() {
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        WebElement iFrameDiv = driver.findElement(By.cssSelector("div[id='iframe_container']"));
        String actualText = iFrameDiv.getText();
        driver.switchTo().parentFrame();
        Assert.assertEquals(actualText, expectedFrameText);
    }

    @Test
    public void test04_verifyOpenTapButton() {
        String parent_handle = driver.getWindowHandle();
        WebElement alert = driver.findElement(By.xpath("//input[@id='btnNewTab']"));
        alert.click();
        Set<String> winHandles = driver.getWindowHandles();
        System.out.println(winHandles.toString());

        for (String winHandle : winHandles) {
            driver.switchTo().window(winHandle);
        }
        System.out.println(driver.getCurrentUrl());
        WebElement tab_text = driver.findElement(By.xpath("//div[@id='new_tab_container']"));
        String actualText = tab_text.getText();
        System.out.println("Tap Text: " + actualText);
        Assert.assertEquals(actualText, expectedTapText);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
