package factory;

import model.Book;

public class InvalidAuthorFactory {
    public static Book missingTitle() {
        Book book = BookFactory.createDefaultBook();
        book.setTitle(null);
        return book;
    }



    public static String invalidPayloadAuthor(){
         String invalidPayloadAuthor = """
    {
        "id": "notAnInteger",
        "idBook": "string",
        "firstName": true,
        "lastName": ["invalid", "array"]
     }
    """;
         return invalidPayloadAuthor;
    }

    public static String emptyBodyAuthor() {
        return "";
    }
    public static String emptyPayloadFieldsAuthor() {
        String invalidPayloadAuthorEmptyFields = """
    {
         "id": ,
        "idBook": ,
        "firstName": ,
        "lastName": 
    }
    """;
        return  invalidPayloadAuthorEmptyFields;
    }
}
