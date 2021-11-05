package lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssertsAndVerifications02 {
    private WebDriver driver;
    private WebElement calculate_btn;
    private WebElement hidden_div;
    private final String expectedValue = "Calculate BMI";
    private final String expectedTag = "input";
    private final int expectedXCoordinate = 439;
    private final int expectedYCoordinate = 265;
    private final int expectedWidth = 133;
    private final int expectedHeight = 35;

    private int width = 0;
    private int height = 0;
    private int xCoordinate = 0;
    private int yCoordinate = 0;


    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/bmi/");
    }

    @Test
    public void test01_Calculate() {
        calculate_btn = driver.findElement(By.xpath("//input[@id='calculate_data']"));
        width = calculate_btn.getSize().getWidth();
        height = calculate_btn.getSize().getHeight();
        xCoordinate = calculate_btn.getLocation().getX();
        yCoordinate = calculate_btn.getLocation().getY();


    }

    @Test
    public void test02_verify_size() {
        //  System.out.println("Size:");
        //  System.out.println("Width: " + width);
        //  System.out.println("Height: " + height);

        Assert.assertEquals(xCoordinate, expectedXCoordinate);
        Assert.assertEquals(yCoordinate, expectedYCoordinate);
    }

    @Test
    public void test02_verify_location() {
        // System.out.println("Location:");
        //System.out.println("X: " + xCoordinate);
        //System.out.println("Y: " + yCoordinate);


        Assert.assertEquals(width, expectedWidth);
        Assert.assertEquals(height, expectedHeight);
    }

    @Test
    public void test02_verify_CalculateEnabled() {
        Assert.assertTrue(calculate_btn.isEnabled());
    }

    @Test
    public void test03_verify_CalculateDisplayed() {
        Assert.assertTrue(calculate_btn.isDisplayed());
    }

    @Test
    public void test04_verify_CalculateNotSelected() {
        Assert.assertFalse(calculate_btn.isSelected());
    }

    @Test
    public void test05_verify_CalculateTagType() {
        //System.out.println(calculate_btn.getTagName());
        Assert.assertEquals(calculate_btn.getTagName(), expectedTag);


    }

    @Test
    public void test06_verify_CalculateTagValue() {
        Assert.assertEquals(calculate_btn.getAttribute("value"), expectedValue);
    }

    @Test
    public void test07_verify_hiddenDiv() {
        hidden_div = driver.findElement(By.xpath("//div[@id='new_input']"));
        Assert.assertFalse(hidden_div.isDisplayed());
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
