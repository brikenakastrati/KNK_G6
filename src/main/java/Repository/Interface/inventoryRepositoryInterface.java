package Repository.Interface;

import javafx.scene.control.TableView;
import model.User;
import model.carInventory;

import java.sql.SQLException;

public interface inventoryRepositoryInterface {
    public carInventory getAllCars(TableView<carInventory> cartbl) throws SQLException;

}
