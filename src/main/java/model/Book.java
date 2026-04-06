package model;

import lombok.Data;


@Data
public class Book {

    private int id;
    private String title;
    private String description ;
    private int pageCount;
    private String excerpt;
    private String publishDate;


}
