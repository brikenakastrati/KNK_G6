package service;

import Repository.PurchasesRepository;
import model.Purchase;
import model.carInventory;
import model.filter.highPriceFilter;

import java.util.List;
import java.util.Map;

public class PurchasesService {
    private PurchasesRepository purchasesRepository = PurchasesRepository.getInstance();

    public void storePurchaseDetails(carInventory car, String account, String cardNumber, String cvv, String expirationDate) {
        purchasesRepository.storePurchaseDetails(car, account, cardNumber, cvv, expirationDate);
    }

        public List<Purchase> getAllPurchases() {
        return purchasesRepository.getAllPurchases();
    }
    public Map<String, Double> getMonthlyCarSales() {
        return purchasesRepository.getMonthlyCarSales();
    }

    public int getTotalNumberOfPurchases() {
        return purchasesRepository.getTotalNumberOfPurchases();
    }

    public List<Purchase> getPurchasesByUserId(int userId) {
        return purchasesRepository.getPurchasesByUserId(userId);
    }
    public List<Purchase> getPurchasesByFilter(highPriceFilter filter, int userId) {
        return purchasesRepository.getPurchasesByFilter(filter, userId);
    }

}
