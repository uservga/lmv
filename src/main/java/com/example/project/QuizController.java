package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class QuizController {

    @FXML
    private CheckBox blackCheckBox;

    @FXML
    private CheckBox whiteCheckBox;

    @FXML
    private CheckBox yellowCheckBox;

    @FXML
    private CheckBox redCheckBox;

    @FXML
    private Text answerTextColor;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Text answerTextStudy;

    @FXML
    private RadioButton audiRadioButton;
    @FXML
    private RadioButton bmwRadioButton;
    @FXML
    private RadioButton nissanRadioButton;
    @FXML
    private RadioButton volkswagenRadioButton;

    @FXML
    private Text answerTextCar;

    private ToggleGroup carToggleGroup;

    @FXML
    public void initialize() {
        carToggleGroup = new ToggleGroup();
        audiRadioButton.setToggleGroup(carToggleGroup);
        bmwRadioButton.setToggleGroup(carToggleGroup);
        nissanRadioButton.setToggleGroup(carToggleGroup);
        volkswagenRadioButton.setToggleGroup(carToggleGroup);
    }


    @FXML
    private void checkAnswerColor() {
        boolean isBlackSelected = blackCheckBox.isSelected();
        boolean isWhiteSelected = whiteCheckBox.isSelected();
        boolean isYellowSelected = yellowCheckBox.isSelected();
        boolean isRedSelected = redCheckBox.isSelected();

        if (isBlackSelected && isWhiteSelected && !isYellowSelected && !isRedSelected) {
            answerTextColor.setText("Правильно");
        } else {
            answerTextColor.setText("Не правильно");
        }
    }

    @FXML
    private void checkAnswerStudy() {
        String selectedValue = choiceBox.getValue();

        if (selectedValue.equalsIgnoreCase("правда")) {
            answerTextStudy.setText("Правильно");
        } else {
            answerTextStudy.setText("Не правильно");
        }
    }

    @FXML
    private void checkAnswerCar() {
        RadioButton selectedRadioButton = (RadioButton) carToggleGroup.getSelectedToggle();

        if (selectedRadioButton == audiRadioButton) {
            answerTextCar.setText("Правильно");
        } else {
            answerTextCar.setText("Не правильно");
        }
    }
}
