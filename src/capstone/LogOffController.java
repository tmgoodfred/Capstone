package capstone;

import java.io.IOException;
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
 * @author Tyler Goodfred
 */
public class LogOffController implements Initializable {
    
    @FXML
    Button yesBtn;
    @FXML
    Button noBtn;
    
    @FXML
    public void yesButtonAction(ActionEvent event) throws IOException{  //if yes button is pressed
        Stage stage2 = (Stage) yesBtn.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void noButtonAction(ActionEvent event) throws IOException{   //unfortunately this means it has to run the algorithm again and can make the launch take time but
        Stage stage2 = (Stage) noBtn.getScene().getWindow();            //it's neccesary for the log off to work properly. 
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
    
}
