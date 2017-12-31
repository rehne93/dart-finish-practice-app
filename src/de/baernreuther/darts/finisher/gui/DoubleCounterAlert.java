package de.baernreuther.darts.finisher.gui;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;


public class DoubleCounterAlert {

    public int getDoubleAlert() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.getButtonTypes().clear();
        alert.setTitle("Doules missed");
        alert.setHeaderText(null);
        alert.setContentText("Doubles missed?");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:res/icon.png"));
        ButtonType buttonTypeOne = new ButtonType("One");
        ButtonType buttonTypeTwo = new ButtonType("Two");
        ButtonType buttonTypeThree = new ButtonType("Three");
        ButtonType buttonTypeCancel = new ButtonType("Zero", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        EventHandler<KeyEvent> fireOnEnter = event -> {
            if (KeyCode.ENTER.equals(event.getCode())
                    && event.getTarget() instanceof Button) {
                ((Button) event.getTarget()).fire();
            }
            // Allow to enter 1,2,3 on Numbers or Numpad as well
            if ((KeyCode.DIGIT1.equals(event.getCode()) || KeyCode.NUMPAD1.equals(event.getCode())) && event.getTarget() instanceof Button) {
                ((Button) alert.getDialogPane().lookupButton(buttonTypeOne)).fire();
            }
            if ((KeyCode.DIGIT2.equals(event.getCode()) || KeyCode.NUMPAD2.equals(event.getCode())) && event.getTarget() instanceof Button) {
                ((Button) alert.getDialogPane().lookupButton(buttonTypeTwo)).fire();
            }
            if ((KeyCode.DIGIT3.equals(event.getCode()) || KeyCode.NUMPAD3.equals(event.getCode())) && event.getTarget() instanceof Button) {
                ((Button) alert.getDialogPane().lookupButton(buttonTypeThree)).fire();
            }
            if ((KeyCode.DIGIT0.equals(event.getCode()) || KeyCode.NUMPAD0.equals(event.getCode())) && event.getTarget() instanceof Button) {
                ((Button) alert.getDialogPane().lookupButton(buttonTypeCancel)).fire();
            }
        };

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getButtonTypes().stream().map(dialogPane::lookupButton).forEach(button -> button.addEventHandler(KeyEvent.KEY_PRESSED,fireOnEnter));


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
