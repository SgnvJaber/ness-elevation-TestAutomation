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

import static org.testng.AssertJUnit.fail;

public class SuperCalculatorV2 {
    WebDriver driver;
    public final String OPERATOR = "*";
    private final String MIN = "5";
    private final String MAX = "5";
    private final int WAIT_TIME = 2;
    private String rand = "";
    private int sum = 0;

    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.random.org/");
    }

    @Test
    public void test01_calculate() {


        try {
            navigateToIframe();
            createRandomNumber(MIN, MAX);
            Thread.sleep(WAIT_TIME * 1000);
            storeRandomNumber();
            int temp = Integer.parseInt(rand) - 1;
            while (temp >= 0) {
                fillDetails(rand, OPERATOR, Integer.toString(temp));
                Thread.sleep(WAIT_TIME * 1000);
                temp--;
            }
            printResult();


        } catch (Exception e) {
            saveScreenshot(driver);
            fail("Test Failed,see details: " + e);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }

    }

    @Step("Enter iFrame")
    public void navigateToIframe() {
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        //Go inside the actual iframe
        //driver.get(iframe.getAttribute("src"));
    }


    @Step("Generate Random Number.")
    public void createRandomNumber(String first_value, String second_value) {
        WebElement firstInput = driver.findElement(By.cssSelector("input[id$='min'"));
        firstInput.clear();
        firstInput.sendKeys(first_value);
        WebElement secondInput = driver.findElement(By.cssSelector("input[id$='max'"));
        secondInput.clear();
        secondInput.sendKeys(second_value);
        driver.findElement(By.xpath("//input[@value='Generate']")).click();

    }

    @Step("Get Random Number Result.")
    public void storeRandomNumber() {
        rand = driver.findElement(By.xpath("//center/span[1]")).getText();
        System.out.println(rand);
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");

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
            String value = td.get(i).getText();
            sum += Integer.parseInt(value);
        }

        System.out.println("The Random Number is: " + rand);
        System.out.println("The result for Number: " + rand + " is: " + sum);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
