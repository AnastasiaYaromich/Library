package com.yaromich.springcourse.services;

import com.yaromich.springcourse.models.Book;
import com.yaromich.springcourse.models.Person;
import com.yaromich.springcourse.repositories.BooksRepository;
import com.yaromich.springcourse.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Person> findByName(String name) {
        return peopleRepository.findByName(name);
    }

    public List<Book> findBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        Integer currentDay = Integer.parseInt(simpleDateFormat.format(new Date()));

        if(person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            person.get().getBooks().forEach(book -> {

                if(book.getAssignAt() != null) {
                    Integer assignDay = Integer.parseInt(simpleDateFormat.format(book.getAssignAt()));

                    if(currentDay - assignDay > 10) {
                        book.setBookOverdue(true);
                    }
                }
            });
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
