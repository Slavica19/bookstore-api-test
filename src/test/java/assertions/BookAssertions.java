package assertions;

import io.restassured.response.Response;
import model.Book;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static utils.ResponseUtils.assertResponseIsNotEmpty;

public class BookAssertions {

    public static void assertValidBook(Book book, SoftAssert softAssert) {
        softAssert.assertNotNull(book, "Book should not be null");
        softAssert.assertTrue(book.getId() >= 0, "ID should be valid");
        softAssert.assertNotNull(book.getTitle(), "Title should not be null");
        softAssert.assertNotNull(book.getDescription(), "Description should not be null");
        softAssert.assertTrue(book.getPageCount() > 0, "PageCount should be positive");
        softAssert.assertNotNull(book.getExcerpt(), "Excerpt should not be null");
        softAssert.assertNotNull(book.getPublishDate(), "PublishDate should not be null");
    }

    public static void assertBookEquals(Book actual, Book expected, SoftAssert soft) {
        soft.assertEquals(actual.getTitle(), expected.getTitle());
        soft.assertEquals(actual.getDescription(), expected.getDescription());
        soft.assertEquals(actual.getPageCount(), expected.getPageCount());
        soft.assertEquals(actual.getExcerpt(), expected.getExcerpt());
    }
}
