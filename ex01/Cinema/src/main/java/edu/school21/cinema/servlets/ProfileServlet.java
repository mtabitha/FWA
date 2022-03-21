package edu.school21.cinema.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = (String) getServletContext().getAttribute("htmlPath");
        req
                .getRequestDispatcher(path + "/Profile.html")
                .forward(req, resp);
    }
}
