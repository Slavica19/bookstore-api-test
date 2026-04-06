package assertions;

import io.restassured.response.Response;
import model.Author;
import model.Book;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static utils.ResponseUtils.assertResponseIsNotEmpty;

public class AuthorAssertions {

    public static void assertValidAuthor(Author author, SoftAssert softAssert) {
        softAssert.assertNotNull(author, "Book should not be null");
        softAssert.assertTrue(author.getId() >= 0, "ID should be valid");
        softAssert.assertTrue(author.getIdBook() >=0, "Title should not be null");
        softAssert.assertNotNull(author.getFirstName(), "Excerpt should not be null");
        softAssert.assertNotNull(author.getClass(), "PublishDate should not be null");
    }


}
