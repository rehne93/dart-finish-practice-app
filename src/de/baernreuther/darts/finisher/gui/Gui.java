package de.baernreuther.darts.finisher.gui;

import de.baernreuther.darts.finisher.doublecounter.DoubleCounter;
import de.baernreuther.darts.finisher.doublecounter.FinishPercentageCounter;
import de.baernreuther.darts.finisher.finishcalculator.DoubleFinishCalculator;
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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * @author  René Bärnreuther
 *
 *
 */
public class Gui extends Application {

    @FXML private Scene sc;

    @FXML private GridPane gridPane;

    /*** Scores the shot from scoreInputField*/
    @FXML private Button scoreButton;

    /*** Numeric input for user*/
    @FXML private TextField scoreInputField;

    /** Shows a number which the user has to score to zero */
    @FXML private Label scoreLeft;

    /** Shows how many hits the user has on doubles */
    @FXML private Label doublesHit;
    /** Shows how many shots the user has on doubles in generell*/
    @FXML private Label doubleShots;

    @FXML private final Label doubleHitLabel = new Label("Hit:");

    @FXML private final Label doubleShotLabel = new Label("Shot:");

    @FXML private final Label scoreLeftLabel = new Label("Left:");

    private boolean isGameFinished = false;
    private static final int HIGHEST_DOUBLE = 50;

    @Override
    public void start(Stage primaryStage) {
        scoreLeft = new Label(String.valueOf(new DoubleFinishNumberGenerator().generateNumberToFinish()));
        scoreLeft.setScaleX(2);
        scoreLeft.setScaleY(2);
        doublesHit = new Label("");
        doubleShots = new Label("");

        scoreInputField = new TextField();

        scoreButton = new Button();
        scoreButton.setText("Scored");
        scoreButton.setDefaultButton(true);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    int scoreInput = getScoreInput();
                    int scoreLeftBeforeCalc = getScoreLeft();
                    if(isGameFinished()) return;
                    int scoreLeft = new DoubleFinishCalculator().scoreLeft(scoreInput,scoreLeftBeforeCalc);
                    hitOnDoublePossible(scoreLeft);
                    gameHasCurrentlyFinished(scoreLeft == 0);

                    /* Reset to allow new enter */
                    Gui.this.scoreLeft.setText(String.valueOf(scoreLeft));
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
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(scoreLeftLabel,0,0);
        gridPane.add(scoreLeft,1,0);

        gridPane.add(scoreInputField,0,1);

        gridPane.add(scoreButton,0,2);

        gridPane.add(doubleHitLabel, 0, 4);
        gridPane.add(doublesHit,1,4);
        gridPane.add(doubleShotLabel,2,4);
        gridPane.add(doubleShots,3,4);

        sc = new Scene(gridPane, 300,250);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Dart Finish Practice");
        primaryStage.getIcons().add(new Image("file:res/icon.png"));
        primaryStage.show();
    }

    /** Sets the GUI Logic if the game is finished <-> the score equals zero */
    private boolean isGameFinished(){
        if(isGameFinished){
            scoreButton.setText("Scored");
            scoreLeft.setText(String.valueOf(new DoubleFinishNumberGenerator().generateNumberToFinish()));
            isGameFinished = false;
            return true;
        }
        return false;
    }

    private void gameHasCurrentlyFinished(boolean scoreIsZero){
        if(scoreIsZero){
            scoreButton.setText("New Game!");
            isGameFinished = true;
        }
    }

    private void hitOnDoublePossible(int scoreLeft){
        if(scoreLeft <= HIGHEST_DOUBLE){
            int doublesShot = new DoubleCounterAlert().getDoubleAlert();
            FinishPercentageCounter finishPercentageCounter = new DoubleCounter();
            int hit = finishPercentageCounter.getDoublesHit(scoreLeft==0);
            if(scoreLeft == 0){doublesShot++;}
            int missed = finishPercentageCounter.getDoubleShot(doublesShot);
            doubleShots.setText(String.valueOf(missed));
            doublesHit.setText(String.valueOf(hit));
        }
    }

    private int getScoreLeft(){
        return Integer.valueOf(scoreLeft.getText());
    }
    private int getScoreInput(){
        if(scoreInputField.getText().equals("")){
            return 0;
        }
        return Integer.valueOf(scoreInputField.getText());
    }
}
