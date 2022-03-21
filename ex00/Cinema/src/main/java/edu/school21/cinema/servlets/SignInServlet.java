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
import java.util.Optional;

public class SignInServlet extends HttpServlet {

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
                .getRequestDispatcher(path + "/SignIn.html")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email    = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> user =  userService.getUser(email);
        user.ifPresent(u -> {
                if (passwordEncoder.matches(password, u.getPassword())) {
                    req.getSession().setAttribute("user", u);
            }
        });
        resp.sendRedirect("/main");
    }
}
