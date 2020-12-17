package capstone;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tyler Goodfred
 */
public class Book {
    static List<Integer> bookID = new ArrayList<>();    //lists to store book data to be added to the tableviews
    static List<String> bookTitle = new ArrayList<>();
    static List<String> bookAuthor = new ArrayList<>();
    static List<String> bookDescription = new ArrayList<>();
    static List<Integer> bookPageCount = new ArrayList<>();
    static List<Double> bookRating = new ArrayList<>();
    static List<Integer> bookTotalReads = new ArrayList<>();
    static List<String> bookMainGenre = new ArrayList<>();
    
    static List<Integer> searchbookID = new ArrayList<>();  //lists to store book data to be added to the tableviews
    static List<String> searchbookTitle = new ArrayList<>();
    static List<String> searchbookAuthor = new ArrayList<>();
    static List<String> searchbookDescription = new ArrayList<>();
    static List<Integer> searchbookPageCount = new ArrayList<>();
    static List<Double> searchbookRating = new ArrayList<>();
    static List<Integer> searchbookTotalReads = new ArrayList<>();
    static List<String> searchbookMainGenre = new ArrayList<>();
    
    static List<Integer> readbookID = new ArrayList<>();  //lists to store book data to be added to the tableviews
    static List<String> readbookTitle = new ArrayList<>();
    static List<String> readbookAuthor = new ArrayList<>();
    static List<String> readbookDescription = new ArrayList<>();
    static List<Integer> readbookPageCount = new ArrayList<>();
    static List<Double> readbookRating = new ArrayList<>();
    static List<Integer> readbookTotalReads = new ArrayList<>();
    static List<String> readbookMainGenre = new ArrayList<>();
    
    //private String bookTitles, bookAuthors, bookDescriptions, mainGenres;   //variables for the generic class
    //private Integer bookIDs, bookPageCounts;
    //private Double bookRatings;
    public String bookTitles, bookAuthors, bookDescriptions, mainGenres;   //variables for the generic class
    public Integer bookIDs, bookPageCounts;
    public Double bookRatings;
    
    public Book(){    //the generic class for books to be added to the tableviews
    this.bookIDs = 0;
    this.bookTitles = "";
    this.bookAuthors = "";
    this.bookDescriptions = "";
    this.bookPageCounts = 0;
    this.mainGenres = "";
    this.bookRatings = 0.0;
    }
    
    public Book(Integer bookIDs, String bookTitles, String bookAuthors, String bookDescriptions, Integer bookPageCounts, String mainGenres, Double bookRatings){ //the generic class for books to be added to the tableviews
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
    
    public static ObservableList<Book> getBookItems(){   //gets all the books and stores them into one observable list - needed for putting into a table column
        ObservableList<Book> items = FXCollections.observableArrayList();
        for(int i=0; i<bookID.size();i++){
            items.add(new Book(bookID.get(i), bookTitle.get(i), bookAuthor.get(i), bookDescription.get(i), bookPageCount.get(i), bookMainGenre.get(i),bookRating.get(i)));
        }
        return items;
    }
    
    public static ObservableList<Book> getSearchItems(){ //gets specifically the items that match the searched term on the search tab into one observable list - needed for putting into a table column
        ObservableList<Book> items2 = FXCollections.observableArrayList();
        for(int i=0; i<searchbookTitle.size();i++){
            items2.add(new Book(searchbookID.get(i), searchbookTitle.get(i), searchbookAuthor.get(i), searchbookDescription.get(i), searchbookPageCount.get(i), searchbookMainGenre.get(i),searchbookRating.get(i)));
        }
        return items2;
    }
    public static ObservableList<Book> getReadItems(){ //gets the items read by the users
        ObservableList<Book> items3 = FXCollections.observableArrayList();
        for(int i=0; i<readbookTitle.size();i++){
            items3.add(new Book(readbookID.get(i), readbookTitle.get(i), readbookAuthor.get(i), readbookDescription.get(i), readbookPageCount.get(i), readbookMainGenre.get(i),readbookRating.get(i)));
        }
        return items3;
    }
}
