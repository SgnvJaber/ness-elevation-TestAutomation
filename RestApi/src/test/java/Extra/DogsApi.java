package Extra;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class DogsApi {
    String baseURL = "https://dog.ceo/api";
    public static RequestSpecification request;
    public static Response response;
    protected static JsonPath jp;
    @BeforeClass
    public void start() {
        RestAssured.baseURI = baseURL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }
    @Test
    public void test01_GET() throws IOException {
        response=request.get("/breeds/image/random");
        jp=response.jsonPath();
        String url= jp.get("message").toString();
        SaveImageFromUrl.saveImage(url,"dog");
        Assert.assertEquals(response.statusCode(),200);
    }

}
