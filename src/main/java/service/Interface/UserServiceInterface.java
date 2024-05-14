package service.Interface;

import javafx.scene.control.TableView;
import model.User;

import java.sql.SQLException;

public interface UserServiceInterface {

    void fillUserTable(TableView<User> tbl, Boolean is_admin) throws SQLException;
}
