package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseUtils {
    public static void assertResponseIsNotEmpty(Response response) {
        String body = response.asString();
        Assert.assertFalse(body.isEmpty(), "Response body is empty!");
    }
}
