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

    public UserDetail getUserDetail(String username) {
        String query = "SELECT * FROM userDetail WHERE accountUserName = ?";

        try (Connection connection = MySQLConfig.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                        String firstName = resultSet.getString("firstname");
                        String lastName = resultSet.getString("lastname");
                        String email = resultSet.getString("email");
                        return new UserDetail(firstName, lastName, email,null);
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
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                userDetailList.add(new UserDetail(firstName, lastName, email,null));
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
