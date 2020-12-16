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

/**
 *
 * @author Tyler Goodfred
 */
/*
        TableColumn usernameTab
        TableColumn userFirstNameTab
        TableColumn userLastNameTab
        TableColumn userBooksReadTotalTab
        TableColumn userTimesAccessed
*/
public class User {
    
    List<Integer> userIDL = new ArrayList<>();    //lists to store book data to be added to the tableviews
    List<String> usernameL = new ArrayList<>();
    List<String> userFirstNameL = new ArrayList<>();
    List<String> userLastNameL = new ArrayList<>();
    List<Integer> userBooksReadTotalL = new ArrayList<>();
    List<Integer> userTimesAccessedL = new ArrayList<>();
    
    private String username, userFirstName, userLastName;   //variables for the generic class
    private Integer userID, userBooksReadTotal, userTimesAccessed;
    
    public User(){    //the generic class for books to be added to the tableviews
    this.userID = 0;
    this.username = "";
    this.userFirstName = "";
    this.userLastName = "";
    this.userBooksReadTotal = 0;
    this.userTimesAccessed = 0;
    }

    public User (Integer userID, String username, String userFirstName, String userLastName, Integer userBooksReadTotal, Integer userTimesAccessed){ //the generic class for books to be added to the tableviews
    this.userID = userID;
    this.username = username;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.userBooksReadTotal = userBooksReadTotal;
    this.userTimesAccessed = userTimesAccessed;
    }
    
    
    public IntegerProperty userIDProperty(){    //these are used to store the data from the books into each column
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
    
    public ObservableList<User> getItems(){   //gets all the books and stores them into one observable list - needed for putting into a table column
        ObservableList<User> items = FXCollections.observableArrayList();
        for(int i=0; i<userIDL.size();i++){
            items.add(new User(userIDL.get(i), usernameL.get(i), userFirstNameL.get(i), userLastNameL.get(i), userBooksReadTotalL.get(i), userTimesAccessedL.get(i)));
        }
        return items;
    }
    
     public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rootpass1");
            Statement stmt = null;
            String query = "SELECT userID,username,userFirstName,userLastName,userBooksReadTotal,userTimesAccessed FROM capstone.users WHERE accessLvl=3"; //gets relevant book data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    userIDL.add(rs.getInt(1));
                    usernameL.add(rs.getString(2));
                    userFirstNameL.add(rs.getString(3));
                    userLastNameL.add(rs.getString(4));
                    userBooksReadTotalL.add(rs.getInt(5));
                    userTimesAccessedL.add(rs.getInt(6));
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
    }
}
