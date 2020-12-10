package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RecipeDetailsServlet", urlPatterns = "/app/recipe/details")
public class RecipeDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        RecipeDao recipeDao = new RecipeDao();
        int recipeID = Integer.parseInt(request.getParameter("id"));
        System.out.println(recipeID);
        Recipe read = recipeDao.read(recipeID);
        request.setAttribute("read", read);
        getServletContext().getRequestDispatcher("/app/recipe-details.jsp").forward(request, response);
    }
}