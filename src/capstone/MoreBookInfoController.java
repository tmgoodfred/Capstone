/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author tmgoo
 */
public class MoreBookInfoController implements Initializable {

    static int bookID = MainMenuController.bookIDtoShare;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(bookID);
    }    
    
}
