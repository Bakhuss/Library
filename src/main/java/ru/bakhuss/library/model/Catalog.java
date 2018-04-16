package ru.bakhuss.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

/**
 * Список книг
 */
@Entity
public class Catalog {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    /**
     * Описание
     */
    @Column
    private String description;

    /**
     * Количество экземпляров книги в библиотеке
     */
    @Column(name = "total_count")
    private Integer totalCount;

    /**
     * Книга
     */
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Список читателей книги
     */
    @OneToMany(mappedBy = "catalog",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<LibraryCard> subscribers;


    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Set<LibraryCard> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<LibraryCard> subscribers) {
        this.subscribers = subscribers;
    }

    public void addSubscriber(LibraryCard subscriber) {
        getSubscribers().add(subscriber);
        subscriber.setCatalog(this);
    }

    public void removeSubscriber(LibraryCard subscriber) {
        getSubscribers().remove(subscriber);
        subscriber.setCatalog(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";book:" + getBook().getName() +
                ";description:" + getDescription() +
                ";totalCount:" + getTotalCount() +
                "}";
    }
}
