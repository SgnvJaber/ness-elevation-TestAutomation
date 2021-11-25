package lesson01.Weather;

import com.google.common.util.concurrent.Uninterruptibles;
//import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeatherPage {

    @FindBy(how = How.XPATH, using = "//input[@name='q']")
    private WebElement txt_search;
    @FindBy(how = How.XPATH, using = "//tbody/tr/td/b/a")
    private List<WebElement> results;
     @FindBy(how = How.XPATH, using = " //*[@id=\"weather-widget\"]/div[2]/div[1]/div[1]/div[2]/ul/li[3]")
      private WebElement txt_humidity;


  //  @Step("Get Humidity with Selenium")
    public String getHumidity(String city) {
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        txt_search.sendKeys(city);
        txt_search.sendKeys(Keys.ENTER);
        results.get(0).click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        String word=txt_humidity.getText();
        //Cutting "Humidity\n" and the "%" from the output
        // Way2-Lior's idea remove all non numbers:"[a-zA-Z:%]"
        String output=word.substring(("Humidity").length()+2,word.length()-1);
        return output;
    }


}
