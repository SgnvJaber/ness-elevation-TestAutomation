package lesson06.GraphicElements;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;


public class AutomatingGraphicElements {
    private WebDriver driver;
    private Actions action;
    private Screen screen;
    private final int waitTime = 10;
    private final String place = "Atidim";

    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/maps");
        action = new Actions(driver);
        screen = new Screen();

    }

    @Test
    public void test01_verifyCreateTask() {
        try {
            zoom_out();
            zoom_out();
            search(place);
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            zoom_in();
            zoom_in();
            findNearbyRestaurants();
        } catch (
                Exception e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }

    @Step("Zoom in Canvas")
    public void zoom_in() throws FindFailed {
        screen.click("C:\\Automation\\TestAutomation\\Selenium\\src\\test\\java\\lesson06\\GraphicElements\\plus.PNG");

    }


    @Step("Zoom out Canvas")
    public void zoom_out() throws FindFailed {
        screen.click("C:\\Automation\\TestAutomation\\Selenium\\src\\test\\java\\lesson06\\GraphicElements\\minus.PNG");

    }

    @Step("Search for location in Google Maps")
    public void search(String location) throws FindFailed {
        screen.type("C:\\Automation\\TestAutomation\\Selenium\\src\\test\\java\\lesson06\\GraphicElements\\search.PNG", location);
        screen.doubleClick();
    }

    @Step("Search for nearby Restaurants Suggestions")
    public void findNearbyRestaurants() throws FindFailed {
        screen.click("C:\\Automation\\TestAutomation\\Selenium\\src\\test\\java\\lesson06\\GraphicElements\\food.PNG");
    }

    @AfterClass
    public void closeBrowser() {
        //driver.quit();
    }


}






