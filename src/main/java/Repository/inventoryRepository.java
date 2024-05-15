package Repository;

import database.DatabaseUtil;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.carInventory;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class inventoryRepository {
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
        ObservableList<carInventory> listData = FXCollections.observableArrayList();
    Connection connection = DBConnector.getConnection();

            PreparedStatement pst = connection.prepareStatement("SELECT * FROM inventory");
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                carInventory carInv = new carInventory(
                        result.getString("carid"),
                        result.getString("carname"),
                        result.getString("cartype"),
                        result.getInt("carstock"),
                        result.getDouble("carprice"),
                        result.getString("carstatus"),
                        result.getString("carimage"));
                listData.add(carInv);
            }


        return listData;
    }



    public ObservableList<carInventory> inventoryListData;




    public void addCar(carInventory newCar) {
        Connection connection = DBConnector.getConnection();

            String query = "INSERT INTO inventory (carid, carname, cartype, carstock, carprice, carstatus, carimage) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, newCar.getCarid());
                pst.setString(2, newCar.getCarname());
                pst.setString(3, newCar.getCartype());
                pst.setInt(4, newCar.getCarstock());
                pst.setDouble(5, newCar.getCarprice());
                pst.setString(6, newCar.getCarstatus());
                pst.setString(7, newCar.getCarimage());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}


