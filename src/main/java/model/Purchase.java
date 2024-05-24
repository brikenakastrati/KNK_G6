package model;

import java.time.LocalDateTime;

public class Purchase {
    private String carName;
    private double carPrice;
    private String buyerName;
    private LocalDateTime purchaseDate;
    private int carStock;

    public int getCarStock() {
        return carStock;
    }
    public void setCarStock(int carStock) {
        this.carStock = carStock;
    }

    public String getCarName() {
        return carName;
    }
    public void setCarName(String carName) {
        this.carName = carName;
    }
    public double getCarPrice() {
        return carPrice;
    }
    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
