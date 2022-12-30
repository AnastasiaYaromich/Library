package com.yaromich.springcourse.services;

import com.yaromich.springcourse.models.Book;
import com.yaromich.springcourse.models.Person;
import com.yaromich.springcourse.repositories.BooksRepository;
import com.yaromich.springcourse.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findWithSort(boolean sortByYear) {
        if(sortByYear) {
            return booksRepository.findAll(Sort.by("year"));
        } else
        return booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if(sortByYear) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        } else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    public List<Book> searchByTitle(String search) {
        return booksRepository.findByTitleStartingWith(search);
    }

    public Person findBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getPerson).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBEUpdated = booksRepository.findById(id).get();

        updatedBook.setId(id);
        updatedBook.setPerson(bookToBEUpdated.getPerson());

        booksRepository.save(updatedBook);
    }

    @Transactional
    public void assign(int id, Person person) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(person);
            book.setAssignAt(new Date());
        });
    }

    @Transactional
    public void release(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);

        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(null);
            book.setAssignAt(null);
        });
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
