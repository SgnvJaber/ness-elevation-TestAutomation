package lesson02.GetExample;//import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetResponse02 {

    private final String city="Jerusalem,IL";
    private final String key="ad48510a9aed1ff96b51557d94bc5964";
    private final String url="http://api.openweathermap.org/data/2.5/weather";
    private final String expectedType="json";
    private final int expectedStatus=200;
    public static RequestSpecification httpRequest;
    public Response response;
    @BeforeClass
    public void start()
    {
        RestAssured.baseURI=url;
        httpRequest=RestAssured.given();
        response=httpRequest.get("?units=metric&q="+city+"&appid="+key);
    }
    @Test
    public void test01_verifyResponseType()
    {
        printResponse();
        Assert.assertTrue(response.getContentType().contains(expectedType));
    }
    @Test
    public void test02_verifyResponseStatus()
    {
        printResponse();
        Assert.assertEquals(response.statusCode(),expectedStatus);
    }
   // @Step("Print content")
    public void printResponse()
    {
        System.out.println("Response:\n"+ response.prettyPrint());
        System.out.println("Response Status:"+response.statusCode());
        System.out.println("Response Time: "+response.getTime());
    }

}
