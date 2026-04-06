package factory;

import model.Author;
import model.Book;

import java.time.Instant;

public class AuthorFactory {

    public static Author createDefaultAuthor() {
        Author author = new Author();
        author.setId(20);
        author.setIdBook(200);
        author.setFirstName("Name");
        author.setLastName("Surname");
        return author;
    }



}
