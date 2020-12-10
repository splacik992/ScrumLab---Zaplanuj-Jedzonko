package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.LastRecipePlan;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/app/plan/details")
public class Plan_Details_Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.readPlan(id);
        List<LastRecipePlan> recipePlan = planDao.readRecipePlan(id);
        HashMap<String, List<LastRecipePlan>> recipePlansMap = new HashMap<>();

        for (int i = 0; i < recipePlan.size(); i++) {
            List<LastRecipePlan> tmp = new ArrayList<>();
            tmp.add(recipePlan.get(i));
            while ((i + 1) < recipePlan.size() && recipePlan.get(i + 1).getDayName().equals(recipePlan.get(i).getDayName())) {
                tmp.add(recipePlan.get(i + 1));
                i++;
            }
            recipePlansMap.put(recipePlan.get(i).getDayName(), tmp);
        }

        request.setAttribute("recipePlansMap", recipePlansMap);
        request.setAttribute("plan", plan);
        getServletContext().getRequestDispatcher("/app/plan_details.jsp").forward(request, response);
    }
}
