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
import java.util.stream.Collectors;

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
    @Column(nullable = false)
    private String name;

    /**
     * Автор(-ы)
     */
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
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

    public void addWriter(Person person) {
        person.getWrittenBooks().add(this);
        getWriters().add(person);
    }

    public void removeWriter(Person person) {
        person.getWrittenBooks().removeIf(b -> b.getId() == this.getId());
        getWriters().removeIf(w -> w.getId() == person.getId());
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
