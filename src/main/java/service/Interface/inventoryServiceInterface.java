package service.Interface;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Photo;
import model.carInventory;

import java.sql.SQLException;
import java.util.Map;

public interface inventoryServiceInterface {
    void fillCarsTable(TableView<carInventory> cartable) throws SQLException;
    Map<String, Double> getMonthlyIncome();
    public void savePhotos(ObservableList<Photo> photos, String carId) throws SQLException;

   public void addCar(carInventory car) throws SQLException;

    int countCarsInStock()throws SQLException;
    public ObservableList<carInventory> inventoryCarList() throws SQLException;
}
