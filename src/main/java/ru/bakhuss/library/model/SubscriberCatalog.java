package ru.bakhuss.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

/**
 * Связь читателя с книгой из Catalog (library card)
 */
@Entity(name = "Subscriber_Catalog")
public class SubscriberCatalog {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @Column(name = "receive_date", updatable = false)
    @Temporal(value = TemporalType.DATE)
    private Date receiveDate;

    @Column(name = "return_date", updatable = false)
    @Temporal(value = TemporalType.DATE)
    private Date returnDate;


    public Long getId() {
        return id;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";subscriber:" + getSubscriber() +
                ";book:" + getCatalog() +
                ";receiveDate:" + getReceiveDate() +
                ";returnDate:" + getReturnDate() +
                "}";
    }
}
