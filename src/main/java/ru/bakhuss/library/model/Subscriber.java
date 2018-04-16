package ru.bakhuss.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;
import java.util.Set;

/**
 * Читатель
 */
@Entity
public class Subscriber {

    @Id
    @Column(name = "person_id")
    private Long id;

    @Version
    private Integer version;

    /**
     * Дата регистрации
     */
    @Column(name = "subscribe_date")
    @Temporal(TemporalType.DATE)
    private Date subscribeDate;

    /**
     * Дата удаления
     */
    @Column(name = "unsubscribe_date")
    @Temporal(TemporalType.DATE)
    private Date unsubscribeDate;

    /**
     * Информация о человеке
     */
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Person person;

    /**
     * Список книг читателя
     */
    @OneToMany(mappedBy = "catalog",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<LibraryCard> catalogs;


    public Long getId() {
        return id;
    }

    public Date getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public Date getUnsubscribeDate() {
        return unsubscribeDate;
    }

    public void setUnsubscribeDate(Date unsubscribeDate) {
        this.unsubscribeDate = unsubscribeDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<LibraryCard> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<LibraryCard> catalogs) {
        this.catalogs = catalogs;
    }

    public void addCatalog(LibraryCard catalog) {
        getCatalogs().add(catalog);
        catalog.setSubscriber(this);
    }

    public void removeCatalog(LibraryCard catalog) {
        getCatalogs().remove(catalog);
        catalog.setSubscriber(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";person:" + getPerson().toString() +
                ";subscriberDate:" + getSubscribeDate() +
                ";unsubscriberDate:" + getUnsubscribeDate() +
                "}";
    }
}
