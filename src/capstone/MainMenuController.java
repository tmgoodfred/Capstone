/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class MainMenuController implements Initializable {

    List<Integer> bookID = new ArrayList<Integer>();
    List<String> bookTitle = new ArrayList<String>();
    List<String> bookAuthor = new ArrayList<String>();
    List<String> bookDescription = new ArrayList<String>();
    List<Integer> bookPageCount = new ArrayList<Integer>();
    List<Double> bookRating = new ArrayList<Double>();
    List<Integer> bookTotalReads = new ArrayList<Integer>();
    List<String> bookMainGenre = new ArrayList<String>();
    
    Integer[] bookIDArray = new Integer[bookID.size()];
    String[] bookTitleArray = new String[bookTitle.size()];
    String[] bookAuthorArray = new String[bookAuthor.size()];
    String[] bookDescriptionArray = new String[bookDescription.size()];
    Integer[] bookPageCountArray = new Integer[bookPageCount.size()];
    Double[] bookRatingArray = new Double[bookRating.size()];
    Integer[] bookTotalReadsArray = new Integer[bookTotalReads.size()];
    String[] bookMainGenreArray = new String[bookMainGenre.size()];
    
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
    
    private String bookTitles, bookAuthors, bookDescriptions, mainGenres;
    private Integer bookPageCounts;
    private Double bookRatings;
    
    public MainMenuController(){
    this.bookTitles = "";
    this.bookAuthors = "";
    this.bookDescriptions = "";
    this.bookPageCounts = 0;
    this.mainGenres = "";
    this.bookRatings = 0.0;
    }

    public MainMenuController (String bookTitles, String bookAuthors, String bookDescriptions, Integer bookPageCounts, String mainGenres, Double bookRatings){
    this.bookTitles = bookTitles;
    this.bookAuthors = bookAuthors;
    this.bookDescriptions = bookDescriptions;
    this.bookPageCounts = bookPageCounts;
    this.mainGenres = mainGenres;
    this.bookRatings = bookRatings;
    }
    
    public ObservableList<MainMenuController> getItems(){
        ObservableList<MainMenuController> items = FXCollections.observableArrayList();
        for(int i=0; i<bookID.size();i++){
            items.add(new MainMenuController(bookTitleArray[i],bookAuthorArray[i], bookDescriptionArray[i], bookPageCountArray[i], bookMainGenreArray[i],bookRatingArray[i]));
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
            String query = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre FROM capstone.books"; //gets book data from database
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
        
        allTableView.getColumns().clear();
        allTableView.getColumns().addAll(allTitleTab, allAuthTab, allDescTab, allPgCntTab, allGenreTab, allRateTab);
        
        bookIDArray = bookID.toArray(bookIDArray);
        bookTitleArray = bookTitle.toArray(bookTitleArray);
        bookAuthorArray = bookAuthor.toArray(bookAuthorArray);
        bookDescriptionArray = bookDescription.toArray(bookDescriptionArray);
        bookPageCountArray = bookPageCount.toArray(bookPageCountArray);
        bookRatingArray = bookRating.toArray(bookRatingArray);
        bookTotalReadsArray = bookTotalReads.toArray(bookTotalReadsArray);
        bookMainGenreArray = bookMainGenre.toArray(bookMainGenreArray);  
        
        allTitleTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookTitle"));
        allAuthTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookAuthor"));
        allDescTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("bookDescription"));
        allPgCntTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Integer>("bookPageCount"));
        allGenreTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, String>("mainGenre"));
        allRateTab.setCellValueFactory(new PropertyValueFactory<MainMenuController, Double>("bookRating"));
        
        //allTableView.getColumns().clear();
        allTableView.setItems(getItems());
        //allTableView.getColumns().addAll(allTitleTab, allAuthTab, allDescTab, allPgCntTab, allGenreTab, allRateTab);
        
    }  
    
}

