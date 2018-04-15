package ru.bakhuss.library.parser;

public class BookLitres {

    String art_id;

    String author_id;

    String alt;

    String author;

    String type;

    String mem;

    String drm;

    String price;

    String available;

    String author_pop_art;

    String can_preorder;

    String promo_price;


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
                ";promo_price:" + promo_price +
                "}";
    }

}
