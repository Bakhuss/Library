package ru.bakhuss.library.parser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookLitres {

    public String art_id;

    public String author_id;

    public String alt;

    public String author;

    public String type;

    public String mem;

    public String drm;

    public String price;

    public String available;

    public String author_pop_art;

    public String can_preorder;

    public String purchased;

    public String promo_price;


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{art_id:" + art_id +
                ";author_id:" + author_id +
                ";alt:" + alt +
                ";author:" + author +
                ";type:" + type +
                ";mem:" + mem +
                ";drm:" + drm +
                ";price:" + price +
                ";available:" + available +
                ";author_pop_art:" + author_pop_art +
                ";can_preorder:" + can_preorder +
                ";purchased:" + purchased +
                ";promo_price:" + promo_price +
                "}";
    }

}
