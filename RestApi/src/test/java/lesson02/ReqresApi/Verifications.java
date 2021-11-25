package lesson02.ReqresApi;

import org.testng.Assert;

public class Verifications {

    public static void verifyResponse(int actual, int expected) {
        Assert.assertEquals(actual, expected);
    }

}
