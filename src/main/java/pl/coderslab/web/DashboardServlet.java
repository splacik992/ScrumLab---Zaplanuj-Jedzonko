package pl.coderslab.web;

import pl.coderslab.dao.DayNameDAO;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/app/dashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("recipeId");
        int recipeId = 0;
        RecipeDao recipeDao = new RecipeDao();
        for (Recipe recipe : recipeDao.findAll()) {
            if (recipe.getName().equals(param)) {
                recipeId = recipe.getId();
            }
        }
        Recipe read = recipeDao.read(recipeId);
        request.setAttribute("read", read);
        //request.setAttribute("id", recipeId);
        getServletContext().getRequestDispatcher("/app/recipe_details_dashboard.jsp").forward(request,response);
        //response.sendRedirect("/app/recipe/details/dashboard?id=" + recipeId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        String userId = String.valueOf(user.getId());
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        List<Plan> planList = planDao.findAll(Integer.parseInt(userId));
        List<Recipe> recipeList = recipeDao.findAll(Integer.parseInt(userId));

        String planSize = String.valueOf(planList.size());
        String recipeSize = String.valueOf(recipeList.size());
        request.setAttribute("planCount", planSize);
        request.setAttribute("recipeCount", recipeSize);

        String lastPlanName = "";

        if (planList.isEmpty()) {
            lastPlanName = "Nie masz jeszcze planów.";
        } else {
            Plan plan = planList.get(planList.size() - 1);
            lastPlanName = plan.getName();
        }
        request.setAttribute("plan", lastPlanName);

        List<LastRecipePlan> lastPlan = planDao.loadLastPlan(Integer.valueOf(userId));
        for (LastRecipePlan lastRecipePlan : lastPlan) {
            request.setAttribute("lastPlanRecipe", lastRecipePlan);

        }

        HashMap<String, List<LastRecipePlan>> recipePlanMAP = new HashMap<>();
        for (int i = 0; i < lastPlan.size(); i++) {
            List<LastRecipePlan> tmp = new ArrayList<>();
            tmp.add(lastPlan.get(i));
            while ((i + 1) < lastPlan.size() && lastPlan.get(i + 1).getDayName().equals(lastPlan.get(i).getDayName())) {
                tmp.add(lastPlan.get(i + 1));
                i++;
            }
            recipePlanMAP.put(lastPlan.get(i).getDayName(), tmp);
        }
        request.setAttribute("recipePlansMap2", recipePlanMAP);

        getServletContext().getRequestDispatcher("/app/dashboard.jsp").forward(request, response);

    }
}
