package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Plan> userPlans = (List<Plan>) request.getSession().getAttribute("userPlans");
        List<Recipe> userRecipes = (List<Recipe>) request.getSession().getAttribute("userRecipes");
        RecipePlan recipePlan = new RecipePlan();
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        List<String> dayNames = Arrays.asList("poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela");

        recipePlan.setMealName(request.getParameter("mealName"));

        String param1 = request.getParameter("userPlan");
        for (Plan userPlan : userPlans) {
            if (userPlan.getName().equals(param1)) {
                recipePlan.setPlanId(userPlan.getId());
            }
        }

        String param2 = request.getParameter("userRecipe");
        for (Recipe userRecipe : userRecipes) {
            if (userRecipe.getName().equals(param2)) {
                recipePlan.setRecipeId(userRecipe.getId());
            }
        }

        String param3 = request.getParameter("dayName");
        for (String dayName : dayNames) {
            if (dayName.equals(param3)) {
                recipePlan.setDayNameId(dayNames.indexOf(dayName) + 1);
            }
        }

        String param4 = request.getParameter("displayOrder");
        if (param4.matches("[0-9]+")) {
            recipePlan.setDisplayOrder(Integer.parseInt(param4));
        } else {
            recipePlan.setDisplayOrder(0);
        }

        recipePlanDao.create(recipePlan);
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("userSession");
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        List<Plan> userPlans = planDao.findAll(user.getId());
        List<Recipe> userRecipes = recipeDao.findAll(user.getId());
        request.getSession().setAttribute("userPlans", userPlans);
        request.getSession().setAttribute("userRecipes", userRecipes);
        getServletContext().getRequestDispatcher("/app/add_recipe_to_plan.jsp").forward(request, response);
    }
}
