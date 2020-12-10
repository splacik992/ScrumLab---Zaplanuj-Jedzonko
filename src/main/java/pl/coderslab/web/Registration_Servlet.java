package pl.coderslab.web;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/register")
public class Registration_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");

        if (request.getParameter("password").equals(request.getParameter("repassword"))) {

            UserDao userDao = new UserDao();
            List<User> tmp = userDao.findAll();
            boolean uniqueEmail = true;
            for (User user : tmp) {
                if (request.getParameter("email").equals(user.getEmail())) {
                    uniqueEmail = false;
                }
            }

            if (uniqueEmail) {
                userDao.create(new User(request.getParameter("name"), request.getParameter("surname"),
                        request.getParameter("email"), request.getParameter("password")));
                response.sendRedirect("/login");
            } else {
                response.sendRedirect("/registrationNotUnique.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}
