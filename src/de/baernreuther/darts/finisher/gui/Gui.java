package de.baernreuther.darts.finisher.gui;

import de.baernreuther.darts.finisher.doublecounter.DoubleCounter;
import de.baernreuther.darts.finisher.doublecounter.FinishPercentageCounter;
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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Gui extends Application {


    @FXML private Button scoreButton;

    @FXML private Label scoreLeftLabel;

    @FXML private TextField scoreInputField;

    @FXML private GridPane gridPane;

    @FXML private Scene sc;

    @FXML private Label doublesHit;

    @FXML private Label doubleNotHit;

    private boolean isGameFinished = false;

    @Override
    public void start(Stage primaryStage) {
        scoreLeftLabel = new Label(String.valueOf(new DoubleFinishNumberGenerator().generateNumberToFinish()));
        scoreLeftLabel.setScaleX(2);
        scoreLeftLabel.setScaleY(2);
        doublesHit = new Label("");
        doubleNotHit = new Label("");

        scoreInputField = new TextField();

        scoreButton = new Button();
        scoreButton.setText("Scored");
        scoreButton.setDefaultButton(true);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    int scoreInput = getScoreInput();
                    int scoreLeftStart = getScoreLeft();

                    if(isGameFinished){
                        scoreButton.setText("Scored");
                        scoreLeftLabel.setText(String.valueOf(new DoubleFinishNumberGenerator().generateNumberToFinish()));
                        isGameFinished = false;
                        return;
                    }
                    FinishCalculation finishCalculation = new DoubleFinishCalculator();
                    int scoreLeft = finishCalculation.scoreLeft(scoreInput,scoreLeftStart);
                    if(scoreLeft <= 50){
                       int doublesMissed = new DoubleCounterAlert().getDoubleAlert();
                        FinishPercentageCounter finishPercentageCounter = new DoubleCounter();
                        int missed = finishPercentageCounter.getDoublesMissed(doublesMissed);
                        int hit = finishPercentageCounter.getDoublesHit(scoreLeft==0);
                        doubleNotHit.setText(String.valueOf(missed));
                        doublesHit.setText(String.valueOf(hit));
                    }

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
        gridPane.add(doublesHit,0,4);
        gridPane.add(doubleNotHit,1,4);

        sc = new Scene(gridPane, 350,300);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Dart Finish Practice");
        primaryStage.show();
    }


    private int getScoreLeft(){
        return Integer.valueOf(scoreLeftLabel.getText());
    }
    private int getScoreInput(){
        if(scoreInputField.getText().equals("")){
            return 0;
        }
        return Integer.valueOf(scoreInputField.getText());
    }
}
