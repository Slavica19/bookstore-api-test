package factory;

import model.Book;

import java.time.Instant;

public class BookFactory {

    public static Book createDefaultBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setDescription("Test Description");
        book.setPageCount(100);
        book.setExcerpt("Test Excerpt");
        book.setPublishDate(Instant.now().toString());
        return book;
    }



}
