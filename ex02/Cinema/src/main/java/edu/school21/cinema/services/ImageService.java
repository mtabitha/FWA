package edu.school21.cinema.services;

import edu.school21.cinema.dao.ImageDao;
import edu.school21.cinema.models.Image;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ImageService {

    private ImageDao    imageDao;
    private String      storagePath;



    @PostConstruct
    public void createStorageDirectory() {
        createDirectory("");
    }

    public ImageService(ImageDao imageDao, String storagePath) {
        this.imageDao = imageDao;
        this.storagePath =  storagePath.endsWith("/") ? storagePath.substring(0, storagePath.length() - 1)
                                                      : storagePath;
    }

    public List<Image> getImages() {
        return imageDao.findAll();
    }


    public void newImage(Part image) {
        if (image == null) {
            return;
        }
        String imageName    = image.getSubmittedFileName();
        String extension    = imageName.substring(imageName.lastIndexOf('.')); //.jpeg
        String newImageName = UUID.randomUUID() + extension;
        extension = extension.replace('.', '/');

        String path         = storagePath + extension + "/" + newImageName;

        createDirectory(extension);
        writeImage(image, path);
        imageDao.save(new Image(newImageName, readableFileSize(image.getSize()), storagePath + extension));
    }


    public void sendImage(String imageName, HttpServletResponse resp) throws IOException {
        Optional<Image> imageFromDB = imageDao.findByName(imageName);
        if (!imageFromDB.isPresent()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }
        ServletOutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(imageFromDB.get().getPath() + "/" + imageName);
        readImage(out, in);
    }

    private void readImage(ServletOutputStream out, FileInputStream in) throws IOException {
        byte[] buff = new byte[1024];
        int count = 0;
        while ((count = in.read(buff)) >= 0) {
            out.write(buff, 0, count);
        }
        in.close();
        out.close();
    }



    private void writeImage(Part image, String path) {
        try {
            image.write(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createDirectory(String path) {
        File dir = new File(storagePath + path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }



    private String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB", "PB", "EB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        String result = null;
        result = new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups))  + units[digitGroups];
        return result;
    }


}
