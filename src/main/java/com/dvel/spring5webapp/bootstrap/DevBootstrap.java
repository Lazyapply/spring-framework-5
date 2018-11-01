package com.dvel.spring5webapp.bootstrap;

import com.dvel.spring5webapp.model.Author;
import com.dvel.spring5webapp.model.Book;
import com.dvel.spring5webapp.model.Publisher;
import com.dvel.spring5webapp.repositories.AuthorRepository;
import com.dvel.spring5webapp.repositories.BookRepository;
import com.dvel.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;

    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        Publisher harperPublisher = new Publisher();
        harperPublisher.setName("Harper Collins");
        publisherRepository.save(harperPublisher);

        // Eric
        Author eric = new Author("Eric", "Evans");
        Book book = new Book("Domain Driven Design", "1234", harperPublisher);
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book);

        // Rob
        Author rob = new Author("Rob", "Johnson");
        Book book2 = new Book("J2EE Development without EJB", "23444", harperPublisher);
        rob.getBooks().add(book2);

        authorRepository.save(rob);
        bookRepository.save(book2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
