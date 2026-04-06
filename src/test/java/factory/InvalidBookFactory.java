package factory;

import model.Book;

public class InvalidBookFactory {
    public static Book missingTitle() {
        Book book = BookFactory.createDefaultBook();
        book.setTitle(null);
        return book;
    }

    public static Book negativePageCount() {
        Book book = BookFactory.createDefaultBook();
        book.setPageCount(-1);
        return book;
    }

    public static Book emptyDescription() {
        Book book = BookFactory.createDefaultBook();
        book.setDescription("");
        return book;
    }

    public static String invalidPayload(){
         String invalidPayload = """
    {
        "id": "notAnInteger",
        "title": 12345,
        "description": true,
        "pageCount": "one hundred",
        "excerpt": ["invalid", "array"],
        "publishDate": 999999
    }
    """;
         return invalidPayload;
    }

    public static String emptyBody() {
        return "";
    }
    public static String emptyPayloadFields() {
        String invalidPayloadEmptyFields = """
    {
        "id": ,
        "title": ,
        "description":,
        "pageCount": ,
        "excerpt": ,
        "publishDate": 
    }
    """;
        return  invalidPayloadEmptyFields;
    }
}
