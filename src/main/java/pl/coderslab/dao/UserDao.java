package pl.coderslab.dao;

import pl.coderslab.model.User;
import pl.coderslab.utils.BCrypt;
import pl.coderslab.utils.DBUtil2;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.*;

public class UserDao {

    public static final String CREATE_USER_QUERY = "INSERT INTO admins ( `first_name`, `last_name`, `email`, `password`, `superadmin`,`enable`) VALUES (?,?,?,?,?,?)";
    public static final String DELETE_USER_QUERY = "DELETE FROM admins where id = ?;";
    public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM admins;";
    public static final String READ_USER_QUERY = "SELECT * FROM admins where id = ?;";
    public static final String UPDATE_USER_QUERY = "UPDATE admins SET first_name = ? , last_name = ? , email = ? , password = ? , superadmin = ?, enable = ? where id = ?";
    public static final String UPDATE_USER_PASSWORD_QUERY = "UPDATE admins SET password = ? where id = ?";

    public static String hashPassword(String password) {
        return pl.coderslab.utils.BCrypt.hashpw(password, pl.coderslab.utils.BCrypt.gensalt());
    }

    /**
     * Create user
     *
     * @param user
     */
    public static User create(User user) {
        try (Connection connection = DbUtil.getConnection();

             PreparedStatement insertStm = connection.prepareStatement(CREATE_USER_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, user.getFirstName());
            insertStm.setString(2, user.getLastName());
            insertStm.setString(3, user.getEmail());
            insertStm.setString(4, hashPassword(user.getPassword()));
            insertStm.setString(5, String.valueOf(user.getSuperAdmin()));
            insertStm.setString(6, String.valueOf(user.getEnable()));
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    user.setId(generatedKeys.getInt(1));
                    return user;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get user by id
     *
     * @param userId
     * @return
     */
    public User read(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement stat = connection.prepareStatement(READ_USER_QUERY);
            stat.setInt(1, userId);
            ResultSet resultSet = stat.executeQuery();
            User user = new User();

            if (resultSet.next()) {

                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setSuperAdmin(Integer.parseInt(resultSet.getString("superadmin")));
                user.setEnable(Integer.parseInt(resultSet.getString("enable")));

            }
            if (user.getId() == 0) {

                return null;
            }
            return user;

        } catch (SQLException throwables) {

            throwables.printStackTrace();
            return null;
        }

    }


    /**
     * Update user
     *
     * @param user
     */

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_QUERY);


            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, hashPassword(user.getPassword()));
            statement.setInt(5, user.getSuperAdmin());
            statement.setInt(6, user.getEnable());
            statement.setInt(7, user.getId());
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete user by id
     *
     * @param id
     */
    public void delete(Integer id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all users
     *
     * @return
     */
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setSuperAdmin(resultSet.getInt("superadmin"));
                user.setEnable(resultSet.getInt("enable"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public boolean adminCheck(String email, String password) {
        boolean check = false;
        for (User u : findAll()) {
            if (u.getEmail().equals(email) & BCrypt.checkpw(password, u.getPassword())) {
                check = true;
            }
        }
        return check;
    }


    public void updatePass(User user, String password) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(UPDATE_USER_PASSWORD_QUERY);
            statement.setString(1, hashPassword(password));
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
