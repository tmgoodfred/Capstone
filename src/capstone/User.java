package capstone;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tyler Goodfred
 */
public class User {
    
    static List<Integer> userIDL = new ArrayList<>();    //lists to store book data to be added to the tableviews
    static List<String> usernameL = new ArrayList<>();
    static List<String> userFirstNameL = new ArrayList<>();
    static List<String> userLastNameL = new ArrayList<>();
    static List<Integer> userBooksReadTotalL = new ArrayList<>();
    static List<Integer> userTimesAccessedL = new ArrayList<>();
    
    public String username, userFirstName, userLastName;   //variables for the generic class
    public Integer userID, userBooksReadTotal, userTimesAccessed;
    
    public User(){    //the generic class for users to be added to the tableviews
    this.userID = 0;
    this.username = "";
    this.userFirstName = "";
    this.userLastName = "";
    this.userBooksReadTotal = 0;
    this.userTimesAccessed = 0;
    }

    public User (Integer userID, String username, String userFirstName, String userLastName, Integer userBooksReadTotal, Integer userTimesAccessed){ //the generic class for users to be added to the tableviews
    this.userID = userID;
    this.username = username;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.userBooksReadTotal = userBooksReadTotal;
    this.userTimesAccessed = userTimesAccessed;
    }
    
    
    public IntegerProperty userIDProperty(){    //these are used to store the data from the users into each column
        return new SimpleIntegerProperty(userID);
    }
    public StringProperty usernameProperty(){
        return new SimpleStringProperty(username);
    }
    public StringProperty userFirstNameProperty(){
        return new SimpleStringProperty(userFirstName);
    }
    public StringProperty userLastNameProperty(){
        return new SimpleStringProperty(userLastName);
    }
    public IntegerProperty userBooksReadTotalProperty(){
        return new SimpleIntegerProperty(userBooksReadTotal);
    }
    public IntegerProperty userTimesAccessedProperty(){
        return new SimpleIntegerProperty(userTimesAccessed);
    }
    
    public static ObservableList<User> getUserItems(){   //gets all the users and stores them into one observable list - needed for putting into a table column
        ObservableList<User> items = FXCollections.observableArrayList();
        for(int i=0; i<userIDL.size();i++){
            items.add(new User(userIDL.get(i), usernameL.get(i), userFirstNameL.get(i), userLastNameL.get(i), userBooksReadTotalL.get(i), userTimesAccessedL.get(i)));
        }
        return items;
    }
}
