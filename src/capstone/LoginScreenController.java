package capstone;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tyler Goodfred
 */
public class LoginScreenController implements Initializable {
    
    JFrame parent = new JFrame();   //for error pop up message that user was not found
    List<Integer> userIDList = new ArrayList<Integer>();
    List<String> usernameList = new ArrayList<String>();    //used to store usernames from the database
    List<String> passwordList = new ArrayList<String>();    //used to store passwords from the database
    List<Integer> accessLvlList = new ArrayList<Integer>();
    List<Integer> userAccessList = new ArrayList<Integer>();
    int noneFoundFlag = 1;  //flag to be set off when a user is not found during sign in
    int noneFoundFlag2 = 1;  //flag to be set off when the password does not match
    int moveOn = 1; //used to make sure that if both username and password are incorrect, only username error is shown

    @FXML
    TextField usernameTxt;
    
    @FXML
    TextField passwordTxt;
    
    @FXML
    Button createAccountBtn;
    
    @FXML
    Button submitBtn;
     
    @FXML
    private void submitButtonAction(ActionEvent event) throws IOException, SQLException {    //when the submit button is pressed, it will take the accepted text field information and compare it to the database to find matching users
        String[] usernameArray = new String[usernameList.size()];
        String[] passwordArray = new String[passwordList.size()];
        usernameArray = usernameList.toArray(usernameArray);    //converts list to array for easy traversal
        passwordArray = passwordList.toArray(passwordArray);    //converts list to array
        String username = usernameTxt.getText();    //extracts the data from the text field
        String password = passwordTxt.getText();    //extracts the data from the text field
        for(int i = 0; i < usernameList.size();i++) //iterates through all usernames extracted from the database
        {
            if(username.matches(usernameArray[i]))  //checks the username input against every username in the database, as soon as one matches, it moves on
            {
                moveOn=2;
                noneFoundFlag = 2;
                if(password.matches(passwordArray[i]))  //this is important, this checks the password entered against the password stored in the same tuple in the database
                {
                    Integer accessLvl = accessLvlList.get(i);
                    Integer userID = userIDList.get(i);
                    Integer userAccess = userAccessList.get(i);
                    userAccess = userAccess + 1;
                    Connection conn = null;
                    try {
                        String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                        conn = DriverManager.getConnection(url2, "root", "Rootpass1");
                        Statement stmt = null;
                        String update = "UPDATE capstone.users SET userTimesAccessed = "+userAccess+" WHERE userID = "+userID+";";
                        try {
                            stmt = conn.createStatement();
                            stmt.executeUpdate(update);
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
                    
                    if(accessLvl == 3){
                        noneFoundFlag2 = 3;
                        Stage stage3 = (Stage) submitBtn.getScene().getWindow();
                        stage3.close();
                        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        break;
                    }
                    else if (accessLvl == 1){
                        //this will show the admin screen (not yet created)
                    }
                    else if (accessLvl == 2){
                        //this will show the "librarian" screen (not yet created)
                    }
                }
                //noneFoundFlag = 2;  //flag is flipped showing that a user was found and don't show the error message
                //break;  //gets us out of the loop if a user is found
            }
        }
        if(noneFoundFlag == 1)  //if the previous loop does not find anything, after it completes, this will run and output the error message.
        {
            usernameTxt.clear();    //clears the text boxes for fresh data to be input
            passwordTxt.clear();    //users will often time think they're signing in with the proper username but will sometimes have small errors like a missing letter.
            JOptionPane.showMessageDialog(parent, "Error: No user with that name found");   
        }
        if(noneFoundFlag2 == 1 && moveOn ==2)   //if the password is incorrect only, this will show
        {
            passwordTxt.clear();    //if the username is correct, there is no need to clear that box, only password is cleared
            JOptionPane.showMessageDialog(parent, "Error: Password does not match");
        }
        
    }
    
    @FXML
    private void createAccountButtonAction(ActionEvent event) throws Exception {
        Stage stage2 = (Stage) createAccountBtn.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("CreateAccountScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    //upon initialization, the datbase is access and queries are run
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rootpass1");
            Statement stmt = null;
            String query = "SELECT userID, username, password, accessLvl, userTimesAccessed FROM capstone.users"; //gets login data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    userIDList.add(rs.getInt(1));
                    usernameList.add(rs.getString(2));  //adds usernames to username list
                    passwordList.add(rs.getString(3));  //adds passwords to password list
                    accessLvlList.add(rs.getInt(4));
                    userAccessList.add(rs.getInt(5));
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
