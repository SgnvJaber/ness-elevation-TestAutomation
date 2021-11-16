package lesson02;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class MobileMethods {


    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    private final int expectedSize = 11;
    private final int expectedOccurrence = 4;
    private WebDriverWait wait;
    private final int waitTime = 10;
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability(MobileCapabilityType.UDID, "ce051605b5d4d82c03");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        //driver.setLogLevel(Level.INFO);
    }

    public void screenshot(String path_screenshot, String filename) throws IOException {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(path_screenshot + filename + ".jpg");
        FileUtils.copyFile(srcFile, targetFile);
    }


    @Test
    public void test01_verifyListSize() {
        List<AndroidElement> list = driver.findElements(By.xpath("//*[@id='list']/android.widget.TextView"));
        printContentRect();
        printActivityDetails();
        Assert.assertEquals(list.size(), expectedSize);
    }

    @Test
    public void test02_verifyEriIsInstalled() {
        Assert.assertTrue(driver.isAppInstalled("com.experitest.ExperiBank"));
    }

    @Test
    public void test03_verifyOrientation() {
        try {
            Assert.assertTrue(driver.getOrientation().equals(ScreenOrientation.LANDSCAPE));
        } catch (AssertionError e) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }

    @Test
    public void test04_verifyScreenshot() {
        try {
            notificationScreenShot();
        } catch (Exception e) {
            fail("Test failed!");
        }
    }

    @Test
    public void test05_verifyLocker() {

        if (!driver.isDeviceLocked()) {
            driver.lockDevice();
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        }
        if (driver.isDeviceLocked()) {
            driver.unlockDevice();
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        }
        try {
            Assert.assertFalse(driver.isDeviceLocked());
        } catch (AssertionError e) {
            fail("Test failed!");
        }
    }

    @Test
    public void test06_verifyListViewCount() {
        driver.startActivity(new Activity("com.example.android.apis", ".ApiDemos"));
        String source = driver.getPageSource();
        int occurrenceOfWord = source.split("ListView").length - 1;
        try {
            Assert.assertEquals(occurrenceOfWord, expectedOccurrence);
        } catch (AssertionError e) {
            System.out.println(e);
            fail("Test failed!");

        }
    }


    @Step("Printing TextView Rect")
    public void printContentRect() {
        AndroidElement content = driver.findElement(By.xpath("//*[@contentDescription='Content']"));
        System.out.println("Width: " + content.getRect().width + " Height: " + content.getRect().height);
        System.out.println("X: " + content.getRect().x + " Y:" + content.getRect().y);

    }

    @Step("Printing Activity Rect")
    public void notificationScreenShot() throws IOException {
        driver.openNotifications();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        screenshot("C:/Automation/TestAutomation/Appium/", "notification");
        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        screenshot("C:/Automation/TestAutomation/Appium/", "HomeScreen");
    }


    @Step("Printing Activity Rect")
    public void printActivityDetails() {
        System.out.println("Activity: " + driver.currentActivity());
        System.out.println("Time: " + driver.getDeviceTime());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
