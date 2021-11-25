package lesson02.UnitConverter;

//package <set your test package>;
import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class UnitConverterMain {
    private String reportDirectory = "C:/Automation/Reports/";
    private String reportFormat = "xml";
    private String testName = "appium.html";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
         dc.setCapability(MobileCapabilityType.UDID, "ce051605b5d4d82c03");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "kr.sira.unit");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".Intro");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void testUntitled() {
        Uninterruptibles.sleepUninterruptibly(8, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@text='LIVING']")).click();
        driver.findElement(By.xpath("//*[@id='tab1_input']")).click();
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }
}