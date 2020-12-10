/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author tmgoo
 */
public class FXMLDocumentController implements Initializable {
    
    List<String> usernameList = new ArrayList<String>();
    List<String> passwordList = new ArrayList<String>();
    static long lineCount;
    static String usernameSQL;
    static String passwordSQL;
    int noneFoundFlag = 1;

    @FXML
    TextField usernameTxt;
    
    @FXML
    TextField passwordTxt;
     
    @FXML
    private void submitButtonAction(ActionEvent event) {
        String[] usernameArray = new String[usernameList.size()];
        String[] passwordArray = new String[passwordList.size()];
        usernameArray = usernameList.toArray(usernameArray);
        passwordArray = passwordList.toArray(passwordArray);
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        for(int i = 0; i < usernameList.size();i++)
        {
            if(username.matches(usernameArray[i]))
            {
                System.out.println("it worked");
                noneFoundFlag = 2;
                break;
            }
        }
        if(noneFoundFlag == 1)
        {
            System.out.println("none found");
        }
        
    }
    
    @FXML
    private void createAccountButtonAction(ActionEvent event) {
        //open new form for creating an account
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rockgod101!");
            Statement stmt = null;
            String query = "SELECT username, password FROM capstone.users";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    usernameList.add(rs.getString(1));
                    passwordList.add(rs.getString(2));
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
