package service.Interface;

import javafx.scene.control.TableView;
import model.carInventory;

import java.sql.SQLException;

public interface inventoryServiceInterface {
    void fillCarsTable(TableView<carInventory> cartable) throws SQLException;
}
