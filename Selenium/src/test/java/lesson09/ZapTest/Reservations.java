package lesson09.ZapTest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Reservations {

    @FindBy(how = How.XPATH, using = "//table[@id='no-more-tables']/tbody/tr/td[@data-title='Flight']")
    private List<WebElement> flights;

    @FindBy(how = How.XPATH, using = "//td/button[@class='form-control btn btn-default']")
    private List<WebElement> cancelButtons;

    @FindBy(how = How.XPATH, using = "//div[@class='col-md-12']/h3")
    private WebElement alertBox;

    public String getBox() {
        return alertBox.getText();
    }

    public void cancelFlight(String flight) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getText().equals(flight)) {
                cancelButtons.get(i).click();
                break;
            }
        }
    }

    public boolean containsFlight(String flight) {
        for (WebElement fly : flights) {
            if (fly.getText().equals(flight)) {
                return true;
            }
        }
        return false;
    }




}
