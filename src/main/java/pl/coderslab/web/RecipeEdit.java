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

@WebServlet("/app/recipe/edit")
public class RecipeEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        int userId = user.getId();
        int id = Integer.parseInt(request.getParameter("recipeId"));
        String name = request.getParameter("recipeName");
        String ingredients = request.getParameter("recipeIngredients");
        String description = request.getParameter("recipeDescription");
        int preparationTime = Integer.parseInt(request.getParameter("recipePreparationTime"));
        String preparation = request.getParameter("recipePreparation");
        Recipe recipe = new Recipe(id, name, ingredients, description, preparationTime, preparation, userId);
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.update2(recipe);
        response.sendRedirect("/app/recipe/list");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Recipe recipe = recipeDao.read(id);
        request.setAttribute("recipe",recipe);
        getServletContext().getRequestDispatcher("/app/app-recipe-edit.jsp").forward(request,response);
    }
}
