package sixth.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sixth.model.da.ProductDa;
import sixth.model.entity.Brand;
import sixth.model.entity.Product;
import sixth.model.utils.Validation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductControllers implements Initializable {
    private Validation validation = new Validation();

    @FXML
    private TextField idTxt, nameTxt, priceTxt, countTxt;
    @FXML
    private ToggleGroup brandToggle;
    @FXML
    private RadioButton zaraBtn, louseVuittonBtn, nikeBtn, gucciBtn;
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idCol, priceCol, countCol;
    @FXML
    private TableColumn<Product, String> nameCol, brandCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        saveBtn.setOnAction(event -> {
            try (ProductDa productDa = new ProductDa()) {
                RadioButton selectedRdo = (RadioButton) brandToggle.getSelectedToggle();
                Product product =
                        Product
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .name(validation.nameValidator(nameTxt.getText()))
                                .brand(Brand.valueOf(selectedRdo.getText()))
                                .price(Integer.parseInt(priceTxt.getText()))
                                .count(Integer.parseInt(countTxt.getText()))
                                .build();
                productDa.save(product);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "product Saved\n" + product.toString());
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "product Save Error\n" + e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction(event -> {
            try (ProductDa productDa = new ProductDa()) {
                // Data Validation
                RadioButton selectedRdo = (RadioButton) brandToggle.getSelectedToggle();
                Product product =
                        Product
                                .builder()
                                .id(Integer.parseInt(idTxt.getText()))
                                .name(validation.nameValidator(nameTxt.getText()))
                                .brand(Brand.valueOf(selectedRdo.getText()))
                                .price(Integer.parseInt(priceTxt.getText()))
                                .count(Integer.parseInt(countTxt.getText()))
                                .build();
                productDa.edit(product);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product Edited\n" + product.toString());
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Product Edit Error\n" + e.getMessage());
                alert.show();
            }
        });

        productTable.setOnMouseReleased(event -> {
            Product product = productTable.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(product.getId()));
            nameTxt.setText(product.getName());
            if (product.getBrand().equals(Brand.zara)) {
                zaraBtn.setSelected(true);
            }
            if (product.getBrand().equals(Brand.gucci)) {
                gucciBtn.setSelected(true);
            }
            if (product.getBrand().equals(Brand.louise_vuitton)) {
                louseVuittonBtn.setSelected(true);
            }
            if (product.getBrand().equals(Brand.nike)) {
                nikeBtn.setSelected(true);
            }
            priceTxt.setText(String.valueOf(product.getPrice()));
            countTxt.setText(String.valueOf(product.getCount()));
        });
    }

    private void resetForm() {
        idTxt.clear();
        nameTxt.clear();
        priceTxt.clear();
        countTxt.clear();

        zaraBtn.setSelected(true);

        try (ProductDa productDa = new ProductDa()) {
            refreshTable(productDa.findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Find products Error\n" + e.getMessage());
            alert.show();
        }
    }

    private void refreshTable(List<Product> productList) {
        ObservableList<Product> products = FXCollections.observableList(productList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(products);
    }
}
