package lesson05;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class ReportingSystem {
    private WebDriver driver;
    private final String myWeight = "90";
    private final String myHeight = "163";
    private String result = "";
    private String means = "";
    private final String expectedResult = "34";
    private final String expectedMean = "That you have overweight.";


    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/bmi/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test01_fillWeight() {
        try {
            fillDetails(myWeight, myHeight);
            verifyResult(expectedResult);
            verifyMean(expectedMean+"fail him");

        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }

    @Step("Fill in details.")
    public void fillDetails(String weight, String height) {
        driver.findElement(By.xpath("//input[@name='weight']")).sendKeys(weight);
        driver.findElement(By.xpath("//input[@name='height']")).sendKeys(height);
        driver.findElement(By.xpath("//input[@id='calculate_data']")).click();
    }

    @Step("Verify Result")
    public void verifyResult(String expected_result) {

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='bmi_result']")).getAttribute("value"), expected_result);
    }

    @Step("Verify Meaning")
    public void verifyMean(String expected_mean) {

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='bmi_means']")).getAttribute("value"), expected_mean);
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
