package com.yaromich.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Анастасия Яромич
 */
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название книги должно существовать")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 10 до 50 символов")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Автор книги должен быть существовать")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 10 до 50 символов длиной")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Год выпуска книги должен быть больше 1700")
    @Column(name = "year")
    private int year;

    @Column(name = "assign_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignAt;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Transient
    private boolean isBookOverdue;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}

    public Person getPerson() {return person;}

    public void setPerson(Person person) {this.person = person;}

    public Date getAssignAt() {return assignAt;}

    public void setAssignAt(Date assignAt) {this.assignAt = assignAt;}

    public Boolean getBookOverdue() {return isBookOverdue;}

    public void setBookOverdue(Boolean bookOverdue) {isBookOverdue = bookOverdue;}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
