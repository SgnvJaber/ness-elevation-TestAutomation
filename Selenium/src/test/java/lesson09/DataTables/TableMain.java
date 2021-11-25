package lesson09.DataTables;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson07.Recorder.MonteScreenRecorder;
import lesson07.TableReader.AutomationListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.List;

@Listeners(AutomationListenersWithRecorder.class)
public class TableMain {
    private WebDriver driver;
    private TablePage table;
    private final int expectedRowSize = 11;
    private final int expectedColSize = 6;


    private final String url = "https://datatables.net/";

    @BeforeClass
    public void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        table = PageFactory.initElements(driver, TablePage.class);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        try {
            MonteScreenRecorder.startRecord(method.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * תרגיל בונוס לממש עם PO
    בסעיף 4, להשתמש עם SoftAssert
    ולוודא שכל האנשים שגרים בניו-יורק (בכל הדפים) מעל גיל 22
    *
    * */

    @Test
    public void test01_verifyTableRowSize() {
        Assert.assertEquals(table.getRowSize(), expectedRowSize);
    }

    @Test
    public void test02_verifyTableColSize() {
        Assert.assertEquals(table.getColSize(), expectedColSize);

    }

    @Test
    public void test03_verifyData() {
        SoftAssert soft = new SoftAssert();
        //Flag "print"- print the list,"" don't print the list
        table.getPeopleAllPages("New York", 0, "print");
        table.reset();
        System.out.println(table.getSoftwareAverageSalariesFromAllPages("Software Engineer"));
        table.reset();
        List<Integer> list = table.getPeopleAllPages("New York", 0, "");
        for (int age : list) {
            soft.assertTrue(age > 22);
        }
        soft.assertAll();

    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
