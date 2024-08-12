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

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductControllers implements Initializable {

    @FXML
    private TextField idTxt,nameTxt,priceTxt,countTxt;
    @FXML
    private ToggleGroup brandToggle;
    @FXML
    private RadioButton zaraBtn;
    @FXML
    private Button saveBtn,removeBtn,editeBtn;
    @FXML
    private TableView<Product>productTable;
    @FXML
    private TableColumn<Product,Integer> idCol, totalPriceCol;
    @FXML
    private TableColumn<Product,String> nameCol,brandCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

        saveBtn.setOnAction(event -> {

        try (ProductDa productDa = new ProductDa()) {
            RadioButton selectedRdo = (RadioButton) brandToggle.getSelectedToggle();
            Product product =
                    Product
                            .builder()
                            .name(validation.nameValidator(nameTxt.getText()))
                            .brand(Brand.valueOf(selectedRdo.getText()))
                            .price()
                            .count()
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
        try (ProductD productDa = new ProductDa()) {
            // Data Validation
            RadioButton selectedRdo = (RadioButton) brandToggle.getSelectedToggle();
            Product product =
                    Product
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(validation.nameValidator(nameTxt.getText()))
                            .family(validation.familyValidator(familyTxt.getText()))
                            .gender(Gender.valueOf(selectedRdo.getText()))
                            .birthDate(birthDate.getValue())

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

         editBtn.setOnAction(event -> {
        try (ProductDa productDa = new ProductDa()) {
            // Data Validation
            RadioButton selectedRdo = (RadioButton) brandToggle.getSelectedToggle();
            Product product =
                    product
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(validation.nameValidator(nameTxt.getText()))
                            .family(validation.familyValidator(familyTxt.getText()))
                            .gender(Gender.valueOf(selectedRdo.getText()))
                            .birthDate(birthDate.getValue())
                            .city(City.valueOf(cityCmb.getSelectionModel().getSelectedItem()))
                            .seSkill(seChk.isSelected())
                            .eeSkill(eeChk.isSelected())
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


          productTable.setOnMouseReleased(event->{
        Product product = productTable.getSelectionModel().getSelectedItem();
        idTxt.setText(String.valueOf(product.getId()));
        nameTxt.setText(product.getName());
        priceTxt.setText(String.valueOf(product.getPrice()));
        if(product.getBrand().equals(Brand.zara){
            zaraBtn.setSelected(true);
        }else {
            //fix it
            gucciBtn.setSelected(true);
        }

    });
}





        private void resetForm(){
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
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(products);
    }




    }
}
