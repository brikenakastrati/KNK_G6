package service;

import Repository.Interface.inventoryRepositoryInterface;
import Repository.inventoryRepository;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Photo;
import model.carInventory;
import service.Interface.inventoryServiceInterface;

import java.sql.SQLException;
import java.util.Map;

public class CarsService implements inventoryServiceInterface {
    private inventoryRepositoryInterface inventoryRepo;

    public CarsService() {
        inventoryRepo = new inventoryRepository();
    }

    @Override
    public void fillCarsTable(TableView<carInventory> cartable) throws SQLException {
        this.inventoryRepo.getAllCars(cartable);
    }

    @Override
    public Map<String, Double> getMonthlyIncome() {
        return inventoryRepo.getMonthlyIncome();
    }

    @Override
    public void savePhotos(ObservableList<Photo> photos, String carId) throws SQLException {
        inventoryRepo.insertPhotoPaths(photos, carId);
    }
    @Override
    public void addCar(carInventory car) throws SQLException {
        inventoryRepo.addCar(car);
    }
    public void deleteCarAndPhotos(String carId) throws SQLException {
        inventoryRepo.deleteCar(carId);
    }
    public int countCarsInStock() throws SQLException{
        return inventoryRepo.countCarsInStock();
    }
    public ObservableList<carInventory> inventoryCarList() throws SQLException{
        return inventoryRepo.inventoryCarList();
    }

}