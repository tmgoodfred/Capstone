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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class MainMenuController implements Initializable {

    List<Integer> bookID = new ArrayList<Integer>();    //lists to store book data to be added to the tableviews
    List<String> bookTitle = new ArrayList<String>();
    List<String> bookAuthor = new ArrayList<String>();
    List<String> bookDescription = new ArrayList<String>();
    List<Integer> bookPageCount = new ArrayList<Integer>();
    List<Double> bookRating = new ArrayList<Double>();
    List<Integer> bookTotalReads = new ArrayList<Integer>();
    List<String> bookMainGenre = new ArrayList<String>();
    
    List<Integer> searchbookID = new ArrayList<Integer>();  //lists to store book data to be added to the tableviews
    List<String> searchbookTitle = new ArrayList<String>();
    List<String> searchbookAuthor = new ArrayList<String>();
    List<String> searchbookDescription = new ArrayList<String>();
    List<Integer> searchbookPageCount = new ArrayList<Integer>();
    List<Double> searchbookRating = new ArrayList<Double>();
    List<Integer> searchbookTotalReads = new ArrayList<Integer>();
    List<String> searchbookMainGenre = new ArrayList<String>();
    
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
    Button searchBtn;
    @FXML
    Button refreshBtn;
    @FXML
    TextField searchTxt;
    
    private String bookTitles, bookAuthors, bookDescriptions, mainGenres;   //variables for the generic class
    private Integer bookPageCounts;
    private Double bookRatings;
    
    public MainMenuController(){    //the generic class for books to be added to the tableviews
    this.bookTitles = "";
    this.bookAuthors = "";
    this.bookDescriptions = "";
    this.bookPageCounts = 0;
    this.mainGenres = "";
    this.bookRatings = 0.0;
    }

    public MainMenuController (String bookTitles, String bookAuthors, String bookDescriptions, Integer bookPageCounts, String mainGenres, Double bookRatings){ //the generic class for books to be added to the tableviews
    this.bookTitles = bookTitles;
    this.bookAuthors = bookAuthors;
    this.bookDescriptions = bookDescriptions;
    this.bookPageCounts = bookPageCounts;
    this.mainGenres = mainGenres;
    this.bookRatings = bookRatings;
    }
       
    public StringProperty bookTitlesProperty(){ //these are used to store the data from the books into each column
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
            items.add(new MainMenuController(bookTitle.get(i), bookAuthor.get(i), bookDescription.get(i), bookPageCount.get(i), bookMainGenre.get(i),bookRating.get(i)));
        }
        return items;
    }
    
    public ObservableList<MainMenuController> getSearchItems(){ //gets specifically the items that match the searched term on the search tab into one observable list - needed for putting into a table column
        ObservableList<MainMenuController> items2 = FXCollections.observableArrayList();
        for(int i=0; i<searchbookTitle.size();i++){
            items2.add(new MainMenuController(searchbookTitle.get(i), searchbookAuthor.get(i), searchbookDescription.get(i), searchbookPageCount.get(i), searchbookMainGenre.get(i),searchbookRating.get(i)));
        }
        return items2;
    }

    @FXML
    private void submitButtonAction(ActionEvent event) throws IOException { //submit button for the search tab
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
        
        allTableView.setItems(getItems());  //puts all book data into the table view
        
    }
}

