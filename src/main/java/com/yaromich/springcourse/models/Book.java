package com.yaromich.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Анастасия Яромич
 */
public class Book {
    private int book_id;
    private String person_id;

    @NotEmpty(message = "Название книги должно существовать")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 10 до 50 символов")
    private String name;

    @NotEmpty(message = "Автор книги должен быть существовать")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 10 до 50 символов длиной")
    private String author;

    @Min(value = 1500, message = "Год выпуска книги должен быть больше 1700")
    private int year;

    public Book(int book_id, String person_id, String name, String author, int year) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getBook_id() {return book_id;}

    public void setBook_id(int book_id) {this.book_id = book_id;}

    public String getPerson_id() {return person_id;}

    public void setPerson_id(String person_id) {this.person_id = person_id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}
}
