package Extra;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CatsApi {
    String baseURL = "https://api.thecatapi.com/";
    public static RequestSpecification request;
    public static Response response;
    protected static JsonPath jp;
    @BeforeClass
    public void start() {
        RestAssured.baseURI = baseURL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("x-api-key","6fee24f3-fe26-443c-8b48-06d2a12a5d7b");
    }
    @Test
    public void test01_GET() throws IOException {
        response=request.get("v1/images/search");
        jp=response.jsonPath();
        String url= jp.getList("url").get(0).toString();
        SaveImageFromUrl.saveImage(url,"cat");
        Assert.assertEquals(response.statusCode(),200);
    }

}
