package ru.bakhuss.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    /**
     * Список читателей книги
     */
    @OneToOne(
            mappedBy = "catalog",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    private SubscriberList subscriberList;


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

    public SubscriberList getSubscriberList() {
        return subscriberList;
    }

    public void setSubscriberList(SubscriberList subscriberList) {
        this.subscriberList = subscriberList;
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
                ";subscriberList:" + getSubscriberList() +
                "}";
    }
}
