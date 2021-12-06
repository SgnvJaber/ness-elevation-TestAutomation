package lesson02.UnitConverter;

//package <set your test package>;
import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class UkMortgageCalcMain {
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
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.shivgadhia.android.ukMortgageCalc");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void testUntitled() {

        driver.findElement(By.xpath("//*[@id='etAmount']")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id='etTerm']")).sendKeys("15");
        driver.findElement(By.xpath("//*[@id='etRate']")).sendKeys("100");
        driver.findElement(By.xpath("//*[@text='Calculate']"));
      AndroidElement elem=driver.findElement(By.xpath("//*[@text='Calculate']"));
      elem.click();
        PointOption start_hour = PointOption.point(1000,1000);
        PointOption end_hour = PointOption.point(50,1000 );
        driver.findElement(By.xpath("//*[@id='btnSave']")).click();
        swipeScreen(start_hour,end_hour);
        List<AndroidElement> elements=driver.findElements(By.xpath("//*[@id='tvRef']"));
        System.out.println(elements.size());
        List<AndroidElement> deletebtns=driver.findElements(By.xpath("//*[@id='btnDel']"));
        System.out.println(deletebtns.size());
        deletebtns.get(0).click();
        driver.findElement(By.xpath("//*[@id='button1']")).click();
        System.out.println(driver.findElements(By.xpath("//*[@id='btnDel']")).size());



        Uninterruptibles.sleepUninterruptibly(20,TimeUnit.SECONDS);

    }

    public void swipeScreen(PointOption start, PointOption end) {
        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

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
        }
    }






    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}