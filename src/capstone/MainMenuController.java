package capstone;

import capstone.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class MainMenuController implements Initializable {

    public static int bookIDtoShare;
    static int userIDfromLogin = LoginScreenController.userIDtoShare;
    
    public static String userBooksReadList;
    public static String userBooksRatingList;
    public static String[] elements = {"0"};
    public static Integer userTotalBooksReadList;
    
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
    TableColumn readTitleTab;
    @FXML
    TableColumn readAuthTab;
    @FXML
    TableColumn readDescTab;
    @FXML
    TableColumn readPgCntTab;
    @FXML
    TableColumn readGenreTab;
    @FXML
    TableColumn readRateTab;
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
    TableColumn recTitleTab;
    @FXML
    TableColumn recAuthTab;
    @FXML
    TableColumn recDescTab;
    @FXML
    TableColumn recPgCntTab;
    @FXML
    TableColumn recGenreTab;
    @FXML
    TableColumn recRateTab;
    
    @FXML
    TableView<Book> allTableView;
    @FXML
    TableView<Book> recTableView;
    @FXML
    TableView<Book> searchTableView;
    @FXML
    TableView<Book> readTableView;
    
    @FXML
    Button searchBtn;
    @FXML
    Button refreshBtn;
    @FXML
    Button refreshButton;
    @FXML
    TextField searchTxt;
    @FXML
    Button recReadMoreButton;
    @FXML
    Button allReadMoreButton;
    @FXML
    Button readReadMoreButton;
    @FXML
    Button searchReadMoreButton;
    
    @FXML
    private void refreshButtonAction (ActionEvent event){   //for when a user adds books to their read list after the form has been initialized initially. 
        Book.readbookID.clear();
        Book.readbookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        Book.readbookAuthor.clear();
        Book.readbookDescription.clear();
        Book.readbookPageCount.clear();
        Book.readbookMainGenre.clear();
        Book.readbookRating.clear();
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
            Statement stmt = null;
            String userQuery = "SELECT userBooksRead, userBooksRating, userBooksReadTotal FROM capstone.users WHERE userID = "+userIDfromLogin+";";
            try {
                stmt = conn.createStatement();
                ResultSet rs2 = stmt.executeQuery(userQuery);   //gets the users read list
                while(rs2.next())
                {
                    userBooksReadList = rs2.getString(1);   //these are actually strings not lists despite the naming
                    userBooksRatingList = rs2.getString(2);
                    userTotalBooksReadList = rs2.getInt(3);
                }
                    elements = userBooksReadList.split(",");    //splits the string of books read by a comma delimiter
                    List<String> fixedLengthList = Arrays.asList(elements);
                    ArrayList<String> listOfString = new ArrayList<String>(fixedLengthList);
                    for(int i=0;i<Book.bookTitle.size();i++){
                        for(int j=0;j<listOfString.size();j++){ //I hate that this is O(n^2).
                            if(Book.bookID.get(i).equals(Integer.parseInt(listOfString.get(j)))) //this compares each bookID to the list of books read by the user
                            {
                                Book.readbookID.add(Book.bookID.get(i));
                                Book.readbookTitle.add(Book.bookTitle.get(i));
                                Book.readbookAuthor.add(Book.bookAuthor.get(i));
                                Book.readbookDescription.add(Book.bookDescription.get(i));
                                Book.readbookPageCount.add(Book.bookPageCount.get(i));
                                Book.readbookMainGenre.add(Book.bookMainGenre.get(i));
                                Book.readbookRating.add(Book.bookRating.get(i));
                            }
                        }
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
        readTableView.setItems(Book.getReadItems()); //puts the read book data into the table view
    }
    
    @FXML
    private void allreadMoreButtonAction (ActionEvent event) throws IOException{
        Book person = allTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void searchreadMoreButtonAction (ActionEvent event) throws IOException{
        Book person = searchTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void readreadMoreButtonAction (ActionEvent event) throws IOException{
        Book person = readTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void recreadMoreButtonAction (ActionEvent event) throws IOException{
        Book person = recTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void submitButtonAction(ActionEvent event) throws IOException { //submit button for the search tab
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
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
            Statement stmt = null;
            String query = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre FROM capstone.books"; //gets relevant book data from database
            String userQuery = "SELECT userBooksRead, userBooksRating, userBooksReadTotal FROM capstone.users WHERE userID = "+userIDfromLogin+";";
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
                ResultSet rs2 = stmt.executeQuery(userQuery);   //gets the users read list
                while(rs2.next())
                {
                    userBooksReadList = rs2.getString(1);   //these are actually strings not lists despite the naming
                    userBooksRatingList = rs2.getString(2);
                    userTotalBooksReadList = rs2.getInt(3);
                }
                if(!userBooksReadList.equals("0")){
                    elements = userBooksReadList.split(",");    //splits the string of books read by a comma delimiter
                    List<String> fixedLengthList = Arrays.asList(elements);
                    ArrayList<String> listOfString = new ArrayList<String>(fixedLengthList);
                    for(int i=0;i<Book.bookTitle.size();i++){
                        for(int j=0;j<listOfString.size();j++){ //I hate that this is O(n^2).
                            if(Book.bookID.get(i).equals(Integer.parseInt(listOfString.get(j)))) //this compares each bookID to the list of books read by the user
                            {
                                Book.readbookID.add(Book.bookID.get(i));
                                Book.readbookTitle.add(Book.bookTitle.get(i));
                                Book.readbookAuthor.add(Book.bookAuthor.get(i));
                                Book.readbookDescription.add(Book.bookDescription.get(i));
                                Book.readbookPageCount.add(Book.bookPageCount.get(i));
                                Book.readbookMainGenre.add(Book.bookMainGenre.get(i));
                                Book.readbookRating.add(Book.bookRating.get(i));
                            }
                        }
                    }
                } //end of if statement
                else{
                    elements = userBooksReadList.split(""); //if the users books read are = 0, split it by "nothing"
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
        
        readTitleTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitles")); //initializes the table columns to prepare them to retrieve the data
        readAuthTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookAuthors"));
        readDescTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookDescriptions"));
        readPgCntTab.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookPageCounts"));
        readGenreTab.setCellValueFactory(new PropertyValueFactory<Book, String>("mainGenres"));
        readRateTab.setCellValueFactory(new PropertyValueFactory<Book, Double>("bookRatings"));
        
        allTableView.setItems(Book.getBookItems());  //puts all book data into the table view
        readTableView.setItems(Book.getReadItems()); //puts the read book data into the table view
        
    }
}

