package capstone;

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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    List<Integer> bookID = new ArrayList<>();    //lists to store book data to be added to the tableviews
    List<String> bookTitle = new ArrayList<>();
    List<String> bookAuthor = new ArrayList<>();
    List<String> bookDescription = new ArrayList<>();
    List<Integer> bookPageCount = new ArrayList<>();
    List<Double> bookRating = new ArrayList<>();
    List<Integer> bookTotalReads = new ArrayList<>();
    List<String> bookMainGenre = new ArrayList<>();
    
    List<Integer> searchbookID = new ArrayList<>();  //lists to store book data to be added to the tableviews
    List<String> searchbookTitle = new ArrayList<>();
    List<String> searchbookAuthor = new ArrayList<>();
    List<String> searchbookDescription = new ArrayList<>();
    List<Integer> searchbookPageCount = new ArrayList<>();
    List<Double> searchbookRating = new ArrayList<>();
    List<Integer> searchbookTotalReads = new ArrayList<>();
    List<String> searchbookMainGenre = new ArrayList<>();
    
    List<Integer> readbookID = new ArrayList<>();  //lists to store book data to be added to the tableviews
    List<String> readbookTitle = new ArrayList<>();
    List<String> readbookAuthor = new ArrayList<>();
    List<String> readbookDescription = new ArrayList<>();
    List<Integer> readbookPageCount = new ArrayList<>();
    List<Double> readbookRating = new ArrayList<>();
    List<Integer> readbookTotalReads = new ArrayList<>();
    List<String> readbookMainGenre = new ArrayList<>();
    
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
    TableView<MainMenuController> allTableView;
    @FXML
    TableView<MainMenuController> recTableView;
    @FXML
    TableView<MainMenuController> searchTableView;
    @FXML
    TableView<MainMenuController> readTableView;
    
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
    
    private String bookTitles, bookAuthors, bookDescriptions, mainGenres;   //variables for the generic class
    private Integer bookIDs, bookPageCounts;
    private Double bookRatings;
    
    public MainMenuController(){    //the generic class for books to be added to the tableviews
    this.bookIDs = 0;
    this.bookTitles = "";
    this.bookAuthors = "";
    this.bookDescriptions = "";
    this.bookPageCounts = 0;
    this.mainGenres = "";
    this.bookRatings = 0.0;
    }

    public MainMenuController (Integer bookIDs, String bookTitles, String bookAuthors, String bookDescriptions, Integer bookPageCounts, String mainGenres, Double bookRatings){ //the generic class for books to be added to the tableviews
    this.bookIDs = bookIDs;
    this.bookTitles = bookTitles;
    this.bookAuthors = bookAuthors;
    this.bookDescriptions = bookDescriptions;
    this.bookPageCounts = bookPageCounts;
    this.mainGenres = mainGenres;
    this.bookRatings = bookRatings;
    }
    
    public IntegerProperty bookIDProperty(){    //these are used to store the data from the books into each column
        return new SimpleIntegerProperty(bookIDs);
    }
    public StringProperty bookTitlesProperty(){
        return new SimpleStringProperty(bookTitles);
    }
    public StringProperty bookAuthorsProperty(){
        return new SimpleStringProperty(bookAuthors);
    }
    public StringProperty bookDescriptionsProperty(){
        return new SimpleStringProperty(bookDescriptions);
    }
    public IntegerProperty bookPageCountsProperty(){
        return new SimpleIntegerProperty(bookPageCounts);
    }
    public StringProperty mainGenresProperty(){
        return new SimpleStringProperty(mainGenres);
    }
    public DoubleProperty bookRatingsProperty(){
        return new SimpleDoubleProperty(bookRatings);
    }
    
    
    public ObservableList<MainMenuController> getItems(){   //gets all the books and stores them into one observable list - needed for putting into a table column
        ObservableList<MainMenuController> items = FXCollections.observableArrayList();
        for(int i=0; i<bookID.size();i++){
            items.add(new MainMenuController(bookID.get(i), bookTitle.get(i), bookAuthor.get(i), bookDescription.get(i), bookPageCount.get(i), bookMainGenre.get(i),bookRating.get(i)));
        }
        return items;
    }
    
    public ObservableList<MainMenuController> getSearchItems(){ //gets specifically the items that match the searched term on the search tab into one observable list - needed for putting into a table column
        ObservableList<MainMenuController> items2 = FXCollections.observableArrayList();
        for(int i=0; i<searchbookTitle.size();i++){
            items2.add(new MainMenuController(searchbookID.get(i), searchbookTitle.get(i), searchbookAuthor.get(i), searchbookDescription.get(i), searchbookPageCount.get(i), searchbookMainGenre.get(i),searchbookRating.get(i)));
        }
        return items2;
    }
    public ObservableList<MainMenuController> getReadItems(){ //gets the items read by the users
        ObservableList<MainMenuController> items3 = FXCollections.observableArrayList();
        for(int i=0; i<readbookTitle.size();i++){
            items3.add(new MainMenuController(readbookID.get(i), readbookTitle.get(i), readbookAuthor.get(i), readbookDescription.get(i), readbookPageCount.get(i), readbookMainGenre.get(i),readbookRating.get(i)));
        }
        return items3;
    }
    
    @FXML
    private void refreshButtonAction (ActionEvent event){   //for when a user adds books to their read list after the form has been initialized initially. 
        readbookID.clear();
        readbookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        readbookAuthor.clear();
        readbookDescription.clear();
        readbookPageCount.clear();
        readbookMainGenre.clear();
        readbookRating.clear();
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rootpass1");
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
                    for(int i=0;i<bookTitle.size();i++){
                        for(int j=0;j<listOfString.size();j++){ //I hate that this is O(n^2).
                            if(bookID.get(i).equals(Integer.parseInt(listOfString.get(j)))) //this compares each bookID to the list of books read by the user
                            {
                                readbookID.add(bookID.get(i));
                                readbookTitle.add(bookTitle.get(i));
                                readbookAuthor.add(bookAuthor.get(i));
                                readbookDescription.add(bookDescription.get(i));
                                readbookPageCount.add(bookPageCount.get(i));
                                readbookMainGenre.add(bookMainGenre.get(i));
                                readbookRating.add(bookRating.get(i));
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
        readTableView.setItems(getReadItems()); //puts the read book data into the table view
    }
    
    @FXML
    private void allreadMoreButtonAction (ActionEvent event) throws IOException{
        MainMenuController person = allTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void searchreadMoreButtonAction (ActionEvent event) throws IOException{
        MainMenuController person = searchTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void readreadMoreButtonAction (ActionEvent event) throws IOException{
        MainMenuController person = readTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void recreadMoreButtonAction (ActionEvent event) throws IOException{
        MainMenuController person = recTableView.getSelectionModel().getSelectedItem();
        bookIDtoShare = person.bookIDs;
        
        Parent root = FXMLLoader.load(getClass().getResource("MoreBookInfo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void submitButtonAction(ActionEvent event) throws IOException { //submit button for the search tab
        searchbookID.clear();
        searchbookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        searchbookAuthor.clear();
        searchbookDescription.clear();
        searchbookPageCount.clear();
        searchbookMainGenre.clear();
        searchbookRating.clear();
        String searchTerm = searchTxt.getText();    //gets the data from the search bar
        for(int i=0; i<bookTitle.size();i++){   //iterates over each book 
            {
                if(bookTitle.get(i).toLowerCase().contains(searchTerm.toLowerCase()) || bookAuthor.get(i).toLowerCase().contains(searchTerm.toLowerCase()) || bookDescription.get(i).toLowerCase().contains(searchTerm.toLowerCase()))
                {   //the above line is comparing the search terms to titles, authors, and or descriptions,and converts it all to lower case for accurate information retrieval. Otherwise Life would not match life or vice versa
                    searchbookID.add(bookID.get(i));
                    searchbookTitle.add(bookTitle.get(i));
                    searchbookAuthor.add(bookAuthor.get(i));
                    searchbookDescription.add(bookDescription.get(i));
                    searchbookPageCount.add(bookPageCount.get(i));
                    searchbookMainGenre.add(bookMainGenre.get(i));
                    searchbookRating.add(bookRating.get(i));
                }
                
            }
        }
        searchTableView.setItems(getSearchItems()); //puts the data from the searched books list into to table view
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rootpass1");
            Statement stmt = null;
            String query = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre FROM capstone.books"; //gets relevant book data from database
            String userQuery = "SELECT userBooksRead, userBooksRating, userBooksReadTotal FROM capstone.users WHERE userID = "+userIDfromLogin+";";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    bookID.add(rs.getInt(1));
                    bookTitle.add(rs.getString(2));
                    bookAuthor.add(rs.getString(3));
                    bookDescription.add(rs.getString(4));
                    bookPageCount.add(rs.getInt(5));
                    bookRating.add(rs.getDouble(6));
                    bookTotalReads.add(rs.getInt(7));
                    bookMainGenre.add(rs.getString(8));
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
                    for(int i=0;i<bookTitle.size();i++){
                        for(int j=0;j<listOfString.size();j++){ //I hate that this is O(n^2).
                            if(bookID.get(i).equals(Integer.parseInt(listOfString.get(j)))) //this compares each bookID to the list of books read by the user
                            {
                                readbookID.add(bookID.get(i));
                                readbookTitle.add(bookTitle.get(i));
                                readbookAuthor.add(bookAuthor.get(i));
                                readbookDescription.add(bookDescription.get(i));
                                readbookPageCount.add(bookPageCount.get(i));
                                readbookMainGenre.add(bookMainGenre.get(i));
                                readbookRating.add(bookRating.get(i));
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
        
        allTitleTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookTitles"));    //initializes the table columns to prepare them to retrieve the data
        allAuthTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookAuthors"));
        allDescTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookDescriptions"));
        allPgCntTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Integer>("bookPageCounts"));
        allGenreTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("mainGenres"));
        allRateTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Double>("bookRatings"));
        
        searchTitleTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookTitles")); //initializes the table columns to prepare them to retrieve the data
        searchAuthTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookAuthors"));
        searchDescTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookDescriptions"));
        searchPgCntTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Integer>("bookPageCounts"));
        searchGenreTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("mainGenres"));
        searchRateTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Double>("bookRatings"));
        
        readTitleTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookTitles")); //initializes the table columns to prepare them to retrieve the data
        readAuthTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookAuthors"));
        readDescTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookDescriptions"));
        readPgCntTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Integer>("bookPageCounts"));
        readGenreTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("mainGenres"));
        readRateTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Double>("bookRatings"));
        
        allTableView.setItems(getItems());  //puts all book data into the table view
        readTableView.setItems(getReadItems()); //puts the read book data into the table view
        
    }
}

