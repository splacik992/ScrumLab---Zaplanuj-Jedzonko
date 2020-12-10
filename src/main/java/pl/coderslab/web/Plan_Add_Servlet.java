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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/app/plan/add")
public class Plan_Add_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        LocalDateTime dateTime = LocalDateTime.now();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        if (!planName.equals("") && !planDescription.equals("")) {
            planDao.createPlan(new Plan(planName,  planDescription,dateTime.toString(), user.getId()));
        }
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/add_plan.jsp").forward(request, response);
    }
}
