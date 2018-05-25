package ru.bakhuss.library.dao;

import org.springframework.data.repository.CrudRepository;
import ru.bakhuss.library.model.Image;

public interface ImageDao extends CrudRepository<Image, Long> {
}
