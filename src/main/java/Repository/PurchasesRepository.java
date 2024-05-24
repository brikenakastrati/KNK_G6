package Repository;

import model.Purchase;
import model.User;
import model.carInventory;
import model.filter.highPriceFilter;
import service.DBConnector;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchasesRepository {
    private static PurchasesRepository instance;
    private UserService userService = new UserService();

    private PurchasesRepository() {}

    public static PurchasesRepository getInstance() {
        if (instance == null) {
            instance = new PurchasesRepository();
        }
        return instance;
    }

    public void storePurchaseDetails(carInventory car, String account, String cardNumber, String cvv, String expirationDate) {

        User currentUser = userService.getUserByUsername(UserService.getUsername());

        String sql = "INSERT INTO CarPurchases (car_id, car_name, car_price, buyer_id, buyer_name, card_number, cvv, expiration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, car.getCarid());
            pstmt.setString(2, car.getCarname());
            pstmt.setDouble(3, car.getCarprice());
            pstmt.setInt(4, currentUser.getId());
            pstmt.setString(5, account);
            pstmt.setString(6, cardNumber);
            pstmt.setString(7, cvv);
            pstmt.setString(8, expirationDate);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Purchase> getPurchasesByUserId(int userId) {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT car_name, car_price, buyer_name, purchase_date FROM CarPurchases WHERE buyer_id = ?";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1,userId);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setCarName(rs.getString("car_name"));
                purchase.setCarPrice(rs.getDouble("car_price"));
                purchase.setBuyerName(rs.getString("buyer_name"));
                purchase.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                purchases.add(purchase);
            }

        }catch (SQLException se) {
            se.getMessage();
        }
        return purchases;
    }

    public List<Purchase> getPurchasesByFilter(highPriceFilter filter , int userId) {
        List<Purchase> purchases = new ArrayList<>();
        String sql1 = "SELECT car_name, car_price, buyer_name, purchase_date FROM CarPurchases WHERE buyer_id = ?";
        String sql = sql1 + " " + filter.buildQuery();
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1,userId);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setCarName(rs.getString("car_name"));
                purchase.setCarPrice(rs.getDouble("car_price"));
                purchase.setBuyerName(rs.getString("buyer_name"));
                purchase.setPurchaseDate(rs.getTimestamp("purchase_date").toLocalDateTime());
                purchases.add(purchase);
            }

        }catch (SQLException se) {
            se.getMessage();
        }
        return purchases;
    }
    public List<Purchase> getAllPurchases(){
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT p.car_name, p.car_price, p.buyer_name, p.purchase_date, i.carstock " + "FROM CarPurchases p JOIN Inventory i ON p.car_id = i.carid";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)
        ){
            ResultSet rst = pst.executeQuery();

            while(rst.next()) {
                Purchase purchase = new Purchase();
                purchase.setCarName(rst.getString("car_name"));
                purchase.setCarPrice(rst.getDouble("car_price"));
                purchase.setBuyerName(rst.getString("buyer_name"));
                purchase.setPurchaseDate(rst.getTimestamp("purchase_date").toLocalDateTime());
                purchase.setCarStock(rst.getInt("carstock"));
                purchases.add(purchase);
            }

        }catch (SQLException se) {
            se.printStackTrace();
        }
        return purchases;
    }

}
