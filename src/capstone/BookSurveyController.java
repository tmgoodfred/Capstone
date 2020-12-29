package capstone;

import static capstone.MainMenuController.elements;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
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
    boolean continueCommand = true;
    String booksReadToInsert;
    String booksRatingToInsert;
    int totalReadToInsert;
    
    Double userRatingAA, userRatingC,userRatingM, userRatingF, userRatingHF,        //variables to store the user's genre preference data
            userRatingH, userRatingT, userRatingR, userRatingSF, userRatingSS, userRatingHS, userRatingYA;
    String usersReadBooks, usersReadBookRatings;
    
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
        List<String> listOfBooksRead = Arrays.asList(elements); //[3],[4],[1]
        Integer ID = MainMenuController.bookIDtoShare;
        for(int i=0;i<listOfBooksRead.size();i++)
        {
            if(listOfBooksRead.get(i).equals(ID.toString()))    //if any books match the books the user has read, do not allow them to rate again
            {
                continueCommand = false;
                break;
            }
        }
        if(continueCommand == true){
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
                    String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                    conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
                    Statement stmt = null;
                    String query = "SELECT bookRating, bookTotalReads FROM capstone.books WHERE bookID = "+MainMenuController.bookIDtoShare+";"; //gets relevant book data from database
                    try {
                        stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()){
                            bookRating = rs.getDouble(1);   //gets the current book rating of the selected book
                            bookTotalReads = rs.getInt(2);  //gets the current total reads of the book selected
                        }
                        readsToInsert = bookTotalReads+1;   //increases the total reads by 1
                        ratingToInsert = (bookRating+rating)/readsToInsert; //recalculates the reating to insert
                        String update = "UPDATE capstone.books SET bookRating="+ratingToInsert+", bookTotalReads="+readsToInsert+" WHERE bookID = "+MainMenuController.bookIDtoShare+";";
                        stmt.executeUpdate(update);
                        booksReadToInsert = MainMenuController.userBooksReadList+","+MainMenuController.bookIDtoShare;  //adds the book to the user's read list
                        booksRatingToInsert = MainMenuController.userBooksRatingList+","+rating.toString(); //inserts the user's rating to the string
                        totalReadToInsert = MainMenuController.userTotalBooksReadList+1;    //increases the user's total books read by 1
                        String update2 = "UPDATE capstone.users SET userBooksRead=\""+booksReadToInsert+"\", userBooksRating=\""+booksRatingToInsert+"\", userBooksReadTotal="+totalReadToInsert+" WHERE userID="+LoginScreenController.userIDtoShare+";";
                        stmt.executeUpdate(update2);
                        
                        String userRatingQuery = "SELECT actionAdventureLvl, classicLvl, mysteryLvl, fantasyLvl, historicalFictionLvl, horrorLvl, thrillerLvl,"
                                + "romanceLvl, sciFiLvl, shortStoriesLvl, historyLvl, youngAdultLvl, userBooksRead, userBooksRating WHERE userID = "+LoginScreenController.userIDtoShare+";";
                        ResultSet rs3 = stmt.executeQuery(userRatingQuery);
                        while(rs3.next()){
                            userRatingAA = rs3.getDouble(1);
                            userRatingC = rs3.getDouble(2);
                            userRatingM = rs3.getDouble(3);
                            userRatingF = rs3.getDouble(4);
                            userRatingHF = rs3.getDouble(5);
                            userRatingH = rs3.getDouble(6);
                            userRatingT = rs3.getDouble(7);
                            userRatingR = rs3.getDouble(8);
                            userRatingSF = rs3.getDouble(9);
                            userRatingSS = rs3.getDouble(10);
                            userRatingH = rs3.getDouble(11);
                            userRatingYA = rs3.getDouble(12);
                            usersReadBooks = rs3.getString(13);
                            usersReadBookRatings = rs3.getString(14);
                        }
                        //need to parse through the string, comma delimiter, and then from there put those into a list and compare them in
                        //a series of if statements comparing the bookID in a select statement in a for loop to the genre, and then putting that into a list for each genre
                        //kinda convoluted
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
        else{   //shows if the user has already read the book selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("You've already read and rated this book!");

            alert.showAndWait();
        }
        Stage stage = (Stage) submitBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void backButtonAction(ActionEvent event) throws Exception { //back button goes back to book screen
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.close();
    }
    
}
