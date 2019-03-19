package ru.bakhuss.library.person.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.bakhuss.library.book.model.Book;
import ru.bakhuss.library.model.Image;
import ru.bakhuss.library.model.Subscriber;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Общая информация о человеке
 */
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Version
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
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
     * Description
     */
    @Column(length = 255)
    private String description;

    /**
     * Книги, написанные человеком
     */
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_writer",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> writtenBooks;


    public List<Book> getWrittenBooks() {
        if (writtenBooks == null) {
            writtenBooks = new ArrayList<>();
        }
        return writtenBooks;
    }

    public void addBook(Book book) {
        System.out.println(book.toString());
        getWrittenBooks().add(book);
        book.getWriters().add(this);
    }

    public void removeBook(Book book) {
        System.out.println(book.toString());
        getWrittenBooks().remove(book);
        book.getWriters().remove(this);
    }

    public void updateState(Person person) {
        setFirstName(person.getFirstName());
        setSecondName(person.getSecondName());
        setSurname(person.getSurname());
        setBirthday(person.getBirthday());
        setPhone(person.getPhone());
        setEmail(person.getEmail());
        setDescription(person.getDescription());
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
                "}";
    }
}
