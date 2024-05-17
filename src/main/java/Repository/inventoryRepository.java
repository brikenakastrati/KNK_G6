package Repository;

import Repository.Interface.inventoryRepositoryInterface;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.carInventory;
import service.DBConnector;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class inventoryRepository implements inventoryRepositoryInterface {
    @FXML
    private TableColumn<carInventory, String> invColCarID;

    @FXML
    private TableColumn<carInventory, String> invColCarName;

    @FXML
    private TableColumn<carInventory, String> invColCarType;

    @FXML
    private TableColumn<carInventory, String> invColPrice;

    @FXML
    private TableColumn<carInventory, String> invColStatus;

    @FXML
    private TableColumn<carInventory, String> invColStock;
    @FXML
    private TableView<carInventory> inventoryTable;

    public ObservableList<carInventory> inventoryCarList() throws SQLException {
        ObservableList<carInventory> carList = FXCollections.observableArrayList();
        String query = "SELECT * FROM inventory";
        Connection connection = DBConnector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                carInventory car = new carInventory(
                        resultSet.getString("carid"),
                        resultSet.getString("carname"),
                        resultSet.getString("cartype"),
                        resultSet.getInt("carstock"),
                        resultSet.getDouble("carprice"),
                        resultSet.getString("carstatus"),
                        resultSet.getString("carimage"),
                        resultSet.getTimestamp("dateAdded")
                );
                carList.add(car);
            }
        }
        return carList;
    }

    public void addCar(carInventory car) throws SQLException {
        String query = "INSERT INTO inventory (carid, carname, cartype, carstock, carprice, carstatus, carimage, dateAdded) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DBConnector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.getCarid());
            statement.setString(2, car.getCarname());
            statement.setString(3, car.getCartype());
            statement.setInt(4, car.getCarstock());
            statement.setDouble(5, car.getCarprice());
            statement.setString(6, car.getCarstatus());
            statement.setString(7, car.getCarimage());
            statement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
        }
    }
    public Map<String, Double> getMonthlyIncome() throws SQLException {
        Map<String, Double> monthlyIncome = new HashMap<>();
        Connection connection = DBConnector.getConnection();

        String query = "SELECT DATE_FORMAT(dateAdded, '%Y-%m') as month, SUM(carprice) as income FROM inventory GROUP BY month ORDER BY month";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String month = resultSet.getString("month");
                double income = resultSet.getDouble("income");
                monthlyIncome.put(month, income);
            }
        }

        return monthlyIncome;
    }
    public int countCarsInStock() throws SQLException {
        String sql = "SELECT SUM(carstock) as totalStock FROM inventory";
        Connection connection = DBConnector.getConnection();
        try(
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet rs =  pst.executeQuery()
                ){
            if (rs.next()) {
                return rs.getInt("totalStock");
            }

        }catch (SQLException se) {
            System.out.println("Error : " + se.getMessage());
        }
        return 0;
    }
    public void deleteCar(String carId) throws SQLException {
        String query = "DELETE FROM inventory WHERE carid = ?";
        Connection connection = DBConnector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, carId);
            statement.executeUpdate();
        }
    }

    public carInventory getAllCars(TableView<carInventory> cartbl) throws SQLException {
        String sql = "SELECT carid, carname, cartype, carstock, carprice, carstatus, carimage, dateAdded FROM inventory";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                String carid = resultSet.getString("carid");
                String carname = resultSet.getString("carname");
                String cartype = resultSet.getString("cartype");
                int stock = resultSet.getInt("carstock");
                double carprice = resultSet.getDouble("carprice");
                String carstatus = resultSet.getString("carstatus");
                String carimage = resultSet.getString("carimage");
                Timestamp dateAdded = resultSet.getTimestamp("dateAdded");
                carInventory carinv = new carInventory(carid, carname, cartype, stock, carprice, carstatus, carimage,dateAdded);
                cartbl.getItems().add(carinv);
            }
        } catch (SQLException se) {
            System.out.println("Error me i marr qeto: " + se.getMessage());
        }
        return null;
    }
}


