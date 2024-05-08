package Repository;

import database.DatabaseUtil;
import model.User;
import model.dto.CreateUserDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

    public static boolean create(CreateUserDto userData){
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO USER (username, email, salt, passwordHash,isAdmin)
                VALUE (?, ?, ?, ?,?)
                """;
        //String query = "INSERT INTO USER VALUE (?, ?, ?, ?, ?)";
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, userData.getUsername());
            pst.setString(2, userData.getEmail());
            pst.setString(3, userData.getSalt());
            pst.setString(4, userData.getPasswordHash());
            pst.setBoolean(5,userData.get_admin_status());
            pst.execute();
            pst.close();
            conn.close();
            return true;
        }catch (Exception e){
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

    private static User getFromResultSet(ResultSet result){
        try{
            int id = result.getInt("id");
            String username = result.getString("username");
            String email = result.getString("email");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            boolean isAdmin=result.getBoolean("isAdmin");
            return new User(
                    id, username, email, salt, passwordHash,isAdmin
            );
        }catch (Exception e){
            return null;
        }
    }

}
