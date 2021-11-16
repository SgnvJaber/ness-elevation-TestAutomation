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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class ToDo {

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
        driver.get("https://todomvc.com/examples/react/#/");
        action = new Actions(driver);
        wait = new WebDriverWait(driver, waitTime);

    }

    @Test
    public void test01_verifyCreateTask() {
        try {
            generateTasksForTesting();
            renameTasks();
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


    @Step("Generate Multiple Tasks")
    public void generateTasksForTesting() {
        createTask("SaedToDo1");
        createTask("SaedToDo2");
        createTask("SaedToDo3");
    }

    @Step("rename Generated Tasks")
    public void renameTasks() {
        renameTask("SaedToDo1", "SaedEditedToDo");
        renameTask("SaedToDo2", "SaedEditedToDo2");
        renameTask("SaedToDo3", "SaedEditedToDo3");
        renameTask("SaedToDo3232", "SaedEditedToDo3");
    }

    @Step("Create Task")
    public void createTask(String task) {
        WebElement input = driver.findElement(By.cssSelector("input[class='new-todo']"));
        input.sendKeys(task);
        action.click(input).sendKeys(Keys.ENTER).build().perform();
        WebElement actual_task = driver.findElement(By.xpath("//label[text()='" + task + "']"));
        assertEquals(actual_task.getText(), task);
    }

    @Step("Rename Task")
    public void renameTask(String old_name, String new_name) {
        if (driver.findElements(By.xpath("//label[text()='" + old_name + "']")).size() == 0) {
            System.out.println("Can't rename a task that doesn't exist!");
            return;
        } else {
            WebElement input = driver.findElement(By.xpath("//label[text()='" + old_name + "']"));
            action.click(input).doubleClick().sendKeys(new_name).sendKeys(Keys.ENTER).build().perform();
            WebElement actual_task = driver.findElement(By.xpath("//label[text()='" + new_name + "']"));
            assertEquals(actual_task.getText(), new_name);
        }
    }

    @Step("Delete Task")
    public void deleteTask(String task) {
        if (driver.findElements(By.xpath("//label[text()='" + task + "']")).size() == 0) {
            System.out.println("Task doesn't exist");
            return;
        } else {
            WebElement label = driver.findElement(By.xpath("//label[text()='" + task + "']"));
            action.moveToElement(label).click().doubleClick();
            action.sendKeys(Keys.BACK_SPACE).sendKeys(Keys.ENTER);
            action.build().perform();
            boolean isRemoved = driver.findElements(By.xpath("//label[text()='" + task + "']")).size() == 0;
            Assert.assertTrue(isRemoved);
        }

    }

    @Step("Mark Task As Completed")
    public void markTask(String task) {
        //Getting the parent of the label text
        WebElement div = driver.findElement(By.xpath("//label[text()='" + task + "']/.."));
        //In order to find the toggle button corresponding the actual task.
        WebElement toggle = div.findElement(By.cssSelector("input[class='toggle']"));
        action.click(toggle);
        action.build().perform();
        Assert.assertTrue(toggle.isSelected());
    }

    @Step("Filter Task")
    public void filter(String type) {
        WebElement selected_type = driver.findElement(By.partialLinkText(type));
        selected_type.click();
        wait.until(ExpectedConditions.attributeToBe(selected_type, "class", "selected"));
        assertEquals(selected_type.getAttribute("class"), "selected");
    }

    @Step("Delete All Completed Missions")
    public void removeAllCompleted() {
        driver.findElement(By.cssSelector("button[class='clear-completed']")).click();
        //if this element exists there are tasks inside the list,by verifying its size is zero,
        //we can verify that all tasks got removed.
        boolean status = driver.findElements(By.xpath("//span[@class='todo-count']/strong")).size() == 0;
        Assert.assertTrue(status);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }


}
