package jjonesfreezeboilpoints;

import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author: Jacob Jones
 * Date Completed: 5/3/2020
 * 
 * This program will display a list of substances and their freezing and boiling temperatures,
 * and then allows the user to change the current temperature to see how the substances change.
 */
public class JJonesFreezeBoilPoints extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        // The minimum size variables for the window
        final int minWidth = 550;
        final int minHeight = 300;
        
        // Initializes the scene layout and it's properties
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, minWidth, minHeight);
        scene.getStylesheets().add("styles/main.css"); 
        
        // Sets the properties of the window
        primaryStage.setTitle("State Tranformation Simulator");
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.setWidth(minWidth);
        primaryStage.setHeight(minHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Initializes the HashMap that stores the freezing and boiling points for the substances
        HashMap<String, int[]> temperatureMap = new HashMap<>(4);
        temperatureMap.put("Ethyl Alcohol", new int[]{-173, 172});
        temperatureMap.put("Mercury", new int[]{-38, 676});
        temperatureMap.put("Oxygen", new int[]{-362, -306});
        temperatureMap.put("Water", new int[]{32, 212});
        
        // Initializes the column that holds the names of the substances and it's properties
        VBox substanceCol = new VBox(new Label("Substance"), new Label("Ethyl Alcohol"), new Label("Mercury"), new Label("Oxygen"),new Label("Water"));
        substanceCol.setAlignment(Pos.CENTER);
        substanceCol.setPadding(new Insets(10));
        substanceCol.setSpacing(5);
        
        // Initializes the column that holds the names of the freezing pojnts and it's properties
        VBox freezingPointCol = new VBox(new Label("Freezing Point"));
        freezingPointCol.setAlignment(Pos.CENTER);
        freezingPointCol.setPadding(new Insets(10));
        freezingPointCol.setSpacing(5);
        
        // Initializes the column that holds the names of the boiling pojnts and it's properties
        VBox boilingPointCol = new VBox(new Label("Boiling Points"));
        boilingPointCol.setAlignment(Pos.CENTER);
        boilingPointCol.setPadding(new Insets(10));
        boilingPointCol.setSpacing(5);
        
        // Initializes the column that holds the names of the state changes and it's properties
        VBox stateChangeCol = new VBox(new Label("State"), new Label("No Change"), new Label("No Change"), new Label("Boils"), new Label("No Change"));
        stateChangeCol.setAlignment(Pos.CENTER);
        stateChangeCol.setPadding(new Insets(10));
        stateChangeCol.setSpacing(5);
        
        // Adds the points from the dictionary into the point columns
        for(String substance : temperatureMap.keySet()){
            freezingPointCol.getChildren().add(new Label(temperatureMap.get(substance)[0] + ""));
            boilingPointCol.getChildren().add(new Label(temperatureMap.get(substance)[1] + ""));
        }
        
        // Initializes the row that holds all of the columns and it's properties
        HBox table = new HBox(substanceCol, freezingPointCol, boilingPointCol, stateChangeCol);
        table.setAlignment(Pos.CENTER);
        table.setPadding(new Insets(10));
        table.setSpacing(5);
        layout.setCenter(table);
        
        // Initializes the elements that change the current temperature and it's properties
        Label tempLabel = new Label("Current Temperature: ");
        TextField tempField = new TextField("70");
        Button tempButton = new Button("Check State Change");
        tempButton.setOnAction(event -> {
            // The current substance that the loop is on
            int index = 0;
            for(String substance : temperatureMap.keySet()){
                // Retrieves the needed temperatures and stores them in variables
                int freezePoint = temperatureMap.get(substance)[0];
                int boilPoint = temperatureMap.get(substance)[1];
                int temperature = Integer.parseInt(tempField.getText());
                
                // If the current temperature is less than the freezing point of the current substance,
                // Set the substance's state to "Freezes"
                if (temperature <= freezePoint){
                    stateChangeCol.getChildren().set(index + 1, new Label("Freezes"));
                } 
                // Otherwise, if the current temperature is more than the boiling point of the current substance,
                // Set the substance's state to "Boils"
                else if (temperature >= boilPoint){
                    stateChangeCol.getChildren().set(index + 1, new Label("Boils"));
                } 
                // Otherwise, set the current substance's state to "No Change"
                else {
                    stateChangeCol.getChildren().set(index + 1, new Label("No Change"));
                }
                index++;
            }
        });
        // Initializes a row that holds all of the temperature elements and sets the row's properties
        HBox tempRow = new HBox(tempLabel, tempField, tempButton);
        tempRow.setAlignment(Pos.CENTER);
        tempRow.setPadding(new Insets(10));
        tempRow.setSpacing(5);
        layout.setBottom(tempRow);
    }
    
}
