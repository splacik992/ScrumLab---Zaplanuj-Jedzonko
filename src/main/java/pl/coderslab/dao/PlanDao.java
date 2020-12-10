package pl.coderslab.dao;

import pl.coderslab.model.LastRecipePlan;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DBUtil2;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private static final String CREATE = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";
    private static final String READ = "SELECT * FROM plan WHERE id = ?;";
    private static final String UPDATE = "UPDATE plan SET name = ? , description = ?, created = ? , admin_id = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM plan WHERE id = ?;";

    private static final String FIND_ALL_PLAN_QUERY = "SELECT * FROM plan;";
    private static final String FIND_ALL_PLAN_OF_ADMINid_QUERY = "SELECT * FROM plan WHERE admin_id = ?;";

    private static final String NUMBER_OF_PLANS_QUERY = "SELECT count(*) AS count FROM plan WHERE admin_id = ?;";
    private static final String LAST_RECIPE_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "        recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;\n";
    private static final String FIND_USER_PLANS_QUERY = "SELECT id, name, description FROM plan WHERE admin_id = ? " +
            "ORDER " +
            "BY id DESC";
    private static final String READ_RECIPE_PLAN = "SELECT day_name.name as day_name, meal_name, recipe.name as " +
            "recipe_name, recipe.description as recipe_description, recipe_id\n" +
            "FROM `recipe_plan`\n" +
            "JOIN day_name on day_name.id=day_name_id\n" +
            "JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order";

    public List<Plan> readUserPlans(int id) {
        List<Plan> res = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_PLANS_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                res.add(plan);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public void createPlan(Plan plan) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, plan.getName());
            preparedStatement.setString(2, plan.getDescription());
            preparedStatement.setString(3, plan.getCreated());
            preparedStatement.setInt(4, plan.getAdmin_id());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Plan readPlan(int id) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                plan.setId(resultSet.getInt("id"));
                plan.setName(resultSet.getString("name"));
                plan.setDescription(resultSet.getString("description"));
                plan.setCreated(resultSet.getString("created"));
                plan.setAdmin_id(resultSet.getInt("admin_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return plan;
    }

    public void updatePlan(Plan plan, int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, plan.getName());
            preparedStatement.setString(2, plan.getDescription());
            preparedStatement.setString(3, plan.getCreated());
            preparedStatement.setInt(4, plan.getAdmin_id());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePlan(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public List<Plan> findAll(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLAN_OF_ADMINid_QUERY)) {

            statement.setInt(1, adminId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdmin_id(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }


    public int numberOfPlans(int id) {
        int numberOfPlans = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(NUMBER_OF_PLANS_QUERY);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                numberOfPlans = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfPlans;
    }

    public List<LastRecipePlan> loadLastPlan(Integer adminId) {
        List<LastRecipePlan> lastRecipePlan = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(LAST_RECIPE_PLAN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    LastRecipePlan recipePlan = new LastRecipePlan();
                    recipePlan.setDayName(resultSet.getString("day_name"));
                    recipePlan.setMealName(resultSet.getString("meal_name"));
                    recipePlan.setRecipeName(resultSet.getString("recipe_name"));
                    recipePlan.setRecipeDescription(resultSet.getString("recipe_description"));
                    lastRecipePlan.add(recipePlan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastRecipePlan;

    }

    public List<LastRecipePlan> readRecipePlan(int id) {
        List<LastRecipePlan> res = new ArrayList<>();
        try (Connection connection = DBUtil2.connect()) {
            PreparedStatement statement = connection.prepareStatement(READ_RECIPE_PLAN);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LastRecipePlan recipePlan = new LastRecipePlan();
                recipePlan.setDayName(resultSet.getString("day_name"));
                recipePlan.setMealName(resultSet.getString("meal_name"));
                recipePlan.setRecipeName(resultSet.getString("recipe_name"));
                recipePlan.setRecipeDescription(resultSet.getString("recipe_description"));
                recipePlan.setRecipeId(resultSet.getInt("recipe_id"));
                res.add(recipePlan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }

}
