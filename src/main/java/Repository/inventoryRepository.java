package Repository;

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

    public ObservableList<carInventory> inventoryCarList()  {
        ObservableList<carInventory> listData= FXCollections.observableArrayList();
        String query="SELECT * FROM inventory";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet result = pst.executeQuery();
            carInventory carInv;
            while(result.next()){
                carInv=new carInventory(result.getInt("id"),result.getString("carid"),
                        result.getString("carname"),
                        result.getString("cartype"),
                        result.getInt("carstock"),
                        result.getDouble("carprice"),


                        result.getString("carstatus"),

                        result.getString("carimage"));
listData.add(carInv);
            }
        }
        catch (SQLException e){
e.printStackTrace();
        }
        return listData;
    }
    public ObservableList<carInventory> inventoryListData;
    public void inventoryShowData(){
        inventoryListData=inventoryCarList();
        invColCarID.setCellValueFactory(new PropertyValueFactory<>("carid"));
        invColCarName.setCellValueFactory(new PropertyValueFactory<>("carname"));
        invColCarType.setCellValueFactory(new PropertyValueFactory<>("cartype"));
        invColStock.setCellValueFactory(new PropertyValueFactory<>("carstock"));
        invColPrice.setCellValueFactory(new PropertyValueFactory<>("carprice"));

        invColStatus.setCellValueFactory(new PropertyValueFactory<>("carstatus"));
        inventoryTable.setItems(inventoryListData);
    }
}



