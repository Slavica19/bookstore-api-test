package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static factory.InvalidBookFactory.*;
import static factory.InvalidBookFactory.emptyPayloadFields;
import static utils.ResponseUtils.assertResponseIsNotEmpty;

@Epic("Books API")
@Test(groups = {"edge"})
public class BooksEdgeTest extends BaseTest {

    @Feature("Get Book")
    @Story("Non-Existent ID")
    @Description("Verify that requesting a book with a non-existent ID returns HTTP 404")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void getBookById_nonExistentID(){
        Response response = books.getBookById(500);
        Assert.assertEquals(response.getStatusCode(), 404, "Expected HTTP status 400");
        assertResponseIsNotEmpty(response);
    }

    @Feature("Create Book")
    @Story("Invalid Data Types")
    @Description("Verify that creating a book with invalid data types returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createBooks_invalidDataTypes() {
        Response response = books.createBook(invalidPayload());
        assertBadRequest(response);
    }

    @Feature("Create Book")
    @Story("Empty Payload")
    @Description("Verify that creating a book with an empty request body returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createBooks_emptyPayload() {
        Response response = books.createBook(emptyBody());
        assertBadRequest(response);
    }

    @Feature("Create Book")
    @Story("Empty Required Fields")
    @Description("Verify that creating a book with empty required fields returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createBooks_emptyFields() {
        Response response = books.createBook(emptyPayloadFields());
        assertBadRequest(response);
    }

    @Feature("Update Book")
    @Story("Invalid Data Types")
    @Description("Verify that updating a book with invalid data types returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateBook_invalidDataTypes() {
        String updatedBook = invalidPayload();
        Response response = books.updateBook(1, updatedBook);
        assertBadRequest(response);
    }

    @Feature("Update Book")
    @Story("Empty Payload")
    @Description("Verify that updating a book with an empty payload returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateBook_emptyPayload() {
        String updatedBook = emptyBody();
        Response response = books.updateBook(1, updatedBook);
        assertBadRequest(response);
    }

    @Feature("Update Book")
    @Story("Empty Required Fields")
    @Description("Verify that updating a book with empty required fields returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateBook_emptyFields() {
        String updatedBook = emptyPayloadFields();
        Response response = books.updateBook(1, updatedBook);
        assertBadRequest(response);
    }

    @Feature("Delete Book")
    @Story("Invalid Path Parameter")
    @Description("Verify that deleting a book with an invalid path parameter returns HTTP 400")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void deleteBook_invalidPathParameter() {
        Response response = books.deleteBookById(" ");
        Assert.assertEquals(response.getStatusCode(), 400, "Expected HTTP 400");
    }
}
