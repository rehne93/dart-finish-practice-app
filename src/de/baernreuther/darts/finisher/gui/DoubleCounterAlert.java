package de.baernreuther.darts.finisher.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DoubleCounterAlert {


    public int getDoubleAlert() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Doules missed");
        alert.setHeaderText(null);
        alert.setContentText("Doubles missed?");

        ButtonType buttonTypeOne = new ButtonType("One");
        ButtonType buttonTypeTwo = new ButtonType("Two");
        ButtonType buttonTypeThree = new ButtonType("Three");
        ButtonType buttonTypeCancel = new ButtonType("Zero", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeOne){
            return 1;
        }else if(result.get() == buttonTypeTwo){
            return 2;
        }else if(result.get() == buttonTypeThree){
            return 3;
        }else{
            return 0;
        }}

}
