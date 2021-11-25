package lesson02.GetExample;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetResponse01 {

    private final String city="Jerusalem,IL";
    private final String key="ad48510a9aed1ff96b51557d94bc5964";
    private final String url="http://api.openweathermap.org/data/2.5/weather";
    public static RequestSpecification httpRequest;
    public Response response;
    @BeforeClass
    public void start()
    {
        RestAssured.baseURI=url;
        httpRequest=RestAssured.given();
    }
    @Test
    public void test01_OpenWeatherMap()
    {
        response=httpRequest.get("?units=metric&q="+city+"&appid="+key);
        response.prettyPrint();

    }


}
