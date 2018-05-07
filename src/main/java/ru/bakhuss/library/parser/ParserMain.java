package ru.bakhuss.library.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserMain {

    public static void main(String[] args) {
//        String writer = "chingiz-abdullaev";
//        writer = boris-akunin;
//        writer = "lev-tolstoy";
//        writer = "dzhordzh-oruell";
//        writer = "vladimir-solovev";
//        writer = "aleksandr-pushkin";
//        writer = "aleksandr-duma";

//        ParserLitres parse = new ParserLitres();
//        parse.getParse(writer).stream().sorted().forEach(System.out::println);

//        parse.getAuthor(writer);

        ParserLiveLib parse = new ParserLiveLib();
//        String writer = "264-fjodor-dostoevskij";
//        writer = "5497-lev-tolstoj";
//        writer = "212153-ivan-turgenev";
//        writer = "8318-anton-chehov";
//        writer = "13161-nikolaj-gogol";
//        writer = "4215-aleksandr-pushkin";
//        writer = "3054-aleksandr-kuprin";
//        writer = "447-mihail-lermontov";
//        writer = "104475-ivan-goncharov";
//        writer = "617-nikolaj-leskov";
//        ArrayList<String> list = parse.getParse(writer);
//        list.forEach(System.out::println);
        try {
            List<String> list = parse.getParse(String.valueOf(300));
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
