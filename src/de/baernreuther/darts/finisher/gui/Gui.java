package de.baernreuther.darts.finisher.gui;

import de.baernreuther.darts.finisher.doublecounter.FinishPercentageCounter;
import de.baernreuther.darts.finisher.finishcalculator.FinishCalculation;
import de.baernreuther.darts.finisher.numbergenerator.NumberGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class Gui extends Application {

    /**
     * Default width for Window
     */
    protected final static int DEFAULT_WIDTH = 300;
    /**
     * Default height for Window
     */
    protected final static int DEFAULT_HEIGHT = 250;
    /**
     * Scene of the screen
     */
    protected Scene scene;
    /**
     * Gridlayout
     */
    protected GridPane gridLayout;
    // TODO Refactor this to be more readable
    protected FinishCalculation finishCalculation;

    protected FinishPercentageCounter finishPercentageCounter;

    protected NumberGenerator numberGenerator;

    /**
     * Calls initializeGridLayout as well as initializeScene(). Afterwards sets default title, default icon and shows the user interface.
     * Initialize all your layout elements and call super.start(stage); at the end with a implemented initializeLayout and initializeScene method.
     */
    @Override
    public void start(Stage primaryStage) {
        initializeGridLayout();
        initializeScene();
        initializeCounter();

        primaryStage.setTitle("Dart Finish Practice");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:res/icon.png"));
        primaryStage.show();
    }

    /**
     * Should initialize all layout elements in the grid layout
     */
    protected void initializeGridLayout() {
        gridLayout = new GridPane();
    }


    /**
     * Should initialize the scene.
     * Initializes it with the gridlayout and DEFAULT_WIDTH as well as DEFAULT_HIGHT
     */
    protected void initializeScene() {
        scene = new Scene(gridLayout, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    // TODO: Make this more readable for the user.
    protected abstract void initializeCounter();


}
