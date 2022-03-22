package edu.school21.cinema.filters;

import edu.school21.cinema.models.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
        if (user != null)
            chain.doFilter(req, res);
        else
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
