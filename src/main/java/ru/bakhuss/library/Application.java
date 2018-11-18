package ru.bakhuss.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.bakhuss.library.controller.impl.BookControllerImpl;
import ru.bakhuss.library.controller.impl.CatalogControllerImpl;
import ru.bakhuss.library.controller.impl.LibraryCardControllerImpl;
import ru.bakhuss.library.controller.impl.PersonControllerImpl;
import ru.bakhuss.library.controller.impl.SubscriberControllerImpl;
import ru.bakhuss.library.dao.BookDao;
import ru.bakhuss.library.dao.CatalogDao;
import ru.bakhuss.library.dao.LibraryCardDao;
import ru.bakhuss.library.dao.PersonDao;
import ru.bakhuss.library.dao.SubscriberDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.service.impl.BookServiceImpl;
import ru.bakhuss.library.service.impl.CatalogServiceImpl;
import ru.bakhuss.library.service.impl.LibraryCardServiceImpl;
import ru.bakhuss.library.service.impl.PersonServiceImpl;
import ru.bakhuss.library.service.impl.SubscriberServiceImpl;

/**
 * Точка входа.
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = {
        BookServiceImpl.class, BookDao.class, BookControllerImpl.class,
        CatalogServiceImpl.class, CatalogDao.class, CatalogControllerImpl.class,
        PersonServiceImpl.class, PersonDao.class, PersonControllerImpl.class,
        SubscriberServiceImpl.class, SubscriberDao.class, SubscriberControllerImpl.class,
        LibraryCardServiceImpl.class, LibraryCardDao.class, LibraryCardControllerImpl.class,
        ResponseErrorException.class
})
public class Application extends SpringBootServletInitializer {

    private static ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        Application.context = context;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        System.out.println("----Application----");
    }
}
