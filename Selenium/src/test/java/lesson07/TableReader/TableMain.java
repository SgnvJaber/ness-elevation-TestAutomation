package lesson07.TableReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson07.PageObjectPattern.ClickPage;
import lesson07.PageObjectPattern.FormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.python.antlr.ast.Str;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Listeners(AutomationListeners.class)
public class TableMain {
    private WebDriver driver;
    private TablePage table;

    private final int rows_size = 7;
    private final int cols_size = 3;
    private final int expectedCountrySize = 4;
    private final String occupation = "QA";
    private final String age = "25";
    private final String location = "Abu Ghosh";
    private List<String> countries;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        table = PageFactory.initElements(driver, TablePage.class);
        table.writeToCSV();
    }

    @Test
    public void test01_verifyTableRows() {
        Assert.assertEquals(table.getRowsSize(), rows_size);
    }

    @Test
    public void test02_verifyTableCols() {
        Assert.assertEquals(table.getColsSize(), cols_size);
    }

    @Test
    public void test03_verifyEuropeCountries() {
        table.printEuropeCountries();
        Assert.assertEquals(table.getEuropeCountries().size(), expectedCountrySize);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
