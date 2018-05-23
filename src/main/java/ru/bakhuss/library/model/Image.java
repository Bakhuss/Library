package ru.bakhuss.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    @Column
    private byte[] img;


    public Long getId() {
        return id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
