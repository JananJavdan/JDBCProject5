package jdbc.repository;

import jdbc.config.MySQLConfig;
import jdbc.model.Account;
import jdbc.model.UserDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDetailRepository {
    public void addUserDetail(UserDetail userDetail) {
        String query = "INSERT INTO userdetail (firstName, lastName, email, accountUsername)" +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = MySQLConfig.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userDetail.getFirstName());
            statement.setString(2, userDetail.getLastName());
            statement.setString(3, userDetail.getEmail());
            statement.setString(4, userDetail.getAccount().getUsername());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDetail getUserDetail(Account account) {
        String query = "SELECT * FROM userDetail WHERE accountUerName=" + account.getUsername();
        try (Connection connection = MySQLConfig.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return new UserDetail(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        (Account) resultSet.getObject("accountUserName")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserDetail> getAllUserDetail() {
        List<UserDetail> userDetailList = new ArrayList<>();

        String query = "SELECT * FROM userDetail";

        try (Connection connection = MySQLConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                userDetailList.add(new UserDetail(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        (Account) resultSet.getObject("accountUserName"))
                );
            }
            if (userDetailList.size()>0)
                return userDetailList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    public void deleteUserDetail(int userId) {
        String query = "DELETE FROM userDetail WHERE userId = ?";
        try (Connection connection = MySQLConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
