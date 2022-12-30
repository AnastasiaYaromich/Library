package com.yaromich.springcourse.models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Анастасия Яромич
 */
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 10, max = 50, message = "Имя должно содержать от 2 до 100 символов")
    @Column(name = "full_name")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1935")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person() {}

    public Person(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getYearOfBirth() {return yearOfBirth;}

    public void setYearOfBirth(int yearOfBirth) {this.yearOfBirth = yearOfBirth;}

    public List<Book> getBooks() {return books;}

    public void setBooks(List<Book> books) {this.books = books;}

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
