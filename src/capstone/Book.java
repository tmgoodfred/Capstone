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
    
    private String bookTitles, bookAuthors, bookDescriptions, mainGenres;   //variables for the generic class
    private Integer bookIDs, bookPageCounts;
    private Double bookRatings;
    
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
    
    public ObservableList<Book> getItems(){   //gets all the books and stores them into one observable list - needed for putting into a table column
        ObservableList<Book> items = FXCollections.observableArrayList();
        for(int i=0; i<bookID.size();i++){
            items.add(new Book(bookID.get(i), bookTitle.get(i), bookAuthor.get(i), bookDescription.get(i), bookPageCount.get(i), bookMainGenre.get(i),bookRating.get(i)));
        }
        return items;
    }
}
