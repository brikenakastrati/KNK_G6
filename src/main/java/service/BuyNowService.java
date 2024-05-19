package service;

import model.carInventory;

public class BuyNowService {
    private static BuyNowService instance;
    private carInventory selectedCar;

    public static BuyNowService getInstance() {
        if (instance == null) {
            instance = new BuyNowService();
        }
        return instance;
    }
    public void setSelectedCar(carInventory car) {
        this.selectedCar = car;
    }
    public carInventory getSelectedCar(){
        return selectedCar;
    }
}
