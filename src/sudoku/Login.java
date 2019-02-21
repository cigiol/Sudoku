/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author cigid
 */



public class Login implements Initializable{
    
    @FXML Button playBtn;
    @FXML Button btnExit;
    String sa="MERHABA"; 
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
     public void playBtnPressed() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("layout.fxml"));
                /* 
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                
                Scene scene = new Scene(fxmlLoader.load(), 500, 680);
                Stage stage = new Stage();
                stage.setTitle("SUDOKU");
                stage.setScene(scene);
                stage.show();
                Stage stage2 = (Stage) playBtn.getScene().getWindow();
    // do what you have to do
                stage2.close();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
	}
     public void exitButtonPressed(){
                Stage stage2 = (Stage) btnExit.getScene().getWindow();
    // do what you have to do
                stage2.close();
     }
     public int at(){
         return 0;
     }
    
}
