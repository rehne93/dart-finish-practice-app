package de.baernreuther.darts.finisher.gui;

import de.baernreuther.darts.finisher.doublecounter.DoubleCounter;
import de.baernreuther.darts.finisher.finishcalculator.DoubleFinishCalculator;
import de.baernreuther.darts.finisher.numbergenerator.HigherDoubleFinishNumberGenerator;
import de.baernreuther.darts.finisher.numbergenerator.SmallerDoubleFinishGenerator;
import de.baernreuther.darts.finisher.numbergenerator.X01NumberGenerator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * @author René Bärnreuther
 */
public class FinishingGui extends Gui {

    private static final int HIGHEST_DOUBLE = 50;

    private final Label doubleHitLabel = new Label("Hit:");
    private final Label doubleShotLabel = new Label("Shot:");
    private final Label scoreLeftLabel = new Label("Left:");
    private final Label doubleAverageLabel = new Label("Average:");

    /*** Scores the shot from scoreInputField*/
    private Button scoreButton;
    /*** Numeric input for user*/
    private TextField scoreInputField;
    /*** Shows a number which the user has to score to zero*/
    private Label scoreLeft;
    /*** Shows how many hits the user has on doubles*/
    private Label doublesHit;
    /*** Shows how many shots the user has on doubles in generell*/
    private Label doubleShots;
    /*** Shows the average on doubles */
    private Label doubleAverage;


    private boolean isGameFinished = false;


    @Override
    protected void initializeCounter() {
        finishPercentageCounter = new DoubleCounter();
        finishCalculation = new DoubleFinishCalculator();
        numberGenerator = new HigherDoubleFinishNumberGenerator();
    }

    @Override
    public void start(Stage primaryStage) {
        scoreLeft = new Label(String.valueOf(new SmallerDoubleFinishGenerator().generateNumberToFinish()));
        scoreLeft.setScaleX(2);
        scoreLeft.setScaleY(2);
        doublesHit = new Label("0");
        doubleShots = new Label("0");
        doubleAverage = new Label("0,0%");

        scoreInputField = new TextField();

        scoreButton = new Button();
        scoreButton.setText("Scored");
        scoreButton.setDefaultButton(true);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int scoreInput = getScoreInput();
                    int scoreLeftBeforeCalc = getScoreLeft();

                    if (isGameFinished()) return;

                    int scoreLeft = finishCalculation.scoreLeft(scoreInput, scoreLeftBeforeCalc);

                    hitOnDoublePossible(scoreLeft);
                    FinishingGui.this.scoreLeft.setText(String.valueOf(scoreLeft));
                    setNewScoreIfFinished(scoreLeft == 0);

                    /* Reset to allow new enter */
                    scoreInputField.setText("");

                } catch (Exception ex) {
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

        super.start(primaryStage);

    }


    @Override
    protected void initializeScene() {
        super.initializeScene();

    }

    @Override
    protected void initializeGridLayout() {
        gridLayout = new GridPane();
        gridLayout.setVgap(20);
        gridLayout.setHgap(10);

        gridLayout.add(scoreLeftLabel, 0, 1);
        gridLayout.add(scoreLeft, 1, 1);

        gridLayout.add(scoreInputField, 0, 2);

        gridLayout.add(scoreButton, 0, 3);

        gridLayout.add(doubleHitLabel, 0, 4);
        gridLayout.add(doublesHit, 1, 4);

        gridLayout.add(doubleShotLabel, 2, 4);
        gridLayout.add(doubleShots, 3, 4);

        gridLayout.add(doubleAverageLabel, 0, 5);
        gridLayout.add(doubleAverage, 1, 5);

        super.enableMenuBar(0);


    }

    @Override
    protected void initializeMenuBar() {
        menuBar = new MenuBar();

        Menu modes = new Menu("Modes");
        CheckMenuItem smallerDoubles = new CheckMenuItem("Smaller Doubles");
        CheckMenuItem higherDoubles = new CheckMenuItem("Higher Doubles");
        CheckMenuItem threeHundertd1 = new CheckMenuItem("301");

        smallerDoubles.setSelected(true);

        modes.getItems().add(smallerDoubles);
        modes.getItems().add(higherDoubles);
        modes.getItems().add(threeHundertd1);

        menuBar.getMenus().add(modes);


        threeHundertd1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberGenerator = new X01NumberGenerator(301);
                higherDoubles.setSelected(false);
                smallerDoubles.setSelected(false);
                threeHundertd1.setSelected(true);
                resetState();
            }
        });
        smallerDoubles.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                numberGenerator = new SmallerDoubleFinishGenerator();
                higherDoubles.setSelected(false);
                smallerDoubles.setSelected(true);
                threeHundertd1.setSelected(false);
                resetState();
            }
        });

        higherDoubles.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                numberGenerator = new HigherDoubleFinishNumberGenerator();
                higherDoubles.setSelected(true);
                smallerDoubles.setSelected(false);
                threeHundertd1.setSelected(false);
                resetState();
            }
        });

    }


    /**
     * Resets the state to a new number.
     */
    private void resetState() {
        scoreLeft.setText(String.valueOf(numberGenerator.generateNumberToFinish()));
    }

    /**
     * Sets the GUI Logic if the game is finished <-> the score equals zero
     */
    private boolean isGameFinished() {
        if (isGameFinished) {
            scoreButton.setText("Scored");
            scoreLeft.setText(String.valueOf(numberGenerator.generateNumberToFinish()));
            isGameFinished = false;
            return true;
        }
        return false;
    }

    private void setNewScoreIfFinished(boolean scoreIsZero) {
        if (scoreIsZero) {
            scoreLeft.setText(String.valueOf(numberGenerator.generateNumberToFinish()));
        }
    }

    private void hitOnDoublePossible(int scoreLeft) {
        if (scoreLeft <= HIGHEST_DOUBLE || getScoreInput() == 0) {
            int doublesShot = new DoubleCounterAlert().getDoubleAlert();

            int hit = finishPercentageCounter.getDoublesHit(scoreLeft == 0);

            if (scoreLeft == 0) {
                doublesShot++;
            }
            int missed = finishPercentageCounter.getDoubleShot(doublesShot);

            doubleShots.setText(String.valueOf(missed));
            doublesHit.setText(String.valueOf(hit));
            doubleAverage.setText(String.format("%.4g", finishPercentageCounter.getAverage()) + "%");

        }
    }

    private int getScoreLeft() {
        return Integer.valueOf(scoreLeft.getText());
    }

    private int getScoreInput() {
        if (scoreInputField.getText().equals("")) {
            return 0;
        }
        return Integer.valueOf(scoreInputField.getText());
    }


}
