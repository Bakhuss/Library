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
import ru.bakhuss.library.service.impl.BookServiceImpl;
import ru.bakhuss.library.service.impl.CatalogServiceImpl;
import ru.bakhuss.library.service.impl.PersonServiceImpl;
import ru.bakhuss.library.service.impl.LibraryCardServiceImpl;
import ru.bakhuss.library.service.impl.SubscriberServiceImpl;

import java.util.Date;

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

        BookServiceImpl csi = context.getBean(BookServiceImpl.class);

        System.out.println("----Application----");
        System.out.println(new Date());
    }
}
