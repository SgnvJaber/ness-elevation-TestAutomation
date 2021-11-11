package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class ToDo {

    private WebDriver driver;
    private Actions action;

    @Attachment
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/react/#/");
        action = new Actions(driver);
    }

    @Test
    public void test01_verifyCreateTask() {
        try {
            createTask("SaedToDo1");
            createTask("SaedToDo2");
            createTask("SaedToDo3");
            renameTask("SaedToDo1", "SaedEditedToDo");
            renameTask("SaedToDo2", "SaedEditedToDo2");
            renameTask("SaedToDo3", "SaedEditedToDo3");
            deleteTask("SaedEditedToDo");
            markTask("SaedEditedToDo2");
            markTask("SaedEditedToDo3");
            filter("All");
            filter("Active");
            filter("Completed");
            removeAllCompleted();
        } catch (
                Exception e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        } catch (AssertionError e) {
            saveScreenshot(driver);
            fail("Assert Failed,see details: " + e);
        }
    }


    @Step("Create Task")
    public void createTask(String task) {
        WebElement input = driver.findElement(By.cssSelector("input[class='new-todo']"));
        input.sendKeys(task);
        action.click(input).sendKeys(Keys.ENTER).build().perform();
    }

    @Step("Rename Task")
    public void renameTask(String old_name, String new_name) {
        WebElement input = driver.findElement(By.xpath("//label[text()='" + old_name + "']"));
        action.click(input).doubleClick().sendKeys(new_name).sendKeys(Keys.ENTER).build().perform();
    }

    @Step("Delete Task")
    public void deleteTask(String task) {
        WebElement label = driver.findElement(By.xpath("//label[text()='" + task + "']"));
        action.moveToElement(label).click().doubleClick();
        action.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.ENTER);
        action.build().perform();
    }

    @Step("Mark Task As Completed")
    public void markTask(String task) {
        //Getting the parent of the label text
        WebElement div = driver.findElement(By.xpath("//label[text()='" + task + "']/.."));
        //In order to find the toggle button corresponding the actual task.
        WebElement toggle = div.findElement(By.cssSelector("input[class='toggle']"));
        action.click(toggle);
        action.build().perform();
    }

    @Step("Filter Task")
    public void filter(String type) {
        if (type.equals("All")) {
            driver.findElement(By.partialLinkText("All")).click();
        } else if (type.equals("Active")) {
            driver.findElement(By.partialLinkText("Active")).click();
        } else {
            driver.findElement(By.partialLinkText("Completed")).click();

        }
    }

    @Step("Delete All Completed Missions")
    public void removeAllCompleted() {
        driver.findElement(By.cssSelector("button[class='clear-completed']")).click();

    }

    @AfterClass
    public void closeBrowser() {
        //driver.quit();
    }


}
