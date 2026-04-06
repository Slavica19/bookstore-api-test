package client;

import io.restassured.response.Response;
import model.Author;

import static io.restassured.RestAssured.given;

public class AuthorsClient {

    private static final String BASE_PATH = "/api/v1/Authors";

    public Response getAuthors() {
            return given()
                    .spec(utils.SpecBuilder.getRequestSpecification())
                    .when()
                    .get(BASE_PATH)
                    .then()
                    .extract()
                    .response();
    }

    public Response createAuthor(Author author) {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .body(author)
                .when()
                .post(BASE_PATH)
                .then()
                .extract().response();

    }
    public Response createAuthorRaw(Object author) {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .body(author)
                .when()
                .post(BASE_PATH)
                .then()
                .extract().response();

    }

    public Response getAuthorsById(int id) {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .pathParam("id",id)
                .when()
                .get(BASE_PATH+"/{id}")
                .then()
                .extract()
                .response();
    }

    public Response updateAuthor(int id, Author author) {
        return   given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .body(author)
                .pathParam("id",id)
                .when()
                .put(BASE_PATH+"/{id}")
                .then()
                .extract()
                .response();
    }
    public Response updateAuthorRaw(int id, Object author) {
        return   given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .body(author)
                .pathParam("id",id)
                .when()
                .put(BASE_PATH+"/{id}")
                .then()
                .extract()
                .response();
    }
    public Response deleteAuthorById(int id) {
        return given()
                .spec(utils.SpecBuilder.getRequestSpecification())
                .pathParam("id", id)
                .when()
                .delete(BASE_PATH+"/{id}")
                .then()
                .extract()
                .response();
    }
    public Response deleteAuthorByObjectId(Object id) {
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
