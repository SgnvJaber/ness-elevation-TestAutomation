package lesson09.ZapTest;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookPage {

    @FindBy(how = How.XPATH, using = "//*[@id='from']")
    private WebElement selectFromElement;

    @FindBy(how = How.XPATH, using = "//*[@id='to']")
    private WebElement selectToElement;

    @FindBy(how = How.XPATH, using = "//*[@id='dateTo']")
    private WebElement date;

    @FindBy(how = How.XPATH, using = "//*[@id='people']")
    private WebElement selectPassengersElement;


    @FindBy(how = How.XPATH, using = "//button[@class='form-control btn btn-default' and @type='submit']")
    private WebElement search_btn;


    public void search()
    {
        search_btn.click();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }


    public void selectDate(String day,String month,String year)
    {
        date.clear();
        date.sendKeys(day+"-"+month+"-"+year);
       // System.out.println(selected_day.getText());
    }



    public void fromCity(String city) {
        Select from = new Select(selectFromElement);
        from.selectByVisibleText(city);
    }
    public void toCity(String city) {
        Select to = new Select(selectToElement);
        to.selectByVisibleText(city);
    }



    public void passengersSelect(int number) {
        Select passengers = new Select(selectPassengersElement);
        passengers.selectByVisibleText(Integer.toString(number));
    }


}
