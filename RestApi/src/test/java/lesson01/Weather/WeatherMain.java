package lesson01.Weather;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WeatherMain {
    private WebDriver driver;
    private WeatherPage weather;

    private final String city = "Jerusalem, IL";
    private final String key = "ad48510a9aed1ff96b51557d94bc5964";
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String expectedType = "application/json; charset=utf-8";
    private final int expectedStatus = 200;
    private final String expectedCountry="IL";
    private String json_humidity = "";
    private String humidity_Selenium = "";
    public static RequestSpecification httpRequest;
    public Response response;
    private JsonPath jp;

    @BeforeClass
    public void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://openweathermap.org/");
        weather = PageFactory.initElements(driver, WeatherPage.class);


        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + key);
        jp=response.jsonPath();

    }

    @Test
    public void test01_verifyCountry() {
        String country=jp.getString("sys.country").toString();
        Assert.assertEquals(country,expectedCountry);
    }

    @Test
    public void test02_verifyHumidityWithSelenium() {
        json_humidity=getActualHumidity();
        //Note:Test might fail as the weather keep changing.
        Assert.assertEquals(json_humidity,weather.getHumidity(city));
    }


    public String getActualHumidity() {
        return jp.get("main.humidity").toString();

    }



    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
