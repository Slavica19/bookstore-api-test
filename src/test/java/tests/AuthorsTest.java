package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import model.Author;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static assertions.AuthorAssertions.assertValidAuthor;
import static factory.AuthorFactory.createDefaultAuthor;

@Epic("Authors API")
@Test(groups = {"happy"})
public class AuthorsTest extends BaseTest {

    protected SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        softAssert = new SoftAssert();
    }

    @Tag("happy")
    @Feature("Get Authors")
    @Story("Retrieve All Authors")
    @Description("Verify that fetching all authors returns a non-empty list with valid author data")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void getBooks_shouldReturnNonEmptyList(){
        Response response = authors.getAuthors();
        // Assert HTTP status code
        assertResponse200(response);
        // Deserialize response into a list of Book objects
        List<Author> authorList = response.jsonPath().getList("", Author.class);
        // Assert the list is not empty
        softAssert.assertFalse(authorList.isEmpty(), "The retrieved author list should not be empty");
        // Validate each book's fields
        for (Author author : authorList) {
            assertValidAuthor(author, softAssert);
        }
        // Report all collected assertion failures
        softAssert.assertAll();
    }
    @Tag("happy")
    @Feature("Get Author")
    @Story("Retrieve Author by Valid ID")
    @Description("Verify that fetching an author by valid ID returns correct and valid author data")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void getAuthorsById_shouldReturnValidAuthor(){
        Response response = authors.getAuthorsById(1);
        assertResponse200(response);
        // Deserialize response into a list of Book objects
        Author author = response.as(Author.class);
        assertValidAuthor(author, softAssert);
        // Report all collected assertion failures
        softAssert.assertAll();

    }
    @Tag("happy")
    @Feature("Create Author")
    @Story("Create New Author with Valid Data")
    @Description("Verify that creating an author returns a valid response and persisted data matches request")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void createAuthor_shouldReturnValidResponse() {
        Author requestAuthor = createDefaultAuthor();
        Response response = authors.createAuthor(requestAuthor);
        assertResponse200(response);
        Author createdAuthor = response.as(Author.class);
        // Validate returned book matches request
        softAssert.assertNotNull(createdAuthor, "Response book should not be null");
        softAssert.assertTrue(createdAuthor.getId() >= 0, "ID should be generated or valid");
        softAssert.assertEquals(createdAuthor.getIdBook(),requestAuthor.getIdBook());
        softAssert.assertEquals(createdAuthor.getFirstName(), requestAuthor.getFirstName());
        softAssert.assertEquals(createdAuthor.getLastName(), requestAuthor.getLastName());

        softAssert.assertAll();
    }
    @Tag("happy")
    @Feature("Update Author")
    @Story("Update Existing Author")
    @Description("Verify that updating an author modifies and returns the correct updated data")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void updateAuthor_shouldReturnUpdatedData() {
        Author updateAuthor = createDefaultAuthor();
        updateAuthor.setIdBook(1);
        updateAuthor.setFirstName("Updated FirstName");
        Response response = authors.updateAuthor(1, updateAuthor);
        assertResponse200(response);
        Author responseAuthor = response.as(Author.class);
        // Validate returned book matches request
        softAssert.assertEquals(responseAuthor.getId(), updateAuthor.getId());
        softAssert.assertEquals(responseAuthor.getIdBook(), updateAuthor.getIdBook());
        softAssert.assertEquals(responseAuthor.getFirstName(), updateAuthor.getFirstName());
        softAssert.assertEquals(responseAuthor.getLastName(), updateAuthor.getLastName());
        softAssert.assertAll();

    }
    @Tag("happy")
    @Feature("Delete Author")
    @Story("Delete Author with Valid ID")
    @Description("Verify that deleting an author with a valid ID returns HTTP 200 success response")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void deleteAuthor_shouldReturnSuccess() {
        Response response = authors.deleteAuthorById(1);
        Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP 200");
    }

}
