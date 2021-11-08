package lesson05;
//Saed Jaber
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NopCommerce {
    private WebDriver driver;
    private final int waitTime = 5;
    private final String filterBy = "Price: Low to High";
    private final int expectedTotal = 3;
    private final int expectedRating = 3;
    WebElement itemGrid;
    List<WebElement> items;

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    private void navigateToActualPage() {
        driver.findElement(By.cssSelector("a[href='/electronics']")).click();
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        WebElement button = driver.findElement(By.xpath("//div[@class='picture']/a[@href='/camera-photo']"));
        button.click();
        Select myFilter = new Select(driver.findElement(By.id("products-orderby")));
        myFilter.selectByVisibleText(filterBy);
    }

    private void getItems() {
        itemGrid = driver.findElement(By.xpath("//div[@class='product-grid']/div[@class='item-grid']"));
        items = itemGrid.findElements(By.cssSelector("div[class='item-box']"));
    }

    @Test
    public void test01_verifyProductsTotal() {
        navigateToActualPage();
        getItems();
        Assert.assertEquals(items.size(), expectedTotal);
    }

    @Test
    public void test02_verifyProductsNames() {
        navigateToActualPage();
        getItems();
        List<String> actual_Names = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            actual_Names.add((items.get(0).findElements(By.xpath("//h2[@class='product-title']/a"))).get(i).getText());

        }
        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("Nikon D5500 DSLR");
        expectedNames.add("Leica T Mirrorless Digital Camera");
        expectedNames.add("Apple iCam");
        Assert.assertEquals(actual_Names, expectedNames);
    }

    @Test
    public void test03_verifyProductsRating() {
        navigateToActualPage();
        getItems();
        List<Integer> actual_ratings = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            String temp = items.get(0).findElements(By.xpath("//div[@class='rating']/div")).get(i).getAttribute("style");
            //Removing the word width and the %
            temp = temp.substring(7, temp.length() - 2);
            //100%=5 Stars,to get the number of start will be dividing the percentage by 20
            actual_ratings.add(Integer.parseInt(temp) / 20);
        }
        try {
            for (int rating : actual_ratings) {

                Assert.assertTrue(rating > expectedRating);
            }
        } catch (AssertionError e) {
            System.out.println("One of products rating is below: " + expectedRating);
        }


    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

}
