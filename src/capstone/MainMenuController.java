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
import javafx.collections.ObservableList;

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
        
        Integer[] bookIDArray = new Integer[bookID.size()];
        bookIDArray = bookID.toArray(bookIDArray);
        
        String[] bookTitleArray = new String[bookTitle.size()];
        bookTitleArray = bookTitle.toArray(bookTitleArray);
        
        String[] bookAuthorArray = new String[bookAuthor.size()];
        bookAuthorArray = bookAuthor.toArray(bookAuthorArray);
        
        String[] bookDescriptionArray = new String[bookDescription.size()];
        bookDescriptionArray = bookDescription.toArray(bookDescriptionArray);
        
        Integer[] bookPageCountArray = new Integer[bookPageCount.size()];
        bookPageCountArray = bookPageCount.toArray(bookPageCountArray);
        
        Double[] bookRatingArray = new Double[bookRating.size()];
        bookRatingArray = bookRating.toArray(bookRatingArray);
        
        Integer[] bookTotalReadsArray = new Integer[bookTotalReads.size()];
        bookTotalReadsArray = bookTotalReads.toArray(bookTotalReadsArray);
        
        String[] bookMainGenreArray = new String[bookMainGenre.size()];
        bookMainGenreArray = bookMainGenre.toArray(bookMainGenreArray);
        
        for(int i=0;i<bookID.size();i++)
        {
            //test
            /*allTitleTab.getColumns().addAll(bookTitleArray);
            allAuthTab.getColumns().addAll(bookAuthorArray);
            allDescTab.getColumns().addAll(bookDescriptionArray);
            allPgCntTab.getColumns().addAll(bookPageCountArray);
            allGenreTab.getColumns().addAll(bookMainGenreArray);
            allRateTab.getColumns().addAll(bookRatingArray);*/
        }
        
    }    
    
}
