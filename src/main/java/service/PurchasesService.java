package service;

import Repository.PurchasesRepository;
import model.Purchase;

import java.util.List;
import java.util.Map;

public class PurchasesService {
    private PurchasesRepository purchasesRepository = PurchasesRepository.getInstance();

    public List<Purchase> getAllPurchases() {
        return purchasesRepository.getAllPurchases();
    }
    public Map<String, Double> getMonthlyCarSales() {
        return purchasesRepository.getMonthlyCarSales();
    }
}
