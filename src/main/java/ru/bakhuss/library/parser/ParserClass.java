package ru.bakhuss.library.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ParserClass {

    public static void main(String[] args) throws Exception {
        String url = null;
        url = "https://www.litres.ru/pages/rmd_search_authors/?q=Книж";
        url = "https://www.litres.ru/chingiz-abdullaev";
        Document doc = Jsoup.connect(url).get();

        List<String> list = doc.getElementsByAttribute("data-obj").eachAttr("data-obj");
        System.out.println(list.get(0));
        List<String> list1 = list.stream()
                .map(p -> p.replace('\'', '\u0000'))
                .map(p -> p.replace('{', '\u0000'))
                .map(p -> p.replace('}', '\u0000').trim())
                .collect(Collectors.toList());
        System.out.println("size: " + list1.size());
        HashMap<String, String> map = new HashMap<>(12);
        for (String sl : list1) {
            map.clear();
            String[] ls = sl.split(",");
            for (String l : ls) {
                System.out.println("strings: " + l.split(":").length);
                String a = l.split(":")[0].trim();
                String b = null;
                if (l.split(":")[1] != null) {
                    b = l.split(":")[1];
                }
                map.put(a, b);
            }
            BookLitres book = new BookLitres();
            book.art_id = map.get("art_id");
            book.author_id = map.get("author_id");
            book.alt = map.get("alt");
            System.err.println(book.toString() + "\n");
        }

    }

}
