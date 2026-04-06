package client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BooksClient {

    private static final String BASE_PATH = "/api/v1/Books";

    public Response getBooks() {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .when()
                .get(BASE_PATH)
                .then()
                .extract()
                .response();

    }
    public Response createBook(Object book) {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .body(book)
                .when()
                .post(BASE_PATH)
                .then()
                .extract().response();
    }

    public Response getBookById(int id) {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .pathParam("id",id)
                .when()
                .get(BASE_PATH+"/{id}")
                .then()
                .extract()
                .response();
    }

    public Response updateBook(int id,Object book) {
        return   given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .body(book)
                .pathParam("id",id)
                .when()
                .put(BASE_PATH+"/{id}")
                .then()
                .extract()
                .response();
    }

    public Response deleteBookById(Object id) {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .pathParam("id", id)
                .when()
                .delete(BASE_PATH+"/{id}")
                .then()
                .extract()
                .response();

    }

}
