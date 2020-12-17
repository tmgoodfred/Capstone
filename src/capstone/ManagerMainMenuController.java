package capstone;

import capstone.User;
import capstone.Book;
import static capstone.User.userBooksReadTotalL;
import static capstone.User.userFirstNameL;
import static capstone.User.userIDL;
import static capstone.User.userLastNameL;
import static capstone.User.userTimesAccessedL;
import static capstone.User.usernameL;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class ManagerMainMenuController implements Initializable {

    @FXML
    TableView<Book> allTableView;
    @FXML
    TableView<User> userTableView;
    
    @FXML
    TableColumn usernameTab;
    @FXML
    TableColumn userFirstNameTab;
    @FXML        
    TableColumn userLastNameTab;
    @FXML
    TableColumn userBooksReadTotalTab;
    @FXML
    TableColumn userTimesAccessedTab;
    @FXML
    TableColumn allTitleTab;
    @FXML
    TableColumn allAuthTab;
    @FXML
    TableColumn allDescTab;
    @FXML
    TableColumn allPgCntTab;
    @FXML
    TableColumn allGenreTab;
    @FXML
    TableColumn allRateTab;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rootpass1");
            Statement stmt = null;
            String query = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre FROM capstone.books"; //gets relevant book data from database
            String query2 = "SELECT userID,username,userFirstName,userLastName,userBooksReadTotal,userTimesAccessed FROM capstone.users WHERE accessLvl=3"; //gets relevant book data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Book.bookID.add(rs.getInt(1));
                    Book.bookTitle.add(rs.getString(2));
                    Book.bookAuthor.add(rs.getString(3));
                    Book.bookDescription.add(rs.getString(4));
                    Book.bookPageCount.add(rs.getInt(5));
                    Book.bookRating.add(rs.getDouble(6));
                    Book.bookTotalReads.add(rs.getInt(7));
                    Book.bookMainGenre.add(rs.getString(8));
                }
                ResultSet rs2 = stmt.executeQuery(query2);
                while (rs2.next()){
                    User.userIDL.add(rs2.getInt(1));
                    User.usernameL.add(rs2.getString(2));
                    User.userFirstNameL.add(rs2.getString(3));
                    User.userLastNameL.add(rs2.getString(4));
                    User.userBooksReadTotalL.add(rs2.getInt(5));
                    User.userTimesAccessedL.add(rs2.getInt(6));
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
        
        allTitleTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitles"));    //initializes the table columns to prepare them to retrieve the data
        allAuthTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookAuthors"));
        allDescTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookDescriptions"));
        allPgCntTab.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookPageCounts"));
        allGenreTab.setCellValueFactory(new PropertyValueFactory<Book, String>("mainGenres"));
        allRateTab.setCellValueFactory(new PropertyValueFactory<Book, Double>("bookRatings"));
        
        usernameTab.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        userFirstNameTab.setCellValueFactory(new PropertyValueFactory<User, String>("userFirstName"));
        userLastNameTab.setCellValueFactory(new PropertyValueFactory<User, String>("userLastName"));
        userBooksReadTotalTab.setCellValueFactory(new PropertyValueFactory<User, Integer>("userBooksReadTotal"));
        userTimesAccessedTab.setCellValueFactory(new PropertyValueFactory<User, Integer>("userTimesAccessed"));
        
        allTableView.setItems(Book.getBookItems());  //puts all book data into the table view
        userTableView.setItems(User.getUserItems());
    }    
    
}
