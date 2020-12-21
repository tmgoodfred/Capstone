package capstone;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
public class DeleteBookController implements Initializable {

    int bookIDtoComp = ManagerMainMenuController.bookIDtoShare;
    
    @FXML
    Button yesBtn;
    @FXML
    Button noBtn;
    
    @FXML
    public void yesButtonAction(ActionEvent event) throws IOException{  //if yes button is pressed
        String delete = "DELETE FROM capstone.books WHERE bookID ="+bookIDtoComp+";";   //delete statement
        Connection conn = null;
            try {
                String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                conn = DriverManager.getConnection(url2, "root", "Rootpass1!");
                Statement stmt = conn.createStatement();
                    try {
                        stmt.executeUpdate(delete);
                        Stage stage2 = (Stage) yesBtn.getScene().getWindow();  //the following takes the user back to the manager main menu screen
                        stage2.close();
                        Parent root = FXMLLoader.load(getClass().getResource("ManagerMainMenu.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                catch (SQLException e ) {
                  throw new Error("Problem", e);
                } finally {
                  if (stmt != null) { stmt.close(); }
                }
            }
            catch (SQLException e) {
                throw new Error("Problem", e);
            } 
            finally {
              try {if (conn != null) {conn.close();}} 
              catch (SQLException ex) {System.out.println(ex.getMessage());}
            } 
    }
    
    @FXML
    public void noButtonAction(ActionEvent event) throws IOException{
        Stage stage2 = (Stage) noBtn.getScene().getWindow();  //the following takes the user back to the manager main menu screen
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("ManagerMainMenu.fxml"));
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
