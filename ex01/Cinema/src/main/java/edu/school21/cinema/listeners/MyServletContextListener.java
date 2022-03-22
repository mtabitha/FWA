package edu.school21.cinema.listeners;

import edu.school21.cinema.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("springContext", new AnnotationConfigApplicationContext(SpringConfig.class));
        servletContext.setAttribute("jspPath", "/WEB-INF/jsp");
        servletContext.setAttribute("htmlPath", "/WEB-INF/html");

        System.out.println("Servlet Context Init");
    }


}
