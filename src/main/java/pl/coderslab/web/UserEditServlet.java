package pl.coderslab.web;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/user/edit")
public class UserEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        String name = request.getParameter("nameEdit");
        String lastName = request.getParameter("lastNameEdit");
        String email = request.getParameter("emailEdit");
        UserDao userDao = new UserDao();

        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        userDao.update(user);

        response.sendRedirect("/app/dashboardServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/editUserData.jsp").forward(request,response);

    }
}
