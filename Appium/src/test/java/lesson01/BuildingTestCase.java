package lesson01;
//package <set your test package>;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class BuildingTestCase {

    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability(MobileCapabilityType.UDID, "ce051605b5d4d82c03");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
    }

    @Test
    public void test01() {
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@text='Login']")).click();
        driver.findElement(By.xpath("//*[@text='Make Payment']")).click();
        driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("0525663961");
        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Saed");
        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("300");
        driver.findElement(By.xpath("//*[@text='Select']")).click();
        driver.findElement(By.xpath("//*[@text='New Zealand']")).click();
        driver.findElement(By.xpath("//*[@text='Send Payment']")).click();
        driver.findElement(By.xpath("//*[@text='Yes']")).click();
        driver.findElement(By.xpath("//*[@text='Logout']")).click();
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@text='Login']")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}