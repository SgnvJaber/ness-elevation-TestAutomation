package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class ActionsEx {
    private WebDriver driver;
    private Actions action;
    private WebDriverWait wait;
    private final int waitTime = 10;

    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_actions.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        action = new Actions(driver);
        wait = new WebDriverWait(driver, waitTime);
    }

    @Test
    public void test01_verifyDraggable() {
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        Actions action = new Actions(driver);
        action.dragAndDrop(draggable, droppable).build().perform();
        WebElement dropped = droppable.findElement(By.tagName("p"));
        System.out.println(dropped.getText());
        Assert.assertTrue(dropped.isDisplayed());
    }

    @Test
    public void test02_verifyClickAndHold() {
        List<WebElement> list = driver.findElements(By.xpath("//ol[@id='select_items']/li"));
        action.clickAndHold(list.get(1)).click(list.get((2)));
        action.build().perform();
        wait.until(ExpectedConditions.attributeToBe(list.get(1), "class", "ui-widget-content ui-selectee ui-selected"));
        wait.until(ExpectedConditions.attributeToBe(list.get(2), "class", "ui-widget-content ui-selectee ui-selected"));
        Assert.assertEquals(list.get(1).getAttribute("class"), "ui-widget-content ui-selectee ui-selected");
        Assert.assertEquals(list.get(2).getAttribute("class"), "ui-widget-content ui-selectee ui-selected");
    }
    @Test
    public void test03_verifyDoubleClick() {
        WebElement click=driver.findElement(By.xpath("//p[@id='dbl_click']"));
        action.doubleClick(click).build().perform();
        WebElement paragraph=driver.findElement(By.xpath("//p[@id='demo']"));
        wait.until(ExpectedConditions.textToBePresentInElement(paragraph,"Hello World"));
        Assert.assertEquals(paragraph.getText(),"Hello World");
    }
    @Test
    public void test04_verifyMouseMovement() {
        WebElement position=driver.findElement(By.xpath("//span[@id='mouse_hover']"));
        action.moveToElement(position).build().perform();
        wait.until(ExpectedConditions.attributeToBe(position, "style", "background-color: rgb(255, 255, 0);"));
        Assert.assertEquals(position.getAttribute("style"),"background-color: rgb(255, 255, 0);");
    }
    @Test
    public void test05_verifyScrolling() {
        WebElement paragraph=driver.findElement(By.xpath("//p[@id='scrolled_element']"));
       JavascriptExecutor js=(JavascriptExecutor)driver;
       js.executeScript("arguments[0].scrollIntoView(true);",paragraph);
       Assert.assertEquals(paragraph.getText(),"This Element is Shown When Scrolled");
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
