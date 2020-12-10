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
import java.time.LocalDateTime;

@WebServlet("/app/recipe/add")
public class RecipeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        recipeDao.create(new Recipe(request.getParameter("recipeName"),
                request.getParameter("ingredients"),
                request.getParameter("recipeDes"),
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                Integer.parseInt(request.getParameter("prepTime")),
                request.getParameter("prepDetails"),
                user.getId()));
        response.sendRedirect("/app/recipe/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        getServletContext().getRequestDispatcher("/app/AddRecipe.jsp").forward(request, response);
    }
}
