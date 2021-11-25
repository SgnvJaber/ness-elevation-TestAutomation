package lesson02.ReqresApi;


import org.testng.annotations.Test;

public class MainClass extends Methods {

    @Test
    public void test01_GET() {
       Verifications.verifyResponse(printUserByID("2"),expectedGetResponse);
    }
    @Test
    public void test02_POST() {
        Verifications.verifyResponse(addUser("Saed","Qa"),expectedPostResponse);
    }
    @Test
    public void test03_UPDATE() {
        Verifications.verifyResponse(UpdateUserDetails("Saed","Qa","3"),expectedUpdateResponse);
    }
    @Test
    public void test04_DELETE() {
        Verifications.verifyResponse(removeUser("2"),expectedDeleteResponse);
    }

}
