package lesson02.ReqresApi;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.List;

public class Base {
    protected static String baseURL = "https://reqres.in/";
    protected static int expectedGetResponse = 200;
    protected static int expectedPostResponse = 201;
    protected static int expectedUpdateResponse = 200;
    protected static int expectedDeleteResponse = 204;
    protected static RequestSpecification request;
    protected static Response response;
    protected static JsonPath jp;
    protected static JSONObject params;
    protected static List<String> usersByPage;

}
