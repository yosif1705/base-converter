package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import my.Number;
import my.ValidCharacters;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML ComboBox<Integer> basesComboBox1;
    @FXML ComboBox<Integer> basesComboBox2;
    @FXML Button swapBtn;
    @FXML TextArea resultTextArea;
    @FXML Button copyBtn;
    @FXML Button convertBtn;
    @FXML Button resetBtn;
    @FXML TextField inputTextField;

    @FXML private void swapBases(){
        Integer temp = basesComboBox1.getValue();
        basesComboBox1.valueProperty().set(basesComboBox2.getValue());
        basesComboBox2.valueProperty().set(temp);
    }
    @FXML private void resetValues(){
        inputTextField.clear();
        basesComboBox1.valueProperty().set(null);
        basesComboBox2.valueProperty().set(null);
        resultTextArea.clear();
    }
    @FXML private void convert(){
        if (inputTextField.getText().isBlank()){
            inputTextField.requestFocus();
            inputTextField.setStyle("-fx-focus-color: #FF0000");
            return;
        } else {
            inputTextField.setStyle("-fx-focus-color: #039ED3;");
        }
        Number number;
        try {
            number = new Number(inputTextField.getText(),basesComboBox1.getValue());
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid input!");
            alert.setHeaderText("The number entered is INVALID");
            alert.setContentText("For input: \"" + inputTextField.getText() + "\"" + " under base " + basesComboBox1.getValue());
            alert.show();
            return;
        }
        inputTextField.setText(number.toString());
        String result = number.parseTo(basesComboBox2.getValue()).toString();
        resultTextArea.setText(result);
    }
    @FXML private void copyResult(){
        if(resultTextArea.getText().isBlank()){
            return;
        }
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(resultTextArea.getText());
        clipboard.setContent(content);
    }

    private ArrayList<Integer> getAllValidBases () {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = ValidCharacters.MIN_RADIX; i < ValidCharacters.MAX_RADIX; i++) {
            result.add(i);
        }
        return result;
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> bases = FXCollections.observableList(getAllValidBases());
        basesComboBox1.setItems(bases);
        basesComboBox2.setItems(bases);
        resultTextArea.setEditable(false);
        resultTextArea.setFocusTraversable(false);
        Platform.runLater(() -> inputTextField.requestFocus());

    }
}