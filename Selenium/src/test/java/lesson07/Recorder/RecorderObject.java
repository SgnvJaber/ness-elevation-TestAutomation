package lesson07.Recorder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
@Listeners(AutomationListenersWithRecorder.class)
public class RecorderObject {
    private WebDriver driver;
    private WebDriverWait wait;
    private final int waitTime = 10;
    private final String expectedRenderedText = "My Rendered Element After Fact!";
    private final String expectedRemovedText = "My Rendered Element After Fact!";

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
        wait = new WebDriverWait(driver, waitTime);

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        try {
            MonteScreenRecorder.startRecord(method.getName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test01_verifyStartRendererButton() {
        WebElement button = driver.findElement(By.xpath("//button[@id='rendered']"));
        button.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='finish2']")));
        WebElement div = driver.findElement(By.xpath("//div[@id='finish2']"));
        String actualText = div.findElement(By.tagName("h4")).getText();
        System.out.println(actualText);
        Assert.assertEquals(actualText, expectedRenderedText);
    }

    @Test
    public void test02_verifyStartHiddenButton() throws InterruptedException {
        WebElement button = driver.findElement(By.xpath("//button[@id='hidden']"));
        button.click();
        Thread.sleep(waitTime * 1000);
        WebElement div = driver.findElement(By.xpath("//div[@id='loading1']"));
        //Failing test on purpose to capture it with screen recorder.
        Assert.assertTrue(!div.isEnabled());
    }

    @Test
    public void test03_verifyRemoveButton() {
        WebElement button = driver.findElement(By.xpath("//button[text()='Remove']"));
        button.click();
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        WebElement text = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(text.isDisplayed());

    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
