package ru.bakhuss.library.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ParserClass {

    public static void main(String[] args) throws Exception {
        String url = null;
        url = "https://www.litres.ru/pages/rmd_search_authors/?q=Книж";
        url = "https://www.litres.ru/chingiz-abdullaev";
        Document doc = Jsoup.connect(url).get();

        Element el = doc.getElementsByAttribute("data-obj")
                .first();
        String s = doc.attr("data-obj");
        String str = el.attr("data-obj")
                .replace("{", " ")
                .replace("}", " ").trim();

        List<String> list = doc.getElementsByAttribute("data-obj").eachAttr("data-obj");
        String t = list.get(0)
                .replace("{", " ")
                .replace("}", " ").trim();
        List<String> tr = Arrays.stream(t.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        List<String> tr1 = tr.stream()
                .map(p -> p.replace("'", " "))
                .collect(Collectors.toList());
        HashMap<String, String> hashMap = new HashMap<>();
        Function<String, String> function = p -> {
            String[] strings = p.split(":");
            hashMap.put(strings[0].trim(), strings[1].trim());
            System.out.println(strings[0]);
            return new String();
        };
        tr1.stream().map(function);
        System.out.println("map: " + hashMap.size());
        for (String s1 : tr1) {
            System.out.println(s1);
        }
    }

}
