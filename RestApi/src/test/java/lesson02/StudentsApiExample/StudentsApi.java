package lesson02.StudentsApiExample;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentsApi {
    String baseURL = "http://localhost:9000";
    public static RequestSpecification request;
    public static Response response;
    private List<String> courses = new ArrayList<String>();

    @BeforeClass
    public void start() {
        RestAssured.baseURI = baseURL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    @Test
    public void test01_noCoursePOST() {
        JSONObject params = new JSONObject();
        params.put("firstName", "Yoni");
        params.put("lastName", "Flenner");
        params.put("email", "yoni@atidcollege.co.il");
        params.put("programme", "Some Enginnering");
        request.body(params.toJSONString());
        response = request.post("/student");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void test02_withCoursePOST() {

        courses.add("Selenium");
        courses.add("Appium");
        courses.add("Rest API");

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", "Saed");
        requestParams.put("lastName", "Jaber");
        requestParams.put("email", "sgnvunleashed2@gmail.com");
        requestParams.put("programme", "QA");
        requestParams.put("courses", courses);

        request.body(requestParams.toJSONString());
        response = request.post("/student");

        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201);

    }


    @Test
    public void test02_noCoursePUT() {
        JSONObject params = new JSONObject();
        params.put("firstName", "Yoni");
        params.put("lastName", "Flenner");
        params.put("email", "yoni@atidcollege.co.il");
        params.put("programme", "Physics");
        request.body(params.toJSONString());
        response = request.put("/student/101");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void test03_DELETE() {
        response = request.delete("/student/102");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 204);
    }

}
