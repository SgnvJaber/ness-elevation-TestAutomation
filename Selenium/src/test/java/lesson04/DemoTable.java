package lesson04;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DemoTable {
    private WebDriver driver;
    private WebElement thead, tbody, tfoot;
    private List<WebElement> table_body;
    private List<WebElement> table_foot;
    private final int expectedLength = 2;
    private final int heightIndex = 2;
    private void getTableElements() {
        thead = driver.findElement(By.xpath("//thead[1]"));
        tbody = driver.findElement(By.xpath("//tbody[1]"));
        tfoot = driver.findElement(By.xpath("//tfoot[1]/tr"));
        table_foot = tfoot.findElements(By.cssSelector("*"));
        table_body = tbody.findElements(By.tagName("tr"));
    }

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.techlistic.com/p/demo-selenium-practice.html");
        getTableElements();
    }


    @Test
    public void test01_verifyStructure() {
        System.out.println(table_body.size());
    }

    @Test
    public void test02_verifyNumberOfStructure() {
        WebElement total = driver.findElement(By.xpath("//td[@colspan='7']"));
        //System.out.println(total.getText());
        String[] numberOfBuildings = total.getText().split(" ");
        Assert.assertEquals(Integer.toString(table_body.size()), numberOfBuildings[0]);
    }

    @Test
    public void test03_printTable() {
        System.out.println(thead.getText());
        System.out.println(tbody.getText());
        System.out.println(tfoot.getText());
    }

    @Test
    public void test04_verifyStructureHeights() {
        List<String> actual_heights = new ArrayList<>();
        for (WebElement elem : table_body) {
            //pointing at elements at index 2(Height)
            actual_heights.add(elem.findElements(By.tagName("td")).get(heightIndex).getText());
        }
        List<String> expectedHeights = new ArrayList<>();
        expectedHeights.add("829m");
        expectedHeights.add("601m");
        expectedHeights.add("509m");
        expectedHeights.add("492m");
        Assert.assertEquals(actual_heights, expectedHeights);
    }

    @Test
    public void test05_verifyFooterSize() {
        Assert.assertEquals(table_foot.size(), expectedLength);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
