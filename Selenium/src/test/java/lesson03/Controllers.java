package lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Controllers {
    private WebDriver driver;
    private List<WebElement> list = new ArrayList<>();
    String first_name = "Saed";
    String last_name = "Jaber";
    String continent = "europe";
    String command = "Switch Commands";

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_controllers.html");
    }

    @Test
    public void test01_fillFirstName() {
        WebElement first_name_field = driver.findElement(By.xpath("//input[@name='firstname']"));
        first_name_field.sendKeys(first_name);
    }

    @Test
    public void test02_fillLastName() {
        WebElement last_name_field = driver.findElement(By.xpath("//input[@name='lastname']"));
        last_name_field.sendKeys(last_name);
    }

    @Test
    public void test03_fillDropDown() {
        Select myContinent = new Select(driver.findElement(By.id("continents")));
        myContinent.selectByValue(continent);
    }

    //Bonus Section:
    @Test
    public void test04_FillAutomationTool() {
        WebElement qTP = driver.findElement(By.xpath("//input[@id='tool-0']"));
        qTP.click();
        WebElement seleniumIDE = driver.findElement(By.xpath("//input[@id='tool-1']"));
        seleniumIDE.click();
        WebElement seleniumWebDriver = driver.findElement(By.xpath("//input[@id='tool-2']"));
        seleniumWebDriver.click();
    }

    @Test
    public void test05_FillProfession() {
        WebElement manualTester = driver.findElement(By.xpath("//input[@id='profession-0']"));
        manualTester.click();
        WebElement automationTester = driver.findElement(By.xpath("//input[@id='profession-1']"));
        automationTester.click();
    }

    @Test
    public void test06_FillSeleniumCommands() {
        Select myCommands = new Select(driver.findElement(By.id("selenium_commands")));
        myCommands.selectByVisibleText(command);
    }

    @Test
    public void test07_FillYearOfExperience() {
        WebElement radio = driver.findElement(By.xpath("//input[@id='exp-4']"));
        radio.click();
    }

    @Test
    public void test08_fileUpload() {
        WebElement browse = driver.findElement(By.xpath("//input[@id='photo']"));
        browse.sendKeys("C:/Automation/TestAutomation/Selenium/src/test/java/lesson03/naruto.jpg");
    }

    @Test
    public void test09_fillDatePicker() {
        WebElement dataPicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
        dataPicker.click();
        WebElement dataWidget = driver.findElement(By.xpath("//div[@id='ui-datepicker-div']"));
        List<WebElement> cells = dataWidget.findElements(By.tagName("td"));
        for (WebElement cell : cells) {
            if (cell.getText().equals("19")) {
                cell.click();
                break;
            }
        }
    }

    @Test
    public void test10_submitButton() {
        WebElement btn = driver.findElement(By.xpath("//button[@id='submit']"));
        btn.submit();
    }

    @Test
    public void test11_verifyUrl() {
        String url = driver.getCurrentUrl();
        if (url.contains(first_name) && url.contains(last_name) && url.contains(continent)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test failed!");
        }
    }

    @Test
    public void test12_getDataFromURL() {
        String url = driver.getCurrentUrl();
        String[] arr = url.split("%");
//    for(String word:arr)
//    {
//        System.out.println(word);
//    }
        //Yoni's idea.
        //The day is the last 2 chars from the first String
        String day = arr[0].substring(arr[0].length() - 2);
        //The month is the last 2 chars from the second String
        String month = arr[1].substring(arr[1].length() - 2);
        //The year start from index 2 and end at 6 from the third String.
        String year = arr[2].substring(2, 6);
        System.out.println(year + "-" + month + "-" + day);

    }

    @AfterClass
    public void closeBrowser() {
           driver.quit();
    }


}
