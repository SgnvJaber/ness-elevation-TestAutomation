package lesson08.ElectronApps;



import lesson07.TableReader.AutomationListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

@Listeners(AutomationListeners.class)
public class ElectronAppsMain {
    private ElectronPage electron;
    private WebDriver driver;
    private ChromeOptions opt;
    private DesiredCapabilities capabilities;


    @BeforeClass
    public void startSession() {
       System.setProperty("webdriver.chrome.driver","C:\\Automation\\ElectronDriver\\electrondriver.exe");
       opt=new ChromeOptions();
       opt.setBinary("C:\\Automation\\ElectronApiDemos\\ElectronApiDemos.exe");
       capabilities=new DesiredCapabilities();
       capabilities.setCapability("chromeOptions",opt);
       capabilities.setBrowserName("chrome");
       driver=new ChromeDriver(capabilities);
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       electron= PageFactory.initElements(driver,ElectronPage.class);
    }

    @Test
    public void test01_verifyMenuSize() {
        electron.printMenu();
        Assert.assertEquals(electron.getMenuSize(),6);
    }



    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
