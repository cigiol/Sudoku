/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author cigid
 */
public class Sudoku extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        /* load layout.fxml from file and assign it to a scene root object */
		Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));

		/* assign the root to a new scene and define its dimensions */
		Scene scene = new Scene(root, 480, 680);

		/* set the title of the stage (window) */
		primaryStage.setTitle("Sudoku");
		/* set the scene of the stage to our newly created from the layout scene */
		primaryStage.setScene(scene);
		/* show the stage */
		primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
