package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private Stage addItemStage;
    @FXML
    private AddItemDialogController addItemController;

    @FXML
    private Label recordCountLabel;

    private ObservableList<User> originalUserList;


    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        nameColumn.getStyleClass().add("table-cell");
        phoneColumn.getStyleClass().add("table-cell");
    }

    @FXML
    private void handleSearchButtonAction() {
        String searchQuery = searchTextField.getText().toLowerCase();
        if (!searchQuery.isEmpty()) {
            ObservableList<User> filteredList = FXCollections.observableArrayList();
            for (User user : tableView.getItems()) {
                if (user.getName().toLowerCase().contains(searchQuery)) {
                    filteredList.add(user);
                }
            }
            tableView.setItems(filteredList);
        } else {
            tableView.setItems(originalUserList);
        }
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modal.fxml"));
        Parent root = loader.load();
        addItemController = loader.getController();
        addItemController.setMainController(this);
        addItemStage = new Stage();
        addItemStage.initModality(Modality.WINDOW_MODAL);
        addItemStage.initOwner(((Button) event.getSource()).getScene().getWindow());
        addItemStage.setScene(new Scene(root));
        addItemStage.setResizable(false);
        addItemStage.showAndWait();
    }

    @FXML
    private void handleEditButtonAction() throws IOException {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modal.fxml"));
            Parent root = loader.load();
            AddItemDialogController addItemController = loader.getController();
            addItemController.setMainController(this);
            addItemController.setEditMode(true, selectedUser);
            addItemController.setEditMode(true);
            Stage addItemStage = new Stage();
            addItemStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = tableView.getScene();
            addItemStage.initOwner(scene.getWindow());
            addItemStage.setScene(new Scene(root));
            addItemStage.setResizable(false);
            addItemStage.showAndWait();
        }
    }

    @FXML
    private void handleQuizButtonAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("quiz.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Quiz Window");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleDeleteButtonAction() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            tableView.getItems().remove(selectedUser);
            setRecordCount(tableView.getItems().size());
        }
    }

    public void setRecordCount(int count) {
        recordCountLabel.setText(String.valueOf(count));
    }

    public void addItem(User user) {
        if (originalUserList == null) {
            originalUserList = FXCollections.observableArrayList();
            tableView.setItems(originalUserList);
        }

        originalUserList.add(user);
        setRecordCount(originalUserList.size());
    }

    public void editSelectedItem(User editedUser) {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            tableView.getItems().remove(selectedUser);
            tableView.getItems().add(editedUser);
            setRecordCount(tableView.getItems().size());
        }
    }
}
