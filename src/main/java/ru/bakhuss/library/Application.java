package ru.bakhuss.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import ru.bakhuss.library.controller.impl.BookControllerImpl;
import ru.bakhuss.library.controller.impl.CatalogControllerImpl;
import ru.bakhuss.library.controller.impl.LibraryCardControllerImpl;
import ru.bakhuss.library.controller.impl.PersonControllerImpl;
import ru.bakhuss.library.controller.impl.SubscriberControllerImpl;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.dao.LibraryCardDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Book;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.parser.ParserLiveLib;
import ru.bakhuss.library.service.impl.BookServiceImpl;
import ru.bakhuss.library.service.impl.CatalogServiceImpl;
import ru.bakhuss.library.service.impl.PersonServiceImpl;
import ru.bakhuss.library.service.impl.LibraryCardServiceImpl;
import ru.bakhuss.library.service.impl.SubscriberServiceImpl;
import ru.bakhuss.library.view.BookView;
import ru.bakhuss.library.view.PersonView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Точка входа.
 */
@ImportResource("spring_mvc_config.xml")
@SpringBootApplication
@ComponentScan(basePackageClasses = {
        BookServiceImpl.class, BookDao.class, BookControllerImpl.class,
        CatalogServiceImpl.class, CatalogDao.class, CatalogControllerImpl.class,
        PersonServiceImpl.class, PersonDao.class, PersonControllerImpl.class,
        SubscriberServiceImpl.class, SubscriberDao.class, SubscriberControllerImpl.class,
        LibraryCardServiceImpl.class, LibraryCardDao.class, LibraryCardControllerImpl.class,
        ResponseErrorException.class
})
public class Application {

    private static ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        Application.context = context;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);


        BookServiceImpl bsi = context.getBean(BookServiceImpl.class);
        PersonServiceImpl psi = context.getBean(PersonServiceImpl.class);
        PersonDao dao = context.getBean(PersonDao.class);
        BookDao bDao = context.getBean(BookDao.class);

//        PersonView person = psi.getPersonById(String.valueOf(2));
//        person.writtenBooks.clear();
//        List<BookView> list = new ArrayList<>();
//        for (int i = 190; i < 334; i++) {
//            BookView v = new BookView();
//            v.id = String.valueOf(i);
//            list.add(v);
//        }
//        person.writtenBooks.addAll(list);
//        psi.updatePerson(person);



//        String author = null;
//        List<String> authors = new ArrayList<>();
//        authors.add("10293-ilya-ilf-evgenij-petrov");
//        authors.add("264-fjodor-dostoevskij");
//        authors.add("5497-lev-tolstoj");
//        authors.add("212153-ivan-turgenev");
//        authors.add("8318-anton-chehov");
//        authors.add("13161-nikolaj-gogol");
//        authors.add("4215-aleksandr-pushkin");
//        authors.add("3054-aleksandr-kuprin");
//        authors.add("447-mihail-lermontov");
//        authors.add("104475-ivan-goncharov");
//        authors.add("617-nikolaj-leskov");
//        authors.add("2511-maksim-gorkij");
//        authors.add("231446-aleksandr-ostrovskij");
//        authors.add("816-aleksandr-griboedov");
//        authors.add("2964-ivan-bunin");
//        authors.add("5498-mihail-saltykovschedrin");
//        authors.add("180735-denis-fonvizin");
//        authors.add("408-mihail-bulgakov");
//        authors.add("5049-nikolaj-karamzin");
//        Long id = 1L;

        ParserLiveLib parse = new ParserLiveLib();
        for (int s = 41; s < 100; s++) {
            try {
                ArrayList<String> books = parse.getParse(String.valueOf(s));
                System.out.println("book.size: " + books.size());

                if (books.size() != 0) {
                    PersonView pV = new PersonView();
                    String[] strs = books.get(0).split(" ");
                    Arrays.stream(strs).forEach(System.out::println);
                    System.out.println(strs.length);
                    if (strs.length < 2) continue;
                    if (strs[1].equals("книг:")) continue;
                    pV.firstName = strs[0];
                    if (strs.length < 3) pV.surname = strs[1];
                    else {
                        pV.secondName = strs[1];
                        pV.surname = strs[2];
                    }
                    System.out.println("-------------Person View: " + pV.toString());
                    Long id = psi.addPerson(pV);
                    PersonView person = psi.getPersonById(String.valueOf(id));

                    for (int i = 1; i < books.size(); i++) {
                        BookView bV = new BookView();
                        bV.name = books.get(i);
                        bV.writers = new HashSet<>();
                        bV.writers.add(person);
                        bsi.addBook(bV);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


//////            if (books != null) {
//////                books.stream().map(b -> {
//////                    BookView bV = new BookView();
//////                    bV.name = b;
//////                    return bV;
//////                }).forEach(b -> {
//////                    b.writers = new HashSet<>();
//////                    b.writers.add(person);
//////                    bsi.addBook(b);
//////                });
//////            }
////            id++;
        }

        System.out.println("----Application----");
        System.out.println(new Date());
    }
}
