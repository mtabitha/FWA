package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    UserService     userService;
    PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        passwordEncoder = (PasswordEncoder) springContext.getBean("passwordEncoder");
        userService     = (UserService)     springContext.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = (String) getServletContext().getAttribute("htmlPath");
        req
                .getRequestDispatcher(path + "/SignUp.html")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                req.getParameter("email"),
                passwordEncoder.encode(req.getParameter("password")));

        resp.sendRedirect(userService.newUser(user));

    }
}
