package lesson02.ReqresApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class Methods extends ApiMethods {

    @BeforeClass
    public void start() {
        RestAssured.baseURI = baseURL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    protected static int printListOfUsersByPage(String page) {
        response=getListOfUsersByPage(page);
        jp = response.jsonPath();
        usersByPage = jp.getList("data.email");
        for (String email : usersByPage) {
            System.out.println(email);
        }
        return response.statusCode();
    }

    protected static int printUserByID(String id) {
        response=getUserByID(id);
        jp = response.jsonPath();
        System.out.println("USER:");
        System.out.println("email: " + jp.get("data.email"));
        System.out.println("Username: " + jp.get("data.first_name") + " " + jp.get("data.last_name"));
        return response.statusCode();
    }

    protected static int UpdateUserDetails(String name, String job, String id) {
        return updateUser(name, job, id).statusCode();
    }

    protected static int addUser(String name, String job) {
        return createUser(name, job).statusCode();
    }


    protected static int removeUser(String id) {
        return deleteUser(id).statusCode();
    }


}
