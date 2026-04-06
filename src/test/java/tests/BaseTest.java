package tests;

import client.AuthorsClient;
import client.BooksClient;
import io.restassured.response.Response;
import org.testng.Assert;

import static utils.ResponseUtils.assertResponseIsNotEmpty;

public class BaseTest {
    public  BooksClient books = new BooksClient();
    public AuthorsClient authors = new AuthorsClient();

    public void assertResponse200(Response response) {
        assertResponseIsNotEmpty(response);
        Assert.assertEquals(response.getStatusCode(), 200,"Expected HTTP status 200");
    }


    public static void assertBadRequest(Response response) {
        assertResponseIsNotEmpty(response);
        Assert.assertTrue(
                response.getStatusCode() == 400,
                "Expected client error but got: " + response.getStatusCode()
        );
    }

}
