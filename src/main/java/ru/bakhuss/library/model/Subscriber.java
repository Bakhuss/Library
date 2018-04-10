package ru.bakhuss.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

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


//    /**
//     * Список книг читателя
//     */
//    @ManyToMany(
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            }
//    )
//    @JoinTable(
//            name = "Book_Subscriber",
//            joinColumns = @JoinColumn(name = "subscriber_id"),
//            inverseJoinColumns = @JoinColumn(name = "catalog_id")
//    )
//    private Set<Catalog> catalogs;

    /**
     * Список книг читателя
     */
    @OneToOne(
            mappedBy = "subscriber",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    private SubscriberList subscriberList;


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
                ";person:" + getPerson() +
                ";subscriberDate:" + getSubscribeDate() +
                ";unsubscriberDate:" + getUnsubscribeDate() +
                ";subscriberList:" + getSubscriberList() +
                "}";
    }
}
