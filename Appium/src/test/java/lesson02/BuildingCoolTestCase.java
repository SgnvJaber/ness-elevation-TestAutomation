package lesson02;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BuildingCoolTestCase {

    private String reportDirectory = "C:/Automation/Reports/";
    private String reportFormat = "xml";
    private String testName = "appium.html";

    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "ce051605b5d4d82c03");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
    }


    @Test
    public void test01_verifyActivityTitle() {
        Assert.assertEquals(driver.currentActivity(), ".ApiDemos");
    }

    @Test
    public void test02_verifyAppNavigation() {
        driver.startActivity(new Activity("com.experitest.ExperiBank", ".LoginActivity"));
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@text='Login']")).getText(), "Login");
        //Fail Test:
        //Assert.assertEquals(driver.findElement(By.xpath("//*[@text='Login']")).getText(),"NotLogin");
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


}
