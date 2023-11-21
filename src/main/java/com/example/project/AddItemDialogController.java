package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemDialogController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private HelloController mainController;

    private boolean editMode;

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        okButton.setDisable(true);
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.isEmpty() || !isValidPhoneNumber(phoneField.getText()));
        });
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(nameField.getText().isEmpty() || !isValidPhoneNumber(newValue));
        });
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^(\\+?380\\d{9}|0\\d{9})$";
        return phoneNumber.matches(regex);
    }

    @FXML
    private void handleOkButtonAction() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        if (!name.isEmpty()) {
            if (editMode) {
                User editedUser = new User(name, phone);
                mainController.editSelectedItem(editedUser);
            } else {
                User newUser = new User(name, phone);
                mainController.addItem(newUser);
            }
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void handleCancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setEditMode(boolean editMode, User user) {
        if (editMode && user != null) {
            nameField.setText(user.getName());
            phoneField.setText(user.getPhone());
        }
    }
}