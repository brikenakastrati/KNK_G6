package Repository;

import Repository.Interface.UserRepositoryInterface;
import javafx.scene.control.TableView;
import model.User;
import model.dto.CreateUserDto;
import service.DBConnector;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserRepositoryInterface {

    public static boolean create(CreateUserDto userData) {
        Connection conn = DBConnector.getConnection();
        String query = """
            INSERT INTO USER (username, email, salt, passwordHash, isAdmin, dateJoined)
            VALUES (?, ?, ?, ?, ?, ?)
            """;
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getUsername());
            pst.setString(2, userData.getEmail());
            pst.setString(3, userData.getSalt());
            pst.setString(4, userData.getPasswordHash());
            pst.setBoolean(5, userData.get_admin_status());
            pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

            pst.execute();
            pst.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static User getByUsername(String username){
        String query = "SELECT * FROM USER WHERE username = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public static User getByEmail(String email){
        String query = "SELECT * FROM USER WHERE email = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }


    private static User getFromResultSet(ResultSet result) {
        try {
            int id = result.getInt("id");
            String username = result.getString("username");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            boolean isAdmin = result.getBoolean("isAdmin");
            Timestamp dateJoined = result.getTimestamp("dateJoined");  // New column
            return new User(id, username, email, salt, passwordHash, isAdmin, dateJoined);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public User getAllUsers(TableView<User> tbl, Boolean is_admin) throws SQLException{
        String sql ="Select * from user where isadmin = ?";
        try(Connection connection = DBConnector.getConnection();
        PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setBoolean(1,is_admin);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String salt = resultSet.getString("salt");
                String passwordHash = resultSet.getString("passwordHash");
                Boolean is_admins = resultSet.getBoolean("isadmin");
                Timestamp dateJoined = resultSet.getTimestamp("dateJoined");  // New column
                User user = new User(id, username, email, salt, passwordHash, is_admins, dateJoined);
                tbl.getItems().add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
        return null;
    }
    @Override
    public void deleteUser(int id) throws SQLException{
        String sql = "DELETE FROM user WHERE id = ?";
        try(Connection connection = DBConnector.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);)
        {
            pst.setInt(1,id);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No user found with the provided id: " + id);
            } else {
                System.out.println("User with id " + id + " deleted successfully.");
            }
        }catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return;
    }

    @Override
    public int countUsers() throws SQLException {
        String sql = "Select COUNT(*) as total from user where isadmin = 0";
        try(
                Connection connection = DBConnector.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
            return 0;
        }catch (SQLException e) {
            System.out.println("Error me count usera : " + e.getMessage());
        }
        return 0;
    }

    public Map<String, Integer> getMonthlyRegistrations() {
        String query = "SELECT DATE_FORMAT(dateJoined, '%Y-%m') AS month, COUNT(*) AS registrations " +
                "FROM user " +
                "GROUP BY month";
        Map<String, Integer> monthlyRegistrations = new HashMap<>();
Connection conn=DBConnector.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String month = resultSet.getString("month");
                int registrations = resultSet.getInt("registrations");
                if (month != null && !month.isEmpty()) {
                    monthlyRegistrations.put(month, registrations);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return monthlyRegistrations;
    }
}
