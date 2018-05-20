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
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    /**
     * Имя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Отчество
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * Фамилия
     */
    @Column(length = 50, nullable = false)
    private String surname;

    /**
     * Дата рождения
     */
    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    /**
     * Телефон
     */
    @Column(length = 15)
    private String phone;

    /**
     * E-mail
     */
    @Column(length = 50)
    private String email;

    /**
     * Изображение человека
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    /**
     * Книги, написанные человеком
     */
    @ManyToMany(mappedBy = "writers",
            fetch = FetchType.LAZY)
    private Set<Book> writtenBooks;

    /**
     * Читательский билет
     */
    @OneToOne(mappedBy = "person",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
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
