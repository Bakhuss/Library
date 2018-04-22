package ru.bakhuss.library.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParserClass {

    public Set<String> getParse(String writer) throws IOException {
        String url = null;
        url = "https://www.litres.ru/pages/rmd_search_authors/?q=Книж";
        url = "https://www.litres.ru/" + writer;
        Document doc = Jsoup.connect(url).get();

        List<String> list = doc.getElementsByAttribute("data-obj").eachAttr("data-obj");

        System.out.println("size: " + list.size());
//        System.out.println(list.get(0));
        List<String> list1 = list.stream()
                .map(p -> p.replace('\'', '"'))
                .map(p -> p.replaceAll("art_id", "\"art_id\""))
                .map(p -> p.replaceAll("author", "\"author\""))
                .map(p -> p.replaceAll("\"author\"_id", "\"author_id\""))
                .map(p -> p.replaceAll("alt", "\"alt\""))
                .map(p -> p.replaceAll("type", "\"type\""))
                .map(p -> p.replaceAll("mem", "\"mem\""))
                .map(p -> p.replaceAll("drm", "\"drm\""))
                .map(p -> p.replaceAll("price", "\"price\""))
                .map(p -> p.replaceAll("available", "\"available\""))
                .map(p -> p.replaceAll("\"author\"_pop_art", "\"author_pop_art\""))
                .map(p -> p.replaceAll("can_preorder", "\"can_preorder\""))
                .map(p -> p.replaceAll("purchased", "\"purchased\""))
                .map(p -> p.replaceAll("promo_\"price\"", "\"promo_price\""))
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        Set<String> titles = null;
        titles = list1.stream().map(p -> {
            try {
                return mapper.readValue(p, BookLitres.class).alt;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toSet());
        return titles;
    }
}
