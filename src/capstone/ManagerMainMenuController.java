package capstone;

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
    TableView<ManagerMainMenuController> allTableView;
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
    TableColumn userTimesAccessed;
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
    
    List<Integer> bookID = new ArrayList<>();    //lists to store book data to be added to the tableviews
    List<String> bookTitle = new ArrayList<>();
    List<String> bookAuthor = new ArrayList<>();
    List<String> bookDescription = new ArrayList<>();
    List<Integer> bookPageCount = new ArrayList<>();
    List<Double> bookRating = new ArrayList<>();
    List<Integer> bookTotalReads = new ArrayList<>();
    List<String> bookMainGenre = new ArrayList<>();
    
    private String bookTitles, bookAuthors, bookDescriptions, mainGenres;   //variables for the generic class
    private Integer bookIDs, bookPageCounts;
    private Double bookRatings;
    
    public ManagerMainMenuController(){    //the generic class for books to be added to the tableviews
    this.bookIDs = 0;
    this.bookTitles = "";
    this.bookAuthors = "";
    this.bookDescriptions = "";
    this.bookPageCounts = 0;
    this.mainGenres = "";
    this.bookRatings = 0.0;
    }

    public ManagerMainMenuController (Integer bookIDs, String bookTitles, String bookAuthors, String bookDescriptions, Integer bookPageCounts, String mainGenres, Double bookRatings){ //the generic class for books to be added to the tableviews
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
    
    public ObservableList<ManagerMainMenuController> getItems(){   //gets all the books and stores them into one observable list - needed for putting into a table column
        ObservableList<ManagerMainMenuController> items = FXCollections.observableArrayList();
        for(int i=0; i<bookID.size();i++){
            items.add(new ManagerMainMenuController(bookID.get(i), bookTitle.get(i), bookAuthor.get(i), bookDescription.get(i), bookPageCount.get(i), bookMainGenre.get(i),bookRating.get(i)));
        }
        return items;
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
        
        allTableView.setItems(getItems());  //puts all book data into the table view
    }    
    
}
