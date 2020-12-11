package capstone;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Tyler Goodfred
 */
public class CreateAccountScreenController implements Initializable {
        
    List<String> usernameList = new ArrayList<String>();
    
    @FXML
    Button backBtn;
    
    @FXML
    TextField usernameTxt;
    
    @FXML
    TextField passwordTxt;
    
    @FXML
    TextField ageTxt;
    
    @FXML
    ComboBox genderDropBox;
    
    @FXML
    private void nextButtonAction(ActionEvent event) {
    
    }
    
    @FXML
    private void backButtonAction(ActionEvent event) throws Exception {
        Stage stage2 = (Stage) backBtn.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    //upon initialization, the datbase is access and queries are run
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rockgod101!");
            Statement stmt = null;
            String query = "SELECT username, password FROM capstone.users"; //gets login data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    usernameList.add(rs.getString(1));  //adds usernames to username list
                }
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
        
        genderDropBox.getItems().addAll("Male", "Female", "Non-Binary", "Agender", "Other");
    }   
    
}
