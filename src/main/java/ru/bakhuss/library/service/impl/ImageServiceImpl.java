package ru.bakhuss.library.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bakhuss.library.dao.ImageDao;
import ru.bakhuss.library.error.ResponseErrorException;
import ru.bakhuss.library.model.Image;
import ru.bakhuss.library.service.ImageService;
import ru.bakhuss.library.view.ImageView;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class ImageServiceImpl implements ImageService {
    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageDao imageDao;

    @Autowired
    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Long addImage(ImageView view) {
        if (view.image.length == 0) throw new ResponseErrorException("Blank image");
        Image image = new Image();
        image.setImg(view.image);
        Image newImage = null;
        try {
            newImage = imageDao.save(image);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error saving image");
        }
        return newImage.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteImage(ImageView view) {
        try {
            Long id = Long.parseLong(view.id);
            if (imageDao.exists(id))
                throw new ResponseErrorException("Not found image by id: " + view.id);
            imageDao.delete(id);
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Image id must be a number(" + view.id + ")");
        } catch (Exception ex) {
            throw new ResponseErrorException("Error removing image by id: " + view.id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ImageView getImageById(String id) {
        Image image = null;
        try {
            image = imageDao.findOne(Long.parseLong(id));
            /*
             * Проверка на NPE
             */
            image.getId();
        } catch (NumberFormatException ex) {
            throw new ResponseErrorException("Image id must be a number(" + id + ")");
        } catch (NullPointerException ex) {
            throw new ResponseErrorException("Not found image by id: " + id);
        } catch (Exception ex) {
            throw new ResponseErrorException("Error requesting image");
        }
        ImageView imgView = new ImageView();
        imgView.id = image.getId().toString();
        imgView.image = image.getImg();

        return imgView;
    }
}
