package lesson02.ReqresApi;

import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class ApiMethods extends Base {

    protected static Response getListOfUsersByPage(String page) {
        response = request.get("/api/users?page=" + page);
        return response;
    }

    protected static Response getUserByID(String id) {
        response = request.get("api/users/" + id);
        return response;
    }

    protected static Response createUser(String name, String job) {
        params = new JSONObject();
        params.put("name", name);
        params.put("job", job);
        request.body(params.toJSONString());
        response = request.post("/api/users");
        return response;
    }


    protected static Response updateUser(String name, String job, String id) {
        params = new JSONObject();
        params.put("name", name);
        params.put("job", job);
        request.body(params.toJSONString());
        response = request.put("/api/users/" + id);
        return response;
    }

    protected static  Response deleteUser(String id) {
        response = request.delete("/api/users/" + id);
        return response;
    }


}

