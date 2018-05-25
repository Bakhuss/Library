package ru.bakhuss.library.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bakhuss.library.model.Image;

import java.util.function.Function;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ImageView {

    public String id;

    public byte[] image;


    public static Function<Image, ImageView> getFuncImageToView() {
        return img -> {
          ImageView view = new ImageView();
          view.id = img.getId().toString();
          view.image = img.getImg();
          return view;
        };
    }

}
