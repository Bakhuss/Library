package ru.bakhuss.library.catalog.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Список книг
 */
@Data
@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Version
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{id:" + getId() +
                ";description:" + getDescription() +
                ";totalCount:" + getTotalCount() +
                "}";
    }
}
