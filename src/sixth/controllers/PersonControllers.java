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
    private TextField usernameTxt, passwordTxt, nameTxt, familyTxt;
    @FXML
    private ToggleGroup genderToggle;
    @FXML
    private RadioButton maleBtn, femaleBtn;
    @FXML
    private DatePicker birthDate;

    @FXML
    private Button saveBtn, editBtn, removeBtn, loginBtn;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, Integer> idCol;
    @FXML
    private TableColumn<Person, String> nameCol, familyCol, genderCol;
    @FXML
    private TableColumn<Person, LocalDate> birthDateCol;

    private Person loggedInPerson;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();

        loginBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                String username = usernameTxt.getText();
                String password = passwordTxt.getText();

                loggedInPerson = personDa.findByUsernameAndPassword(username, password);
                if (loggedInPerson != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Successful\n" + loggedInPerson);
                    alert.show();
                    loadPersonDetails(loggedInPerson);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username or Password");
                    alert.show();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Login Error\n" + e.getMessage());
                alert.show();
            }
        });

        saveBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                RadioButton selectedRdo = (RadioButton) genderToggle.getSelectedToggle();
                Person person =
                        Person
                                .builder()
                                .username(usernameTxt.getText())
                                .password(passwordTxt.getText())
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
                RadioButton selectedRdo = (RadioButton) genderToggle.getSelectedToggle();
                loggedInPerson.setName(validation.personNameValidator(nameTxt.getText()));
                loggedInPerson.setFamily(validation.familyValidator(familyTxt.getText()));
                loggedInPerson.setGender(Gender.valueOf(selectedRdo.getText()));
                loggedInPerson.setBirthDate(birthDate.getValue());

                personDa.edit(loggedInPerson);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Edited\n" + loggedInPerson);
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Edit Error\n" + e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction(event -> {
            try (PersonDa personDa = new PersonDa()) {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure To Remove Person?");
                if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                    personDa.remove(String.valueOf(loggedInPerson));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Removed With Username : " + loggedInPerson.getUsername());
                    alert.show();
                    resetForm();
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Person Remove Error\n" + e.getMessage());
                alert.show();
            }
        });

        personTable.setOnMouseReleased(event -> {
            Person person = personTable.getSelectionModel().getSelectedItem();
            loadPersonDetails(person);
        });
    }

    private void loadPersonDetails(Person person) {
        usernameTxt.setText(person.getUsername());
        passwordTxt.setText(person.getPassword());
        nameTxt.setText(person.getName());
        familyTxt.setText(person.getFamily());
        if (person.getGender().equals(Gender.male)) {
            maleBtn.setSelected(true);
        } else {
            femaleBtn.setSelected(true);
        }
        birthDate.setValue(person.getBirthDate());
    }

    private void resetForm() {
        usernameTxt.clear();
        passwordTxt.clear();
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
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        personTable.setItems(persons);
    }
}
