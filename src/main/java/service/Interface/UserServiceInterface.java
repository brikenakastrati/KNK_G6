package service.Interface;

import javafx.scene.control.TableView;
import model.User;

import java.sql.SQLException;
import java.util.Map;

public interface UserServiceInterface {

    void fillUserTable(TableView<User> tbl, Boolean is_admin) throws SQLException;
    int countUsers() throws SQLException;
    void deleteUser(int id) throws SQLException;

    Map<String, Integer> getMonthlyRegistrations();
}
