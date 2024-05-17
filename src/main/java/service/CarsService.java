package service;

import Repository.Interface.inventoryRepositoryInterface;
import Repository.inventoryRepository;
import javafx.scene.control.TableView;
import model.carInventory;
import service.Interface.inventoryServiceInterface;

import java.sql.SQLException;

public class CarsService implements inventoryServiceInterface {
    private inventoryRepositoryInterface inventoryRepository;
    public CarsService(){
        this.inventoryRepository = new inventoryRepository();
    }
    @Override
    public void fillCarsTable(TableView<carInventory> cartable) throws SQLException {
        this.inventoryRepository.getAllCars(cartable);
    }
}
