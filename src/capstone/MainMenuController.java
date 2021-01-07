package capstone;

import algorithm.BookData;
import algorithm.Centroid;
import algorithm.EuclideanDistance;
import algorithm.KMeans;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toSet;
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
    int userIDfromLogin = LoginScreenController.userIDtoShare;
    
    public static String userBooksReadList;
    public static String userBooksRatingList;
    public static String[] elements = {"0"};
    String[] bookTitleCluster;
    public static Integer userTotalBooksReadList;
    
    List<BookData> records = new ArrayList<>();
    Map<Centroid, List<BookData>> clusters;
    
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
    Button logOffButton1;
    @FXML
    Button logOffButton2;
    @FXML
    Button logOffButton3;
    @FXML
    Button logOffButton4;
    
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
    private void logOffButtonAction (ActionEvent event) throws IOException{
        Stage stage2 = (Stage) logOffButton1.getScene().getWindow();
        stage2.close();
        Stage stage3 = (Stage) logOffButton2.getScene().getWindow();
        stage3.close();
        Stage stage4 = (Stage) logOffButton3.getScene().getWindow();
        stage4.close();
        Stage stage5 = (Stage) logOffButton4.getScene().getWindow();
        stage5.close();
        Parent root = FXMLLoader.load(getClass().getResource("LogOff.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    
    int flag = 0;
    String[] tempy;
    String members;
    public void printClusterInfo(){
        clusters.forEach((key, value) -> {
        members = String.join(" ‽ ", value.stream().map(BookData::getBookTitle).collect(toSet()));  //uses interrobang to split each book title 
        tempy = members.split(" ‽ ");                                                               //since some books will use commas and single quotes
        for(int i=0; i<tempy.length;i++){       //checks to find which cluster has the user in it
            if(tempy[i].equals("USER")){
                flag = 1;           //flag flipped if user is in the cluster
            }
        }
            });
        if(flag == 1){      //sets the user's personal cluster to the cluster that will be parsed and displayed
            bookTitleCluster = members.split(" ‽ ");
        }
        else if (tempy.length == 1 && flag == 1){    //in case the user is in a lone cluster for some reason, it will at least give *some* recommendation
            bookTitleCluster = members.split(" ‽ ");
            System.out.println("NO MATCHES, RANDOM CLUSTER USED");
        }
        else{System.out.println("No matches");}
    }
    
    private Centroid sortedCentroid(Centroid key) { //sorts centroids
        List<Map.Entry<String, Double>> entries = new ArrayList<>(key.getCoordinates().entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        Map<String, Double> sorted = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }

        return new Centroid(sorted);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Book.recbookID.clear();
        Book.recbookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        Book.recbookAuthor.clear();
        Book.recbookDescription.clear();
        Book.recbookPageCount.clear();
        Book.recbookMainGenre.clear();
        Book.recbookRating.clear();
        
        Book.searchbookID.clear();
        Book.searchbookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        Book.searchbookAuthor.clear();
        Book.searchbookDescription.clear();
        Book.searchbookPageCount.clear();
        Book.searchbookMainGenre.clear();
        Book.searchbookRating.clear();
        
        Book.bookID.clear();
        Book.bookTitle.clear();    //clears all lists because if you don't, the data just stacks and shows repeats or innacurate data
        Book.bookAuthor.clear();
        Book.bookDescription.clear();
        Book.bookPageCount.clear();
        Book.bookMainGenre.clear();
        Book.bookRating.clear();
        
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
            String query = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre FROM capstone.books"; //gets relevant book data from database
            String bookQuery = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre,"
                    + "actionAdventureLvl, classicLvl, mysteryLvl, fantasyLvl, historicalFictionLvl,"
                    + "horrorLvl, thrillerLvl, romanceLvl, sciFiLvl, shortStoriesLvl, historyLvl, youngAdultLvl FROM capstone.books"; //gets relevant book data from database
            String userQuery2 = "SELECT actionAdventureLvl, classicLvl, mysteryLvl, fantasyLvl, historicalFictionLvl, horrorLvl, thrillerLvl, romanceLvl, sciFiLvl, "
                    + "shortStoriesLvl, historyLvl, youngAdultLvl FROM capstone.users WHERE userID = "+userIDfromLogin+";";
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
                ResultSet rs5 = stmt.executeQuery(bookQuery);       //this is used to put the books and genre information into the map for the clustering
                while (rs5.next()){
                    Map<String, Double> genreRatings = new HashMap<String, Double>();
                    genreRatings.put("Action/Aventure",rs5.getDouble(9));
                    genreRatings.put("Classic",rs5.getDouble(10));
                    genreRatings.put("Mystery",rs5.getDouble(11));
                    genreRatings.put("Fantasy",rs5.getDouble(12));
                    genreRatings.put("Historical Fiction",rs5.getDouble(13));
                    genreRatings.put("Horror",rs5.getDouble(14));
                    genreRatings.put("Thriller",rs5.getDouble(15));
                    genreRatings.put("Romance",rs5.getDouble(16));
                    genreRatings.put("Sci-Fi",rs5.getDouble(17));
                    genreRatings.put("Short Story",rs5.getDouble(18));
                    genreRatings.put("History",rs5.getDouble(19));
                    genreRatings.put("Young Adult",rs5.getDouble(20));
                    records.add(new BookData(rs5.getString(2), genreRatings));
                }
                ResultSet rs4 = stmt.executeQuery(userQuery2);              //this one is used to get the user's data in the form of a book for comparison to other books for clustering
                while(rs4.next()){
                    Map<String, Double> genreRatings = new HashMap<String, Double>();
                    genreRatings.put("Action/Aventure",rs4.getDouble(1));
                    genreRatings.put("Classic",rs4.getDouble(2));
                    genreRatings.put("Mystery",rs4.getDouble(3));
                    genreRatings.put("Fantasy",rs4.getDouble(4));
                    genreRatings.put("Historical Fiction",rs4.getDouble(5));
                    genreRatings.put("Horror",rs4.getDouble(6));
                    genreRatings.put("Thriller",rs4.getDouble(7));
                    genreRatings.put("Romance",rs4.getDouble(8));
                    genreRatings.put("Sci-Fi",rs4.getDouble(9));
                    genreRatings.put("Short Story",rs4.getDouble(10));
                    genreRatings.put("History",rs4.getDouble(11));
                    genreRatings.put("Young Adult",rs4.getDouble(12));
                    records.add(new BookData("USER", genreRatings));
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
                
                clusters = KMeans.fit(records, 4, new EuclideanDistance(), 1500);       //the big boy, the call to algorithm that makes the whole recommendation section display
                printClusterInfo();                                                     //first variable is the data for clustering, second is the number of clusters, next is the clustering
                int readFlag = 0;                                                       //centroid distance call, and the last is the amount of time it's run. The more the more accurate, but the longer it takes to run
                List<Integer> clusterIDs = new ArrayList<>();
                for(int i=0;i<bookTitleCluster.length;i++){
                    if(!bookTitleCluster[i].equals("USER")){    //checks to make sure the book ID is not going to be checked if the "book title" is the USER "book"
                        String temp = "SELECT bookID FROM capstone.books WHERE bookTitle = \""+bookTitleCluster[i]+"\""+";";
                        ResultSet rs7 = stmt.executeQuery(temp);
                        while(rs7.next()){
                            for(int j=0;j<elements.length;j++){
                                if(rs7.getInt(1) == Integer.parseInt(elements[j])){ //if the book is one that the user has read, don't add it
                                    readFlag = 1;
                                }
                            }
                            if(readFlag == 0){
                                clusterIDs.add(rs7.getInt(1));      //the list of book IDs that the user has not read to be put in the form
                            }
                        }
                    }
                }
                
                clusterIDs.sort(new Comparator<Integer>(){      //this is kind of a clunky sorting algorithm, but it used to sort the books by rating instead of being in order by bookID
                    public int compare(Integer thing1, Integer thing2){
                        String ratingGet = "SELECT bookRating FROM capstone.books WHERE bookID = "+thing1+";";
                        String ratingGet2 = "SELECT bookRating FROM capstone.books WHERE bookID = "+thing2+";";
                        Connection conn = null;
                        Statement stmt = null;
                        String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                        try {
                            conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
                            stmt = conn.createStatement();
                            ResultSet rs1 = stmt.executeQuery(ratingGet);
                            while(rs1.next()){
                                thing1 = rs1.getInt(1);
                            }
                            ResultSet rs2 = stmt.executeQuery(ratingGet2);
                            while(rs2.next()){
                                thing2 = rs2.getInt(1);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(thing1 < thing2){
                            return -1;  //if thing1's book rating is less than thing2's rating
                        }
                        else if(thing2 < thing1){
                            return 1; //if thing1's books rating is more than thing2's rating
                        }
                        else
                            return 0;
                    }
                });
                
                if(clusterIDs.size() >=7){    //as long as theres more than 7 books in the cluster, only show 7
                    for(int i=0;i<7;i++){
                        String recQuery2 = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre "
                                + "FROM capstone.books WHERE bookID = "+clusterIDs.get(i)+";";
                        ResultSet rs6 = stmt.executeQuery(recQuery2);
                        while(rs6.next()){
                            Book.recbookID.add(rs6.getInt(1));
                            Book.recbookTitle.add(rs6.getString(2));
                            Book.recbookAuthor.add(rs6.getString(3));
                            Book.recbookDescription.add(rs6.getString(4));
                            Book.recbookPageCount.add(rs6.getInt(5));
                            Book.recbookRating.add(rs6.getDouble(6));
                            Book.recbookTotalReads.add(rs6.getInt(7));
                            Book.recbookMainGenre.add(rs6.getString(8));
                        }
                    }
                }
                else{                                   //if there's less than 7 books in the cluster, display all of them
                    for(int i=0;i<clusterIDs.size();i++){
                        String recQuery2 = "SELECT bookID,bookTitle,bookAuthor,bookDescription,bookPageCount,bookRating,bookTotalReads,mainGenre "
                                + "FROM capstone.books WHERE bookID = "+clusterIDs.get(i)+";";
                        ResultSet rs6 = stmt.executeQuery(recQuery2);
                        while(rs6.next()){
                            Book.recbookID.add(rs6.getInt(1));
                            Book.recbookTitle.add(rs6.getString(2));
                            Book.recbookAuthor.add(rs6.getString(3));
                            Book.recbookDescription.add(rs6.getString(4));
                            Book.recbookPageCount.add(rs6.getInt(5));
                            Book.recbookRating.add(rs6.getDouble(6));
                            Book.recbookTotalReads.add(rs6.getInt(7));
                            Book.recbookMainGenre.add(rs6.getString(8));
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
        
        recTitleTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookTitles"));    //initializes the table columns to prepare them to retrieve the data
        recAuthTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookAuthors"));
        recDescTab.setCellValueFactory(new PropertyValueFactory<Book, String>("bookDescriptions"));
        recPgCntTab.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookPageCounts"));
        recGenreTab.setCellValueFactory(new PropertyValueFactory<Book, String>("mainGenres"));
        recRateTab.setCellValueFactory(new PropertyValueFactory<Book, Double>("bookRatings"));
        
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
        
        recTableView.setItems(Book.getRecItems()); //puts the recommended books into the table view
        allTableView.setItems(Book.getBookItems());  //puts all book data into the table view
        readTableView.setItems(Book.getReadItems()); //puts the read book data into the table view  
    }
}

