package service;

import Repository.inventoryRepository;
import model.carInventory;

import java.sql.SQLException;

public class BuyNowService {
    private static BuyNowService instance = new BuyNowService();
    private carInventory selectedCar;
    private inventoryRepository inventoryRepo = new inventoryRepository();

    public static BuyNowService getInstance() {
        if (instance == null) {
            instance = new BuyNowService();
        }
        return instance;
    }

    public void decreaseStock(String carid) {
        try {
            inventoryRepo.decreaseStock(carid);
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void setSelectedCar(carInventory car) {
        this.selectedCar = car;
    }
    public carInventory getSelectedCar(){
        return selectedCar;
    }
}
