package ru.bakhuss.library;

import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.model.Person;
import ru.bakhuss.library.service.impl.BookServiceImpl;
import ru.bakhuss.library.service.impl.CatalogServiceImpl;
import ru.bakhuss.library.service.impl.PersonServiceImpl;
import ru.bakhuss.library.service.impl.SubscriberListServiceImpl;
import ru.bakhuss.library.service.impl.SubscriberServiceImpl;
import ru.bakhuss.library.view.PersonView;

import java.util.Date;

/**
 * Точка входа.
 */
@ImportResource("spring_mvc_config.xml")
@SpringBootApplication
@ComponentScan(basePackageClasses = {
        BookServiceImpl.class, BookDao.class,
        CatalogServiceImpl.class, CatalogDao.class,
        PersonServiceImpl.class, PersonDao.class,
        SubscriberServiceImpl.class, SubscriberDao.class,
        SubscriberListServiceImpl.class, SubscriberDao.class
})
public class Application {

    static ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        Application.context = context;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        PersonView p = new PersonView();
        p.firstName = "Piter";
        p.surname = "Pen";
        p.birthday = new Date();

        PersonView p1 = new PersonView();
        p1.firstName = "Ivan";
        p1.surname = "Ivanov";
        p1.birthday = new Date();

        PersonServiceImpl psi = context.getBean(PersonServiceImpl.class);

        System.out.println("----Application----");

        System.out.println("------------------");
        System.out.println(psi.getAllPersons());
    }
}
