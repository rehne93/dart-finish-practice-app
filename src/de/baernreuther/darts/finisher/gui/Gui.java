package de.baernreuther.darts.finisher.gui;

import de.baernreuther.darts.finisher.finishcalculator.DoubleFinishCalculator;
import de.baernreuther.darts.finisher.finishcalculator.FinishCalculation;
import de.baernreuther.darts.finisher.numbergenerator.DoubleFinishNumberGenerator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.font.TextLabel;


public class Gui extends Application {


    @FXML private Button scoreButton;

    @FXML private Label scoreLeftLabel;

    @FXML private TextField scoreInputField;

    @FXML private GridPane gridPane;

    @FXML private Scene sc;

    @FXML private Label average;

    private boolean isGameFinished = false;

    @Override
    public void start(Stage primaryStage) {
        scoreLeftLabel = new Label(String.valueOf(new DoubleFinishNumberGenerator().generateNumberToFinish()));

        scoreInputField = new TextField();

        scoreButton = new Button();
        scoreButton.setText("Scored");
        scoreButton.setDefaultButton(true);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    if(isGameFinished){
                        scoreButton.setText("Scored");
                        scoreLeftLabel.setText(String.valueOf(new DoubleFinishNumberGenerator().generateNumberToFinish()));
                        isGameFinished = false;
                        return;
                    }
                    FinishCalculation finishCalculation = new DoubleFinishCalculator();
                    int scoreLeft = finishCalculation.scoreLeft(Integer.parseInt(scoreInputField.getText()),Integer.parseInt(scoreLeftLabel.getText()));
                    if(scoreLeft == 0){
                        scoreButton.setText("New Game!");
                        isGameFinished = true;
                    }
                    scoreLeftLabel.setText(String.valueOf(scoreLeft));
                    scoreInputField.setText("");

                }catch(Exception ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Number Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a number.");
                    alert.showAndWait();
                }
            }
        });

        // force the field to be numeric only
        // source: https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        scoreInputField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    scoreInputField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.add(scoreLeftLabel,0,0);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(scoreInputField,0,1);
        gridPane.add(scoreButton,0,2);


        sc = new Scene(gridPane, 350,300);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Dart Finish Practice");
        primaryStage.show();
    }
}
