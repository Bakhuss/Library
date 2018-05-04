package ru.bakhuss.library.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParserLiveLib {
    public Set<String> getParse(String writer) throws IOException {
        Set<String> titles = null;
        String url = null;
        url = "https://www.livelib.ru/author/" + writer;
        Document doc = Jsoup.connect(url).get();

        List<String> list = doc.getElementsByClass("ll_book").eachText();
        list.forEach(System.out::println);
        titles = new HashSet<>(list);

        return titles;
    }

    public String getAuthor(String writer) throws IOException {
        String url = null;
        url = "https://www.livelib.ru/author/" + writer;
        Document doc = Jsoup.connect(url).get();
        String name = doc.getElementsByClass("author-original-name").first().text();
        System.out.println(name);
        return name;
    }
}
