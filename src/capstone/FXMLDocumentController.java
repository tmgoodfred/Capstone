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

/**
 *
 * @author tmgoo
 */
public class FXMLDocumentController implements Initializable {
    
    static String[] usernameArray;
    static String[] passwordArray;
    static int lineCount = 0;
    static String usernameSQL;
    static String passwordSQL;
    public static void main(String[] args){
        Connection conn = null;
        try {
            System.out.println("help1");
            String url = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url, "root", "Rockgod101!");
            Statement stmt = null;
            String query = "SELECT username, password FROM capstone.users";
            String query2 = "SELECT COUNT(*) FROM capstone.users";
            try {
                System.out.println("help2");
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSet rs2 = stmt.executeQuery(query2);
                while (rs2.next()){
                    lineCount = rs2.getInt("COUNT(*)");
                }
                for(int i = 0; i<lineCount; i++){
                    System.out.println("line count = " + lineCount);
                    while (rs.next()){
                        usernameSQL = rs.getString("username");
                        passwordSQL = rs.getString("password");
                    }
                    usernameArray[i] = usernameSQL;
                    System.out.println("username = " + usernameSQL);
                    passwordArray[i] = passwordSQL;
                    System.out.println("password = " + passwordSQL);
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

    
    @FXML
    TextField usernameTxt;
    
    @FXML
    TextField passwordTxt;
     
    @FXML
    private void submitButtonAction(ActionEvent event) {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
    }
    
    @FXML
    private void createAccountButtonAction(ActionEvent event) {
        //open new form for creating an account
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
