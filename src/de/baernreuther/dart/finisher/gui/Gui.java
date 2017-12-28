package de.baernreuther.dart.finisher.gui;

import de.baernreuther.dart.finisher.logic.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application{


    @FXML private TextField scoreInput;

    @FXML private Button shotButton;

    @FXML private Label scoreLabel;

    @FXML private Label dartsThrown;

    @FXML private Label average;

    @FXML private Label avgTextLabel;

    @FXML private Label dtTextLabel;

    @Override
    public void start(Stage primaryStage){
        shotButton = new Button();
        shotButton.setText("Scored");
        shotButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if(Integer.valueOf(scoreLabel.getText()) == 0){
                    scoreLabel.setText(String.valueOf(new DoubleFinishNumberGenerator().generateFinish()));
                    shotButton.setText("Score");
                    dartsThrown.setText("0");
                    return;
                }
                try{
                    int thrown = Integer.valueOf(scoreInput.getText());
                    IVerifyFinish verifyFinisher = new VerifyFinishDouble();
                    int scoreLeft = verifyFinisher.verifyFinish(thrown, Integer.valueOf(scoreLabel.getText()));
                    scoreLabel.setText(String.valueOf(scoreLeft));
                    if(scoreLeft == 0){
                        shotButton.setText("New Game");
                    }

                    int dartsThrownNum = Integer.valueOf(dartsThrown.getText()) +3;
                    dartsThrown.setText(String.valueOf(dartsThrownNum));

                    IAverageCalculator averageCalculator = new AverageCalculator();
                   double avg = averageCalculator.calculateAverage(dartsThrownNum,thrown);
                   average.setText(String.format("%.2f",avg));
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                scoreInput.setText("");
            }
        });

        scoreInput = new TextField();
        scoreInput.setText("0");
        //Removes non numeric characters
        scoreInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    scoreInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



        scoreLabel = new Label(String.valueOf(new DoubleFinishNumberGenerator().generateFinish()));
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);
        dartsThrown = new Label("0");
        average = new Label("0");
        avgTextLabel = new Label("Avg:");
        dtTextLabel = new Label("Dts/Leg:");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,20,10,20));

        grid.add(scoreLabel,0,0);
        grid.add(scoreInput,0,1);
        grid.add(shotButton,0,2);

        grid.add(average,1,3);
        grid.add(avgTextLabel,0,3);
        grid.add(dtTextLabel,2,3);
        grid.add(dartsThrown,3,3);

        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setTitle("Dart Finish Practice");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

