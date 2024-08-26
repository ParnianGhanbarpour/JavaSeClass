package sixth.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sixth.model.da.PersonDa;
import sixth.model.entity.Gender;
import sixth.model.entity.Person;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import sixth.model.utils.Validation;


import java.time.LocalDate;
import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonControllers implements Initializable {
    private final Validation validation = new Validation();

    @FXML
    private TextField idTxt ,nameTxt ,familyTxt ;
    @FXML
    private ToggleGroup genderToggle;
    @FXML
    private RadioButton maleBtn, femaleBtn ;
    @FXML
    private DatePicker birthDate;

    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Integer> idCol;
    @FXML
    private TableColumn<Person, String> nameCol, familyCol ,genderCol;
    @FXML
    private  TableColumn<Person,DatePicker>birthDateCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      resetForm();

    saveBtn.setOnAction(event -> {
        try (PersonDa personDa = new PersonDa()) {
            RadioButton selectedRdo = (RadioButton) genderToggle.getSelectedToggle();
            Person person =
                    Person
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(validation.personNameValidator(nameTxt.getText()))
                            .family(validation.familyValidator(familyTxt.getText()))
                            .gender(Gender.valueOf(selectedRdo.getText()))
                            .birthDate(birthDate.getValue())

                            .build();
            personDa.save(person);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Saved\n" + person);
            alert.show();
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Person Save Error\n" + e.getMessage());
            alert.show();
        }
    });
    editBtn.setOnAction(event -> {
        try (PersonDa personDa = new PersonDa()) {
            // Data Validation
            RadioButton selectedRdo = (RadioButton) genderToggle.getSelectedToggle();
            Person person =
                    Person
                            .builder()
                            .id(Integer.parseInt(idTxt.getText()))
                            .name(validation.personNameValidator(nameTxt.getText()))
                            .family(validation.familyValidator(familyTxt.getText()))
                            .gender(Gender.valueOf(selectedRdo.getText()))
                            .birthDate(birthDate.getValue())
                            .build();
            personDa.edit(person);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Edited\n" + person);
            alert.show();
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Person Edit Error\n" + e.getMessage());
            alert.show();
        }
    });
    removeBtn.setOnAction(event -> {
        try (PersonDa personDa = new PersonDa()) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure To Remove Person ?");
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                int id = Integer.parseInt(idTxt.getText());
                personDa.remove(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Removed With ID : " + id);
                alert.show();
                resetForm();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Person Remove Error\n" + e.getMessage());
            alert.show();
        }
    });
     personTable.setOnMouseReleased(event->{
        Person person = personTable.getSelectionModel().getSelectedItem();
        idTxt.setText(String.valueOf(person.getId()));
        nameTxt.setText(person.getName());
        familyTxt.setText(person.getFamily());
        if(person.getGender().equals(Gender.male)){
            maleBtn.setSelected(true);
        }else {
            femaleBtn.setSelected(true);
        }
        birthDate.setValue(person.getBirthDate());

     });
    }
    private void resetForm() {
        idTxt.clear();
        nameTxt.clear();
        familyTxt.clear();


        birthDate.setValue(LocalDate.now());

        femaleBtn.setSelected(true);

        try (PersonDa personDa = new PersonDa()) {
            refreshTable(personDa.findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Find Persons Error\n" + e.getMessage());
            alert.show();
        }
    }
    private void refreshTable(List<Person> personList) {
        ObservableList<Person> persons = FXCollections.observableList(personList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birth Date"));

        personTable.setItems(persons);
    }


}



