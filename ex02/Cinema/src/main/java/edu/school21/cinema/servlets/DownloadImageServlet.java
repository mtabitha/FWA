package edu.school21.cinema.servlets;

import edu.school21.cinema.services.ImageService;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class DownloadImageServlet extends HttpServlet {

    private ImageService imageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        imageService = (ImageService)   springContext.getBean("imageService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String fileName = UUID.randomUUID().toString();
//        for (Part part : req.getParts()) {
//            part.write(storagePath + "/" + fileName);
//        }
        System.out.println("hello");
        imageService.newImage(req.getPart("file"));
        resp.sendRedirect("/profile");
    }
}
