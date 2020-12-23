package capstone;

import capstone.User;
import capstone.Book;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    TableView<Book> searchTableView;
    
    @FXML
    TextField searchTxt;
    
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
    @FXML
    TableColumn searchTitleTab;
    @FXML
    TableColumn searchAuthTab;
    @FXML
    TableColumn searchDescTab;
    @FXML
    TableColumn searchPgCntTab;
    @FXML
    TableColumn searchGenreTab;
    @FXML
    TableColumn searchRateTab;
    
    @FXML
    Button addBookButton;
    @FXML
    Button editBookButton;
    @FXML
    Button deleteBookButton;
    @FXML
    Button searchAddBookButton;
    @FXML
    Button searchEditBookButton;
    @FXML
    Button searchDeleteBookButton;
    @FXML
    Button searchBtn;
    @FXML
    Button addUserButton;
    @FXML
    Button editUserButton;
    @FXML
    Button deleteUserButton;
    
    public static int bookIDtoShare;
    public static int userIDtoShare;
    public static String bookNametoShare;
    
    @FXML
    public void addButtonAction(ActionEvent event) throws IOException{
        Stage stage2 = (Stage) addBookButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void editButtonAction(ActionEvent event) throws IOException{
        Book person = allTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        bookNametoShare = person.bookTitles;
        Stage stage2 = (Stage) editBookButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("EditBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void deleteButtonAction(ActionEvent event) throws IOException{
        Book person = allTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        Stage stage2 = (Stage) deleteBookButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void searchAddButtonAction(ActionEvent event) throws IOException{
        Stage stage2 = (Stage) addBookButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void searchEditButtonAction(ActionEvent event) throws IOException{
        Book person = searchTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        bookNametoShare = person.bookTitles;
        Stage stage2 = (Stage) editBookButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("EditBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void searchDeleteButtonAction(ActionEvent event) throws IOException{
        Book person = searchTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        Stage stage2 = (Stage) deleteBookButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteBook.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void addUserButtonAction(ActionEvent event) throws IOException{
        Stage stage2 = (Stage) addUserButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("CreateAccountScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void editUserButtonAction(ActionEvent event) throws IOException{
        User person = userTableView.getSelectionModel().getSelectedItem();
        userIDtoShare = person.userID;
        Stage stage2 = (Stage) editUserButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("EditUser.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void deleteUserButtonAction(ActionEvent event) throws IOException{
        User person = userTableView.getSelectionModel().getSelectedItem();
        userIDtoShare = person.userID;
        Stage stage2 = (Stage) deleteBookButton.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteUser.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void searchButtonAction(ActionEvent event) throws IOException { //submit button for the search tab
        Book.searchbookID.clear();
        Book.searchbookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        Book.searchbookAuthor.clear();
        Book.searchbookDescription.clear();
        Book.searchbookPageCount.clear();
        Book.searchbookMainGenre.clear();
        Book.searchbookRating.clear();
        String searchTerm = searchTxt.getText();    //gets the data from the search bar
        for(int i=0; i<Book.bookTitle.size();i++){   //iterates over each book 
            {
                if(Book.bookTitle.get(i).toLowerCase().contains(searchTerm.toLowerCase()) || Book.bookAuthor.get(i).toLowerCase().contains(searchTerm.toLowerCase()) 
                        || Book.bookDescription.get(i).toLowerCase().contains(searchTerm.toLowerCase()) || Book.bookMainGenre.get(i).toLowerCase().contains(searchTerm.toLowerCase()))
                {   //the above line is comparing the search terms to titles, authors, descriptions, and or genres and converts it all to lower case for accurate information retrieval. Otherwise Life would not match life or vice versa
                    Book.searchbookID.add(Book.bookID.get(i));
                    Book.searchbookTitle.add(Book.bookTitle.get(i));
                    Book.searchbookAuthor.add(Book.bookAuthor.get(i));
                    Book.searchbookDescription.add(Book.bookDescription.get(i));
                    Book.searchbookPageCount.add(Book.bookPageCount.get(i));
                    Book.searchbookMainGenre.add(Book.bookMainGenre.get(i));
                    Book.searchbookRating.add(Book.bookRating.get(i));
                }
                
            }
        }
        searchTableView.setItems(Book.getSearchItems()); //puts the data from the searched books list into to table view
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Book.bookID.clear();
        Book.bookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        Book.bookAuthor.clear();
        Book.bookDescription.clear();
        Book.bookPageCount.clear();
        Book.bookMainGenre.clear();
        Book.bookRating.clear();
        
        User.userIDL.clear();
        User.usernameL.clear();
        User.userFirstNameL.clear();
        User.userLastNameL.clear();
        User.userBooksReadTotalL.clear();
        User.userTimesAccessedL.clear();
        
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
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
        
        searchTitleTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitles")); //initializes the table columns to prepare them to retrieve the data
        searchAuthTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookAuthors"));
        searchDescTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookDescriptions"));
        searchPgCntTab.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookPageCounts"));
        searchGenreTab.setCellValueFactory(new PropertyValueFactory<Book, String>("mainGenres"));
        searchRateTab.setCellValueFactory(new PropertyValueFactory<Book, Double>("bookRatings"));
        
        usernameTab.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        userFirstNameTab.setCellValueFactory(new PropertyValueFactory<User, String>("userFirstName"));
        userLastNameTab.setCellValueFactory(new PropertyValueFactory<User, String>("userLastName"));
        userBooksReadTotalTab.setCellValueFactory(new PropertyValueFactory<User, Integer>("userBooksReadTotal"));
        userTimesAccessedTab.setCellValueFactory(new PropertyValueFactory<User, Integer>("userTimesAccessed"));
        
        allTableView.setItems(Book.getBookItems());  //puts all book data into the table view
        userTableView.setItems(User.getUserItems());
    }    
    
}
