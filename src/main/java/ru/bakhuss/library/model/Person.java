package ru.bakhuss.library.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Общая информация о человеке
 */
public class Person {

    private Long id;

    private Integer version;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Отчество
     */
    private String secondName;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Дата рождения
     */
    private Date birthday;

    /**
     * Телефон
     */
    private String phone;

    /**
     * E-mail
     */
    private String email;

    /**
     * Description
     */
    private String description;

    /**
     * Изображение человека
     */
    private Image image;

    /**
     * Книги, написанные человеком
     */
    private Set<Book> writtenBooks;

    /**
     * Читательский билет
     */
    private Subscriber subscriber;


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }

    public void addWrittenBook(Book book) {
        getWrittenBooks().add(book);
        book.getWriters().add(this);
    }

    public void removeWrittenBook(Book book) {
        book.getWriters().removeIf(w -> w.id == this.getId());
        getWrittenBooks().removeIf(b -> b.getId() == book.getId());
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";firstName:" + getFirstName() +
                ";secondName:" + getSecondName() +
                ";surname:" + getSurname() +
                ";birthday:" + getBirthday() +
                ";phone:" + getPhone() +
                ";email:" + getEmail() +
                ";image:" + (image != null) +
                "}";
    }
}
