/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tmgoo
 */
public class MoreBookInfoController implements Initializable {

    @FXML
    Button backBtn;
    
    static int bookID = MainMenuController.bookIDtoShare;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(bookID);
    }  
    
    @FXML
    private void backButtonAction(ActionEvent event) throws Exception { //back button reinitializes and launches the main login screen
        Stage stage2 = (Stage) backBtn.getScene().getWindow();
        stage2.close();
    }
    
}
