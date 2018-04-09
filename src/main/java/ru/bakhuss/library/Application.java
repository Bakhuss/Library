package ru.bakhuss.library;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.service.impl.BookServiceImpl;
import ru.bakhuss.library.service.impl.CatalogServiceImpl;
import ru.bakhuss.library.service.impl.PersonServiceImpl;
import ru.bakhuss.library.service.impl.SubscriberListServiceImpl;
import ru.bakhuss.library.service.impl.SubscriberServiceImpl;

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

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
