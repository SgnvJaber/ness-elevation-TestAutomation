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

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-12']/h4")
    private WebElement result_title;

    @FindBy(how = How.XPATH, using = "//table[@id='no-more-tables']/tbody/tr/td[@data-title='Flight']")
    private List<WebElement> flights;


    @FindBy(how = How.XPATH, using = "//td/span/button[@class='form-control btn btn-default']")
    private List<WebElement> submitButtons;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-12']/alert/div")
    private WebElement alertBox;

    @FindBy(how = How.XPATH, using = "//ul/li[1]/a")
    private WebElement reservations;

    public void search() {
        search_btn.click();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }

    public String getSearchResultStatus() {
        return result_title.getText();
    }

    public boolean containsFlight(String flight) {
        for (WebElement fly : flights) {
            if (fly.getText().equals(flight)) {
                return true;
            }
        }
        return false;
    }

    public void printFlightsList() {
        for (WebElement flight : flights) {
            System.out.printf("Flight: " + flight.getText());
        }
    }

    public void bookFlight(String flight) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getText().equals(flight)) {
                submitButtons.get(i).click();
                break;
            }
        }
    }

    public String getSubmitResult() {
        return alertBox.getText();
    }


    public int numberOfFlights() {
        return flights.size();
    }

    public void selectDate(String day, String month, String year) {
        date.clear();
        date.sendKeys(year + "-" + month + "-" + day);
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

public void navigateToReservation()
{
    reservations.click();
    Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
}



}
