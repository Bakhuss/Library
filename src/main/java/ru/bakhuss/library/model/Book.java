package ru.bakhuss.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Collection;
import java.util.Set;

/**
 * Книга
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    /**
     * Название
     */
    @Column(length = 100, nullable = false)
    private String name;

    /**
     * Автор(-ы)
     */
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    },
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "Book_Writer",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> writers;


    /**
     * Запись в каталоге книг
     */
    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Collection<Catalog> catalogs;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getWriters() {
        return writers;
    }

    public void setWriters(Set<Person> writers) {
        this.writers = writers;
    }

    public void addWriters(Set<Person> prs) {
        getWriters().addAll(prs);
        prs.forEach(p -> p.getWrittenBooks().add(this));
    }

    public void removeWriters(Set<Person> prs) {
        getWriters().removeAll(prs);
        prs.forEach(p -> p.getWrittenBooks().remove(this));
    }


    public Collection<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Collection<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public void addCatalog(Catalog catalog) {
        getCatalogs().add(catalog);
        catalog.setBook(this);
    }

    public void removeCatalog(Catalog catalog) {
        getCatalogs().remove(catalog);
        catalog.setBook(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";name:" + getName() +
                ";writers:" + getWriters() +
                ";catalogs:" + getCatalogs() +
                "}";
    }
}
