package pl.coderslab.model;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter", urlPatterns = {"/app/*"})
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = ((HttpServletRequest) req).getSession();
        if (session.getAttribute("userSession") == null) {
            ((HttpServletResponse) resp).sendRedirect("/login");
        }
        chain.doFilter(req, resp);



    }

    public void init(FilterConfig config) throws ServletException {

    }

}
