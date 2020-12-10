package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/app/plan/edit")
public class PlanEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String planName = request.getParameter("namePlan");
        String description = request.getParameter("descriptionPlan");
        String date = String.valueOf(LocalDate.now());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        int userId = user.getId();
        PlanDao planDao = new PlanDao();
        Plan plan = new Plan(Integer.parseInt(id), planName, description, date, userId);
        planDao.updatePlan(plan, Integer.parseInt(id));
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        session.getAttribute("userSession");
        PlanDao planDao = new PlanDao();
        request.setAttribute("planEdit", planDao.readPlan(Integer.parseInt(id)));

        getServletContext().getRequestDispatcher("/app/sheduleEdit.jsp").forward(request, response);
    }
}