package Repository.Interface;

import javafx.scene.control.TableView;
import model.User;

import java.sql.SQLException;
import java.util.Map;

public interface UserRepositoryInterface {
    public User getAllUsers(TableView<User> tbl, Boolean is_admin) throws SQLException;
    public void deleteUser(int id) throws SQLException;
    int countUsers() throws SQLException;
    Map<String, Integer> getMonthlyRegistrations();
}

