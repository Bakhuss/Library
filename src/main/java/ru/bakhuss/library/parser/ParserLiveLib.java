package ru.bakhuss.library.parser;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserLiveLib {
    public ArrayList<String> getParse(String writer) throws Exception {
        ArrayList<String> titles = new ArrayList<>();
        String url = null;
        url = "https://www.livelib.ru/author/" + writer;
        Document doc = null;
        doc = Jsoup.connect(url).get();
        System.out.println(doc.title());
        String name = doc.getElementsByClass("author-original-name").first().text();
        titles.add(name);

        List<String> list = doc.getElementsByClass("ll_book").eachText();
        titles.addAll(list);

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
