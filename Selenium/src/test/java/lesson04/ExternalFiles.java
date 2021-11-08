package lesson04;

import helpers.Reader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExternalFiles {
    private WebDriver driver;
    private String result = "";
    private String means = "";
    private Reader read=new Reader();
    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(read.getData("URL",0));
    }

    @Test
    public void test01_fillWeight() {
        WebElement weight_field = driver.findElement(By.xpath("//input[@name='weight']"));
        weight_field.sendKeys(read.getData("Weight",0));
    }

    @Test
    public void test02_fillHeight() {
        WebElement height_field = driver.findElement(By.xpath("//input[@name='height']"));
        height_field.sendKeys(read.getData("Height",0));
    }

    @Test
    public void test03_Calculate() {
        WebElement calculate_btn = driver.findElement(By.xpath("//input[@id='calculate_data']"));
        calculate_btn.click();
    }

    @Test
    public void test04_verifyResult() {
        WebElement bmi_result = driver.findElement(By.xpath("//input[@id='bmi_result']"));
        result = bmi_result.getAttribute("value");
        Assert.assertEquals(result, read.getData("ExpectedResult",0));
    }

    @Test
    public void test05_verifyMeans() {
        WebElement bmi_means = driver.findElement(By.xpath("//input[@id='bmi_means']"));
        means = bmi_means.getAttribute("value");
        Assert.assertEquals(means, read.getData("ExpectedMeans",0));
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
