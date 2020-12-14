/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import static capstone.MainMenuController.elements;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author tmgoo
 */
public class BookSurveyController implements Initializable {

    @FXML
    Button submitBtn;
    @FXML
    Button backBtn;
    @FXML
    RadioButton rate1;
    @FXML
    RadioButton rate2;
    @FXML
    RadioButton rate3;
    @FXML
    RadioButton rate4;
    @FXML
    RadioButton rate5;
    
    ToggleGroup rate = new ToggleGroup();
    
    Double rating;
    double bookRating, ratingToInsert;
    int bookTotalReads, readsToInsert;
    int continueCommand = 0;
    String booksReadToInsert;
    int totalReadToInsert;
    JFrame parent = new JFrame();   //for error pop up message
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rate1.setToggleGroup(rate);
         rate2.setToggleGroup(rate);
         rate3.setToggleGroup(rate);
         rate4.setToggleGroup(rate);
         rate5.setToggleGroup(rate);
    }

    @FXML
    private void submitButtonAction(ActionEvent event) throws Exception {
        //here will be code to add +1 to book reads, recalculate the book rating
        //which means selecting the total reads, current score, and then adding 1 and then doing the maths
        //also adding this book read to the user's list, and first checking if the user has already read, then
        //outputting an error saying "you've already read and rated this book!" and closing the window
        List<String> fixedLengthList = Arrays.asList(elements);
        ArrayList<String> listOfString = new ArrayList<String>(fixedLengthList);
        for(int i=0;i<listOfString.size();i++)
        {
            if(elements[i].equals(MainMenuController.bookIDtoShare))
            {
                continueCommand = 1;
                break;
            }
            break;
        }
        if(continueCommand == 0){
            if(rate.getSelectedToggle() == rate1){
                    rating = 1.0;
                }
                else if(rate.getSelectedToggle() == rate2){
                    rating = 2.0;
                }
                else if(rate.getSelectedToggle() == rate3){
                    rating = 3.0;
                }
                else if(rate.getSelectedToggle() == rate4){
                    rating = 4.0;
                }
                else if(rate.getSelectedToggle() == rate5){
                    rating = 5.0;
                }

                Connection conn = null;
                try {
                    String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                    conn = DriverManager.getConnection(url2, "root", "Rootpass1");
                    Statement stmt = null;
                    String query = "SELECT bookRating, bookTotalReads FROM capstone.books WHERE bookID = "+MainMenuController.bookIDtoShare+";"; //gets relevant book data from database
                    try {
                        stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            bookRating = rs.getDouble(1);
                            bookTotalReads = rs.getInt(2);
                        }
                        readsToInsert = bookTotalReads+1;
                        ratingToInsert = (bookRating+rating)/readsToInsert;
                        String update = "UPDATE capstone.books SET bookRating="+ratingToInsert+", bookTotalReads="+readsToInsert+" WHERE bookID = "+MainMenuController.bookIDtoShare+";";
                        stmt.executeUpdate(update);
                        booksReadToInsert = MainMenuController.userBooksReadList+","+MainMenuController.bookIDtoShare;
                        totalReadToInsert = MainMenuController.userTotalBooksReadList+1;
                        String update2 = "UPDATE capstone.users SET userBooksRead="+booksReadToInsert+", userBooksReadTotal="+totalReadToInsert+" WHERE userID = "+LoginScreenController.userIDtoShare+";";
                        stmt.executeUpdate(update2);
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

                Stage stage = (Stage) submitBtn.getScene().getWindow();
                stage.close();
        }
        else{
            JOptionPane.showMessageDialog(parent, "Error: You've already read and rated this book!");
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    private void backButtonAction(ActionEvent event) throws Exception { //back button goes back to book screen
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }
    
}
