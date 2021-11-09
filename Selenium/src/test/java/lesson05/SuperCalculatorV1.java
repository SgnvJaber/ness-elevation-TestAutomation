package lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class SuperCalculatorV1 {
    WebDriver driver;
    public final String OPERATOR = "*";
    private final int WAIT_TIME = 2;
    private final int MAX = 3;

    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://juliemr.github.io/protractor-demo/");
    }

    @Test
    public void test01_calculate() {
        try {
            for (int i = 1; i <= MAX; i++) {
                for (int j = 1; j <= MAX; j++) {
                    fillDetails(Integer.toString(i), OPERATOR, Integer.toString(j));
                    Thread.sleep(WAIT_TIME * 1000);
                }
            }
            printResult();
        } catch (Exception e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }
    @Step("Fill in details.")
    public void fillDetails(String first_value, String myOperator, String second_value) {
        Select operation = new Select(driver.findElement(By.xpath("//select[@ng-model='operator']")));
        operation.selectByVisibleText(myOperator);
        driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys(first_value);
        driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys(second_value);
        driver.findElement(By.xpath("//button[@id='gobutton']")).click();
    }

    @Step("Print Results.")
    public void printResult() {
        List<WebElement> td = driver.findElements(By.xpath("//td[@class='ng-binding']"));
        for (int i = 1; i < td.size(); i += 2) {
            System.out.println(td.get(i).getText());
        }
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
