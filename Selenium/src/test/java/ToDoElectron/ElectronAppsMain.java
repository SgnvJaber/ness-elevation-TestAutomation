package ToDoElectron;


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
import org.testng.asserts.SoftAssert;

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
       opt.setBinary("C:\\Users\\Sgnv\\AppData\\Local\\Programs\\todolist\\Todolist.exe");
       capabilities=new DesiredCapabilities();
       capabilities.setCapability("chromeOptions",opt);
       capabilities.setBrowserName("chrome");
       driver=new ChromeDriver(capabilities);
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       electron= PageFactory.initElements(driver, ElectronPage.class);
    }

    @Test
    public void test01_verifyCreateTaske() {
        SoftAssert soft=new SoftAssert();
        electron.createTask("Task3");
        electron.createTask("Task4");
        electron.createTask("Task5");
        soft.assertTrue(electron.containsTask("Task3"));
        soft.assertTrue(electron.containsTask("Task4"));
        soft.assertTrue(electron.containsTask("Task5"));
        soft.assertAll();

    }
    @Test
    public void test02_verifyTaskRename() {
        SoftAssert soft=new SoftAssert();
        electron.editTask("Task3Updated",2,driver);
        electron.editTask("Task4Updated",1,driver);
        soft.assertTrue(electron.containsTask("Task3Updated"));
        soft.assertTrue(electron.containsTask("Task4Updated"));
    }

    @Test
    public void test03_verifyDeleteTask() {
        SoftAssert soft=new SoftAssert();

        electron.deleteTask("Task5",driver);
        electron.deleteTask("Task4Updated",driver);
        electron.deleteTask("Task3Updated",driver);
        soft.assertFalse(electron.containsTask("Task4Updated"));
        soft.assertFalse(electron.containsTask("Task3Updated"));
        soft.assertFalse(electron.containsTask("Task5"));
    }
    @AfterClass
    public void closeBrowser() {
        //driver.quit();
    }

}
