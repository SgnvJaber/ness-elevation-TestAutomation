package lesson06;
//By:Saed Jaber,ID:208480632

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class PizzaOrder {
    private WebDriver driver;
    private final String firstName = "Saed";
    private final String lastName = "Jaber";
    private final String choice = "Delivery|3";
    private final String expectedInitialPrice = "$7.50";
    private final String expectedUpdatedPrice = "$10.50";

    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void fillDetails() {
        driver.findElement(By.cssSelector("input[name='input_22.3']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='input_22.6']")).sendKeys(lastName);
    }

    private void clearDetails() {
        driver.findElement(By.cssSelector("input[name='input_22.3']")).clear();
        driver.findElement(By.cssSelector("input[name='input_22.6']")).clear();
    }

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/pizza/");
    }

    @Test
    public void test01_verifyInitialPrice() {
        try {
            WebElement initial_price = driver.findElement(By.cssSelector("span[class='ginput_total ginput_total_5']"));
            System.out.println("Initial Price: " + initial_price.getText());
            Assert.assertEquals(initial_price.getText(), expectedInitialPrice);
        } catch (Exception e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }

    @Test
    public void test02_verifyUpdatedPrice() {
        try {
            fillDetails();
            Select mySelection = new Select(driver.findElement(By.id("input_5_21")));
            mySelection.selectByValue(choice);
            WebElement updated_price = driver.findElement(By.cssSelector("span[class='ginput_total ginput_total_5']"));
            System.out.println("Updated Price: " + updated_price.getText());
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Assert.assertEquals(updated_price.getText(), expectedUpdatedPrice);
        } catch (Exception e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }

    @Test
    public void test03_verifyCoupon() {
        try {
            //Clearing Details and filling them again so the tests won't be dependent
            clearDetails();
            fillDetails();
            WebElement iframe = driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iframe);
            String myCoupon = driver.findElement(By.cssSelector("div[id='coupon_Number']")).getText();
            driver.switchTo().parentFrame();
            driver.findElement(By.cssSelector("textarea[id='input_5_20']")).sendKeys(myCoupon);
            System.out.println("Coupon: " + myCoupon);
            WebElement alert = driver.findElement(By.cssSelector("input[id='gform_submit_button_5']"));
            alert.click();
            Alert popup = driver.switchTo().alert();
            String text = popup.getText();
            System.out.println("Popup Text: " + text);
            popup.accept();
            Assert.assertEquals(text, firstName + " " + lastName + " " + myCoupon);
        } catch (
                Exception e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
