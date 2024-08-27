package sixth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sixth.model.da.ProductDa;
import sixth.model.entity.Brand;
import sixth.model.entity.Product;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class SellControllers  implements Initializable {
    @FXML
    private TextField idTxt, nameTxt, familyTxt, productTxt;
    @FXML
    private DatePicker sellTimePicker;
    @FXML
    private TableView<Product> mainTable;
    @FXML
    private TableColumn<Product, Integer> idCol;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, String> familyCol;
    @FXML
    private TableColumn<Product, String> productCol;
    @FXML
    private TableColumn<Product, Timestamp> sellTimeCol;
    @FXML
    private Button saveBtn, editBtn, removeBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}



