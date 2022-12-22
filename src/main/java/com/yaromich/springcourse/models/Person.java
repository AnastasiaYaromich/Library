package com.yaromich.springcourse.models;

import javax.validation.constraints.*;

/**
 * @author Анастасия Яромич
 */
public class Person {
    private int person_id;


    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 10, max = 50, message = "Имя должно содержать от 2 до 100 символов")
    private String full_name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1935")
    private int year_of_birth;

    public Person() {}

    public Person(int person_id, String full_name, int year_of_birth) {
        this.person_id = person_id;
        this.full_name = full_name;
        this.year_of_birth = year_of_birth;
    }

    public int getPerson_id() {return person_id;}

    public void setPerson_id(int person_id) {this.person_id = person_id;}

    public String getFull_name() {return full_name;}

    public void setFull_name(String full_name) {this.full_name = full_name;}

    public int getYear_of_birth() {return year_of_birth;}

    public void setYear_of_birth(int year_of_birth) {this.year_of_birth = year_of_birth;}
}
