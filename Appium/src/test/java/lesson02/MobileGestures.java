package lesson02;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class MobileGestures {

    private TouchAction action;
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    private final String expectedTime = "09:45";
    protected AndroidDriver<AndroidElement> driver = null;
    private AndroidElement title;
    private AndroidElement menu;
    private final String expectedMenu="Sample menu";
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability(MobileCapabilityType.UDID, "ce051605b5d4d82c03");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        action = new TouchAction(driver);
        //driver.setLogLevel(Level.INFO);
    }


    @Test
    public void test01_verifySwipe() {
        driver.findElement(By.xpath("//*[@contentDescription='Views']")).click();
        driver.findElement(By.xpath("//*[@contentDescription='Date Widgets']")).click();
        driver.findElement(By.xpath("//*[@contentDescription='2. Inline']")).click();

        AndroidElement hour_start_location = driver.findElement(By.xpath("//*[@contentDescription='12']"));
        AndroidElement hour_end_location = driver.findElement(By.xpath("//*[@contentDescription='9']"));

        PointOption start_hour = PointOption.point(hour_start_location.getRect().x, hour_start_location.getRect().y);
        PointOption end_hour = PointOption.point(hour_end_location.getRect().x, hour_end_location.getRect().y);

        swipeScreen(start_hour, end_hour);

        AndroidElement minute_start_location = driver.findElement(By.xpath("//*[@contentDescription='15']"));
        AndroidElement minute_end_location = driver.findElement(By.xpath("//*[@contentDescription='45']"));

        PointOption start_minutes = PointOption.point(minute_start_location.getRect().x, minute_start_location.getRect().y);
        PointOption end_minutes = PointOption.point(minute_end_location.getRect().x,minute_end_location.getRect().y+(minute_end_location.getRect().getHeight()/2));
        swipeScreen(start_minutes, end_minutes);
        String actual_hour=driver.findElementById("hours").getText();
        String actual_minute=driver.findElementById("minutes").getText();
        String actual_time=actual_hour+":"+actual_minute;
        Assert.assertEquals(actual_time,expectedTime);

    }

    @Step("Init Steps to verify title")
    public void moveToTitle()
    {
        driver.resetApp();
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        driver.findElement(By.xpath("//*[@text='Expandable Lists']")).click();
        driver.findElement(By.xpath("//*[@text='1. Custom Adapter']")).click();
        title= driver.findElement(By.xpath("//*[@text='People Names']"));
    }

    @Test
    public void test02_verifyTitleWithPress()
    {
        moveToTitle();
        action.press(new ElementOption().withElement(title)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).release().perform();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@text='Sample menu']")).getText(),expectedMenu);
    }

    @Test
    public void test03_verifyTitleWithTouchScreenPress()
    {
        moveToTitle();
        action.longPress(new LongPressOptions().withElement(ElementOption.element(title)).withDuration(Duration.ofSeconds(2))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@text='Sample menu']")).getText(),expectedMenu);
    }



    public void swipeScreen(PointOption start, PointOption end) {

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(start)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(end)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


}
