/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tmgoo
 */
public class MoreBookInfoController implements Initializable {

    @FXML
    Button backBtn;
    @FXML
    Button bookReadBtn;
    @FXML
    ImageView thumbnailImg;
    @FXML
    TextField titleTxt;
    @FXML
    TextField authorTxt;
    @FXML
    TextArea descriptionTxt;
    @FXML
    Label noImageAvailable;
    
    String bookTitle,bookAuthor,bookDescription;
    InputStream bookCover;
    
    int bookIDtoComp = MainMenuController.bookIDtoShare;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rootpass1");
            Statement stmt = null;
            String query = "SELECT bookTitle, bookAuthor, bookCover, bookDescription FROM capstone.books WHERE bookID ="+bookIDtoComp+";"; //gets relevant book data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    bookTitle = rs.getString(1);
                    bookAuthor = rs.getString(2);
                    bookCover = rs.getBinaryStream(3);
                    bookDescription = rs.getString(4);
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
        /*noImageAvailable.setText("");
        titleTxt.clear();
        authorTxt.clear();
        descriptionTxt.clear();*/
        if(bookCover != null){
            Image bookThumbnail = new Image(bookCover);
            thumbnailImg.setImage(bookThumbnail);
            titleTxt.setText(bookTitle);
            authorTxt.setText(bookAuthor);
            descriptionTxt.setText(bookDescription);
        }
        else{
            noImageAvailable.setText("No Image Available");
            titleTxt.setText(bookTitle);
            authorTxt.setText(bookAuthor);
            descriptionTxt.setText(bookDescription);
        }
            
    }
    
    
    @FXML
    private void backButtonAction(ActionEvent event) throws Exception { //back button reinitializes and launches the main login screen
        Stage stage2 = (Stage) backBtn.getScene().getWindow();
        stage2.close();
    }
    
    @FXML
    private void readButtonAction(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BookSurvey.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
