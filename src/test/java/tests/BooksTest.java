    package tests;

    import io.qameta.allure.*;
    import io.qameta.allure.testng.Tag;
    import io.restassured.response.Response;
    import model.Book;
    import org.testng.Assert;
    import org.testng.annotations.BeforeMethod;
    import org.testng.annotations.Test;
    import org.testng.asserts.SoftAssert;

    import java.util.List;

    import static assertions.BookAssertions.assertValidBook;
    import static factory.BookFactory.createDefaultBook;

    @Epic("Books API")
    @Test(groups = {"happy"})
    public class BooksTest extends BaseTest {

        protected SoftAssert softAssert;

        @BeforeMethod
        public void setup() {
            softAssert = new SoftAssert();
        }


        @Tag("happy")
        @Feature("Get Books")
        @Story("Retrieve All Books")
        @Description("Verify that fetching all books returns a non-empty list with valid book data")
        @Severity(SeverityLevel.CRITICAL)
     @Test
        public void getBooks_shouldReturnNonEmptyList(){
              Response response = books.getBooks();
            // Assert HTTP status code
            assertResponse200(response);
            // Deserialize response into a list of Book objects
            List<Book> bookList = response.jsonPath().getList("", Book.class);
            // Assert the list is not empty
            softAssert.assertFalse(bookList.isEmpty(), "The retrieved book list should not be empty");
            // Validate each book's fields
              for (Book book : bookList) {
                  assertValidBook(book, softAssert);
              }
              // Report all collected assertion failures
            softAssert.assertAll();
        }
        @Tag("happy")
        @Feature("Get Book")
        @Story("Retrieve Book by Valid ID")
        @Description("Verify that fetching a book by valid ID returns correct and valid book data")
        @Severity(SeverityLevel.CRITICAL)
      @Test
        public void getBookById_shouldReturnValidBook(){
           Response response = books.getBookById(1);
            assertResponse200(response);
         // Deserialize response into a list of Book objects
           Book book = response.as(Book.class);
           assertValidBook(book, softAssert);
         // Report all collected assertion failures
           softAssert.assertAll();

        }
        @Tag("happy")
        @Feature("Create Book")
        @Story("Create New Book with Valid Data")
        @Description("Verify that creating a book returns a valid response and matches with the request")
        @Severity(SeverityLevel.BLOCKER)
      @Test
        public void createBooks_shouldReturnValidResponse() {
            Book requestBook = createDefaultBook();
            Response response = books.createBook(requestBook);
            assertResponse200(response);
            Book createdBook = response.as(Book.class);
            // Validate returned book matches request

            softAssert.assertNotNull(createdBook, "Response book should not be null");
            softAssert.assertTrue(createdBook.getId() >= 0, "ID should be generated or valid");

            softAssert.assertEquals(createdBook.getTitle(), requestBook.getTitle());
            softAssert.assertEquals(createdBook.getDescription(), requestBook.getDescription());
            softAssert.assertEquals(createdBook.getPageCount(), requestBook.getPageCount());
            softAssert.assertEquals(createdBook.getExcerpt(), requestBook.getExcerpt());
            softAssert.assertNotNull(createdBook.getPublishDate(), "PublishDate should not be null");
            softAssert.assertAll();
        }
        @Tag("happy")
        @Feature("Update Book")
        @Story("Update Existing Book")
        @Description("Verify that updating a book modifies and returns the correct updated data")
        @Severity(SeverityLevel.CRITICAL)
     @Test
        public void updateBook_shouldReturnUpdatedData() {
            Book updatedBook = createDefaultBook();
            updatedBook.setId(1);
            updatedBook.setTitle("Updated Title");
            updatedBook.setDescription("Updated Description");

            Response response = books.updateBook(1, updatedBook);
            assertResponse200(response);
            Book responseBook = response.as(Book.class);
            // Validate returned book matches request
            softAssert.assertEquals(responseBook.getId(), updatedBook.getId());
            softAssert.assertEquals(responseBook.getTitle(), updatedBook.getTitle());
            softAssert.assertEquals(responseBook.getDescription(), updatedBook.getDescription());
            softAssert.assertEquals(responseBook.getPageCount(), updatedBook.getPageCount());
            softAssert.assertEquals(responseBook.getExcerpt(), updatedBook.getExcerpt());
            softAssert.assertNotNull(responseBook.getPublishDate(), "PublishDate should not be null");

            softAssert.assertAll();

    }
        @Tag("happy")
        @Feature("Delete Book")
        @Story("Delete Book with Valid ID")
        @Description("Verify that deleting a book with a valid ID returns HTTP 200 success response")
        @Severity(SeverityLevel.CRITICAL)
        @Test
        public void deleteBook_shouldReturnSuccess() {
            Response response = books.deleteBookById(1);
            Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP 200");
        }

    }
