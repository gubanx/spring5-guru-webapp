package org.sample.spring5webapp.bootstrap;

import org.sample.spring5webapp.model.Author;
import org.sample.spring5webapp.model.Book;
import org.sample.spring5webapp.model.Publisher;
import org.sample.spring5webapp.repositories.AuthorRepository;
import org.sample.spring5webapp.repositories.BookRepository;
import org.sample.spring5webapp.repositories.PublisherRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Dondarrion");
        Publisher p1 = new Publisher("Harper Collins", "Doctor Lopez 102");
        publisherRepository.save(p1);
        Book ddd = new Book("Domain driven design", "1234", p1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);


        Author rod = new Author("Rod", "Johnson");
        Publisher p2 = new Publisher("Worx", "Carlos Saura 3");
        publisherRepository.save(p2);
        Book noEjb = new Book("No EJB", "2344", p2);
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEjb);

    }
}
