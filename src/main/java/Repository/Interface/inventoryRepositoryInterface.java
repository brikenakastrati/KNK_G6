package Repository.Interface;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Photo;
import model.User;
import model.carInventory;

import java.sql.SQLException;
import java.util.Map;

public interface inventoryRepositoryInterface {
    public carInventory getAllCars(TableView<carInventory> cartable) throws SQLException;

    Map<String, Double> getMonthlyIncome();

    void insertPhotoPaths(ObservableList<Photo> photos, String carId) throws SQLException;

    void addCar(carInventory car) throws SQLException;

    void deleteCar(String carId) throws SQLException;
}
