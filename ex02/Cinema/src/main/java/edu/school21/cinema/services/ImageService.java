package edu.school21.cinema.services;

import edu.school21.cinema.dao.ImageDao;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageService {

    private ImageDao    imageDao;
    private String      storagePath;

    private void createDirectory(String path) {
        File dir = new File(storagePath + path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    @PostConstruct
    public void createStorageDirectory() {
        createDirectory(null);
    }

    public ImageService(ImageDao imageDao, String storagePath) {
        this.imageDao = imageDao;
        this.storagePath = storagePath;
    }


    public void newImage(Part image) {
        if (image == null) {
            return;
        }
        String imageName    = image.getSubmittedFileName();
        String extension    = imageName.substring(imageName.lastIndexOf('.')); //.jpeg
        String newImageName = UUID.randomUUID().toString() + extension;

        createDirectory(extension.replace('.', '/'));
        try {
            image.write(storagePath + "/" + newImageName);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
