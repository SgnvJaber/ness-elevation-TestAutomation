package ToDoElectron;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElectronPage {

    Actions action ;

    @FindBy(xpath = "//input[@class='input_b5pqF']")
    private WebElement txt_input;

    @FindBy(xpath = "//div[@class='textWrapper_X9gil']/label[@class='label_5i8SP']")
    private List<WebElement> tasks;

    @FindBy(xpath = "//*[name()='svg' and @class='destroy_19w1q']")
    private List<WebElement> deleteButtons;



    public void createTask(String task) {
        txt_input.sendKeys(task);
        txt_input.sendKeys(Keys.ENTER);
    }

    public boolean containsTask(String task) {
        for (WebElement elem : tasks) {
            if (elem.getText().equals(task)) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(String task) {
        for (int i=0;i<tasks.size();i++) {
            if (tasks.get(i).getText().equals(task)) {
                return i;
            }
        }
        return -1;
    }





    public void editTask(String name,int index, WebDriver driver) {
       action= new Actions(driver);
       action.click(tasks.get(index)).doubleClick().doubleClick();
       action.sendKeys(name).sendKeys(Keys.ENTER).build().perform();
       action.build().perform();

    }
    public void deleteTask(String task, WebDriver driver) {
        action= new Actions(driver);
        int index=getIndex(task);
        action.moveToElement(tasks.get(index)).build().perform();
        deleteButtons.get(index).click();
        //Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);



    }



}
