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

@WebServlet("/app/edit/pass")
public class User_Password_Edit_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPass = request.getParameter("newPass");
        String reNewPass = request.getParameter("reNewPass");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        if (newPass.equals(reNewPass) && !newPass.equals("")) {
            UserDao userDao = new UserDao();
            userDao.updatePass(user, newPass);
        }
        response.sendRedirect("/dashboardServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/app/app-edit-password.jsp").forward(request, response);
    }
}
