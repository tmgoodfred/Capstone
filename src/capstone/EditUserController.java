package capstone;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class EditUserController implements Initializable {

    List<String> usernameList = new ArrayList<String>(); 
    
    @FXML
    Button backBtn;
    @FXML
    Button submitBtn;
    
    @FXML
    TextField usernameTxt;
    @FXML
    TextField firstNameTxt;
    @FXML
    TextField lastNameTxt;
    @FXML
    TextField passwordTxt;
    @FXML
    TextField confirmPasswordTxt;
    @FXML
    TextField ageTxt;
    
    @FXML
    ComboBox genderDropBox;
    
    int moveOn = 1;
    int userIDtoComp = ManagerMainMenuController.userIDtoShare;
    
    String username, usernameComp;
    
    @FXML
    private void submitButtonAction(ActionEvent event) throws IOException{ 
        //String username = usernameTxt.getText();
        //String[] usernameArray = new String[usernameList.size()];
        //usernameArray = usernameList.toArray(usernameArray);
        /*for(int i=0; i<usernameList.size();i++){
            if(username.matches(usernameArray[i]))  //checks the username input against every username in the database, as soon as one matches, it throws the error
            {
                moveOn = 2;
                break;
            }
        }*/
        //String username = usernameTxt.getText();
        for(int i=0; i<usernameList.size();i++){
            System.out.println("name entered = " + usernameTxt.getText() + " comparing to ="+usernameList.get(i));
            if(usernameTxt.getText().equals(usernameList.get(i))){    //checks to make sure the book doesn't already exist except for the current book
                System.out.println("MATCHIN USERNAME");
                if(usernameList.get(i).equals(usernameComp)){
                    System.out.println("IT'S THE SAME, DON'T WORRY");
                    username = usernameTxt.getText();
                    break;
                }
                moveOn = 2;
            }
        }
        
        String password = passwordTxt.getText();
        String confirmPassword = confirmPasswordTxt.getText();
        if(!password.matches(confirmPassword) && moveOn != 2){  //checks if the passwords match
            moveOn = 3;
        }
        
        String firstName = firstNameTxt.getText();
        String lastName = lastNameTxt.getText();
        
        String age = ageTxt.getText();
        int ageNum = Integer.parseInt(age);
        if((ageNum < 18 || ageNum > 99) && moveOn != 2 && moveOn != 3){ //checks to make sure the age is from 18 to 99
            moveOn = 4;
        }
        String gender = (String) genderDropBox.getValue();
        if((gender == "Gender" || gender == null) && moveOn != 2 && moveOn != 3 && moveOn!= 4){ //this checks to make sure a gender is selected
            moveOn = 5;
        }
        Connection conn = null;
        while(moveOn == 1){
            try {
                String url = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                conn = DriverManager.getConnection(url, "root", "Rootpass1");
                Statement stmt = null;
                PreparedStatement insert = conn.prepareStatement("UPDATE capstone.users SET username = ?, password = ?, userFirstName = ?, userLastName = ?, userAge = ?, userGender = ?"    //statement for inserting into the database
                            + "WHERE userID = "+userIDtoComp+";");
                try {
                    insert.setString(1, username);
                    insert.setString(2, password);
                    insert.setString(3, firstName);
                    insert.setString(4, lastName);
                    insert.setInt(5, Integer.parseInt(age));
                    insert.setString(6, gender);
                    insert.executeUpdate(); //executes the insert
                    insert.close();       
                    //Stage stage2 = (Stage) submitBtn.getScene().getWindow();  //the following takes the user back to the login screen
                    //stage2.close();
                    Parent root = FXMLLoader.load(getClass().getResource("ManagerMainMenu.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch (SQLException e ) {
                  throw new Error("Problem", e);
                } finally {
                  if (stmt != null) { stmt.close(); }
                }
            }
            catch (SQLException e) {
                throw new Error("Problem", e);
            } finally {
              try {if (conn != null) {conn.close();}} 
              catch (SQLException ex) {System.out.println(ex.getMessage());}
            }
        } //end of while statement
        if (moveOn == 2){
            usernameTxt.clear();    //clears the text boxes for fresh data to be input
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("Error: Username already taken");

            alert.showAndWait();
            moveOn = 0;
        }
        if (moveOn == 3){
            passwordTxt.clear();    //if the passwords do not match, clear the passwords but not username
            confirmPasswordTxt.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("Error: Passwords do not match");

            alert.showAndWait();
            moveOn = 0;
        }
        if (moveOn == 4){
            ageTxt.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("Error: Please enter an age between 18 and 99");

            alert.showAndWait();
            moveOn = 0;
        }
        if (moveOn == 5){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("Error: Please select a gender");

            alert.showAndWait();
            moveOn = 0;
        }
    }
    
    @FXML
    private void backButtonAction(ActionEvent event) throws Exception { //back button reinitializes and launches the main menu screen for the manager
        Stage stage2 = (Stage) backBtn.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("ManagerMainMenu.fxml"));
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
            String query = "SELECT username, password, userFirstName, userLastName, userAge, userGender FROM capstone.users WHERE userID ="+userIDtoComp+";"; //gets login data from database
            String query1 = "SELECT username FROM capstone.users;";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    usernameTxt.setText(rs.getString(1));  //adds usernames to username list
                    usernameComp = rs.getString(1);
                    passwordTxt.setText(rs.getString(2));
                    confirmPasswordTxt.setText(rs.getString(2));
                    firstNameTxt.setText(rs.getString(3));
                    lastNameTxt.setText(rs.getString(4));
                    Integer age = rs.getInt(5);
                    ageTxt.setText(age.toString());
                    genderDropBox.setValue(rs.getString(6));
                }
                ResultSet rs2 = stmt.executeQuery(query1);
                while (rs2.next()){
                    usernameList.add(rs2.getString(1));
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
        
        genderDropBox.getItems().addAll("Male", "Female", "Non-Binary", "Agender", "Other");    //fills the gender choice box with options
    }  
    
}
