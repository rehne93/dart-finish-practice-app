package de.baernreuther.darts.finisher.gui;

import de.baernreuther.darts.finisher.doublecounter.FinishPercentageCounter;
import de.baernreuther.darts.finisher.finishcalculator.FinishCalculation;
import de.baernreuther.darts.finisher.numbergenerator.NumberGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;


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
    /**
     * MenuBar on top
     */
    protected MenuBar menuBar;
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
        initializeMenuBar();
        initializeGridLayout();
        initializeScene();
        initializeCounter();

        primaryStage.setTitle("Dart Finish Practice");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
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

    /**
     * Initializes a simple MenuBar.
     * Should be overriden if necessary.
     */
    protected void initializeMenuBar(){
        menuBar = new MenuBar();
    }


    /**
     * Adds the menuBar if not zero
     */
    protected final void enableMenuBar(int rowIndex){
        if(menuBar != null){
            gridLayout.add(menuBar,0,rowIndex);
        }
    }
    // TODO: Make this more readable for the user.
    protected abstract void initializeCounter();


}
