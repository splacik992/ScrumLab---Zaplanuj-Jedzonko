package pl.coderslab.web;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;
import pl.coderslab.utils.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession httpSession = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            if (email != null || password != null ) {
                if (user.getEmail().equals(email) && BCrypt.checkpw(password, user.getPassword())) {
                    httpSession.setAttribute("userSession",user);
                    response.sendRedirect("/app/dashboardServlet");
                    return;
                }
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}