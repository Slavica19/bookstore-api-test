package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static factory.InvalidAuthorFactory.*;
import static utils.ResponseUtils.assertResponseIsNotEmpty;

@Epic("Authors API")
@Test(groups = {"edge"})
public class AuthorsEdgeTest extends BaseTest{
    protected SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        softAssert = new SoftAssert();
    }
    @Feature("Get Author")
    @Story("Non-Existent ID")
    @Description("Verify that requesting an author with a non-existent ID returns HTTP 404")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void getAuthorById_nonExistentID(){
        Response response = authors.getAuthorsById(5000);
        softAssert.assertEquals(response.getStatusCode(), 404, "Expected HTTP status 404");
        assertResponseIsNotEmpty(response);
        softAssert.assertAll();
    }

    @Feature("Create Author")
    @Story("Invalid Data Types")
    @Description("Verify that creating an author with invalid data types returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createAuthor_invalidDataTypes() {
        Response response = authors.createAuthorRaw(invalidPayloadAuthor());
        assertBadRequest(response);

    }

    @Feature("Create Author")
    @Story("Empty Payload")
    @Description("Verify that creating an author with an empty request body returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createAuthors_emptyPayload() {
        Response response = authors.createAuthorRaw(emptyBodyAuthor());
        assertBadRequest(response);

    }

    @Feature("Create Author")
    @Story("Empty Required Fields")
    @Description("Verify that creating an author with empty required fields returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createAuthors_emptyFields() {
        Response response = authors.createAuthorRaw(emptyPayloadFieldsAuthor());
        assertBadRequest(response);

    }

    @Feature("Update Author")
    @Story("Invalid Data Types")
    @Description("Verify that updating an author with invalid data types returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateAuthor_invalidDataTypes() {
        String updatedAuthor = invalidPayloadAuthor();
        Response response = authors.updateAuthorRaw(1, updatedAuthor);
        assertBadRequest(response);
    }

    @Feature("Update Author")
    @Story("Empty Payload")
    @Description("Verify that updating an author with an empty payload returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateAuthor_emptyPayload() {
        String updatedAuthor= emptyBodyAuthor();
        Response response = authors.updateAuthorRaw(1, updatedAuthor);
        assertBadRequest(response);
    }

    @Feature("Update Author")
    @Story("Empty Required Fields")
    @Description("Verify that updating an author with empty required fields returns HTTP 400")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateAuthor_emptyFields() {
        String updatedAuthor = emptyPayloadFieldsAuthor();
        Response response = authors.updateAuthorRaw(1, updatedAuthor);
        assertBadRequest(response);
    }

    @Feature("Delete Author")
    @Story("Invalid Path Parameter")
    @Description("Verify that deleting an author with an invalid path parameter returns HTTP 400")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void deleteAuthor_invalidPathParameter() {
        Response response = authors.deleteAuthorByObjectId(" ");
        assertBadRequest(response);
    }
}
