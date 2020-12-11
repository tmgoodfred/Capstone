package capstone;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Tyler Goodfred
 */
public class CreateAccountScreenController implements Initializable {
        
    List<String> usernameList = new ArrayList<String>();
    
    @FXML
    Button backBtn;
    
    @FXML
    TextField usernameTxt;
    
    @FXML
    TextField passwordTxt;
    
    @FXML
    TextField ageTxt;
    
    @FXML
    ComboBox genderDropBox;
    double actionAdventureLvl, classicLvl, mysteryLvl, fantasyLvl, historicalFictionLvl, horrorLvl, thrillerLvl, romanceLvl, sciFiLvl, shortStoriesLvl, historyLvl, youngAdultLvl;
    
    
    // <editor-fold defaultstate="collapsed" desc="radio buttons">
    @FXML
    RadioButton c1;
    @FXML
    RadioButton c2;
    @FXML
    RadioButton c3;
    @FXML
    RadioButton c4;
    @FXML
    RadioButton c5;
    @FXML
    RadioButton aa1;
    @FXML
    RadioButton aa2;
    @FXML
    RadioButton aa3;
    @FXML
    RadioButton aa4;
    @FXML
    RadioButton aa5;
    @FXML
    RadioButton m1;
    @FXML
    RadioButton m2;
    @FXML
    RadioButton m3;
    @FXML
    RadioButton m4;
    @FXML
    RadioButton m5;
    @FXML
    RadioButton f1;
    @FXML
    RadioButton f2;
    @FXML
    RadioButton f3;
    @FXML
    RadioButton f4;
    @FXML
    RadioButton f5;
    @FXML
    RadioButton hf1;
    @FXML
    RadioButton hf2;
    @FXML
    RadioButton hf3;
    @FXML
    RadioButton hf4;
    @FXML
    RadioButton hf5;
    @FXML
    RadioButton h1;
    @FXML
    RadioButton h2;
    @FXML
    RadioButton h3;
    @FXML
    RadioButton h4;
    @FXML
    RadioButton h5;
    @FXML
    RadioButton t1;
    @FXML
    RadioButton t2;
    @FXML
    RadioButton t3;
    @FXML
    RadioButton t4;
    @FXML
    RadioButton t5;
    @FXML
    RadioButton r1;
    @FXML
    RadioButton r2;
    @FXML
    RadioButton r3;
    @FXML
    RadioButton r4;
    @FXML
    RadioButton r5;
    @FXML
    RadioButton sf1;
    @FXML
    RadioButton sf2;
    @FXML
    RadioButton sf3;
    @FXML
    RadioButton sf4;
    @FXML
    RadioButton sf5;
    @FXML
    RadioButton ss1;
    @FXML
    RadioButton ss2;
    @FXML
    RadioButton ss3;
    @FXML
    RadioButton ss4;
    @FXML
    RadioButton ss5;
    @FXML
    RadioButton hs1;
    @FXML
    RadioButton hs2;
    @FXML
    RadioButton hs3;
    @FXML
    RadioButton hs4;
    @FXML
    RadioButton hs5;
    @FXML
    RadioButton ya1;
    @FXML
    RadioButton ya2;
    @FXML
    RadioButton ya3;
    @FXML
    RadioButton ya4;
    @FXML
    RadioButton ya5;
    ToggleGroup aaG = new ToggleGroup();
    ToggleGroup cG = new ToggleGroup();
    ToggleGroup mG = new ToggleGroup();
    ToggleGroup fG = new ToggleGroup();
    ToggleGroup hfG = new ToggleGroup();
    ToggleGroup hG = new ToggleGroup();
    ToggleGroup tG = new ToggleGroup();
    ToggleGroup rG = new ToggleGroup();
    ToggleGroup sfG = new ToggleGroup();
    ToggleGroup ssG = new ToggleGroup();
    ToggleGroup hsG = new ToggleGroup();
    ToggleGroup yaG = new ToggleGroup();
    // </editor-fold>
    
    
    //toggleGroup.getSelectedToggle() use this to retrieve chosen item
    @FXML
    private void submitButtonAction(ActionEvent event) {
        // <editor-fold defaultstate="collapsed" desc="if statements for radio buttons">
        if(aaG.getSelectedToggle() == aa1){
            actionAdventureLvl = 0.0;
        }
        else if(aaG.getSelectedToggle() == aa2){
            actionAdventureLvl = 0.3;
        }
        else if(aaG.getSelectedToggle() == aa3){
            actionAdventureLvl = 0.5;
        }
        else if(aaG.getSelectedToggle() == aa4){
            actionAdventureLvl = 0.7;
        }
        else if(aaG.getSelectedToggle() == aa5){
            actionAdventureLvl = 1.0;
        }
        if(cG.getSelectedToggle() == c1){
            classicLvl = 0.0;
        }
        else if(cG.getSelectedToggle() == c2){
            classicLvl = 0.3;
        }
        else if(cG.getSelectedToggle() == c3){
            classicLvl = 0.5;
        }
        else if(cG.getSelectedToggle() == c4){
            classicLvl = 0.7;
        }
        else if(cG.getSelectedToggle() == c5){
            classicLvl = 1.0;
        }
        if(mG.getSelectedToggle() == m1){
            mysteryLvl = 0.0;
        }
        else if(mG.getSelectedToggle() == m2){
            mysteryLvl = 0.3;
        }
        else if(mG.getSelectedToggle() == m3){
            mysteryLvl = 0.5;
        }
        else if(mG.getSelectedToggle() == m4){
            mysteryLvl = 0.7;
        }
        else if(mG.getSelectedToggle() == m5){
            mysteryLvl = 1.0;
        }
        if(fG.getSelectedToggle() == f1){
            fantasyLvl = 0.0;
        }
        else if(fG.getSelectedToggle() == f2){
            fantasyLvl = 0.3;
        }
        else if(fG.getSelectedToggle() == f3){
            fantasyLvl = 0.5;
        }
        else if(fG.getSelectedToggle() == f4){
            fantasyLvl = 0.7;
        }
        else if(fG.getSelectedToggle() == f5){
            fantasyLvl = 1.0;
        }
        if(hfG.getSelectedToggle() == hf1){
            historicalFictionLvl = 0.0;
        }
        else if(hfG.getSelectedToggle() == hf2){
            historicalFictionLvl = 0.3;
        }
        else if(hfG.getSelectedToggle() == hf3){
            historicalFictionLvl = 0.5;
        }
        else if(hfG.getSelectedToggle() == hf4){
            historicalFictionLvl = 0.7;
        }
        else if(hfG.getSelectedToggle() == hf5){
            historicalFictionLvl = 1.0;
        }
        if(hG.getSelectedToggle() == h1){
            horrorLvl = 0.0;
        }
        else if(hG.getSelectedToggle() == h2){
            horrorLvl = 0.3;
        }
        else if(hG.getSelectedToggle() == h3){
            horrorLvl = 0.5;
        }
        else if(hG.getSelectedToggle() == h4){
            horrorLvl = 0.7;
        }
        else if(hG.getSelectedToggle() == h5){
            horrorLvl = 1.0;
        }
        if(tG.getSelectedToggle() == t1){
            thrillerLvl = 0.0;
        }
        else if(tG.getSelectedToggle() == t2){
            horrorLvl = 0.3;
        }
        else if(tG.getSelectedToggle() == t3){
            horrorLvl = 0.5;
        }
        else if(tG.getSelectedToggle() == t4){
            horrorLvl = 0.7;
        }
        else if(tG.getSelectedToggle() == t5){
            horrorLvl = 1.0;
        }
        if(rG.getSelectedToggle() == r1){
            romanceLvl = 0.0;
        }
        else if(rG.getSelectedToggle() == r2){
            romanceLvl = 0.3;
        }
        else if(rG.getSelectedToggle() == r3){
            romanceLvl = 0.5;
        }
        else if(rG.getSelectedToggle() == r4){
            romanceLvl = 0.7;
        }
        else if(rG.getSelectedToggle() == r5){
            romanceLvl = 1.0;
        }
        if(sfG.getSelectedToggle() == sf1){
            sciFiLvl = 0.0;
        }
        else if(sfG.getSelectedToggle() == sf2){
            sciFiLvl = 0.3;
        }
        else if(sfG.getSelectedToggle() == sf3){
            sciFiLvl = 0.5;
        }
        else if(sfG.getSelectedToggle() == sf4){
            sciFiLvl = 0.7;
        }
        else if(sfG.getSelectedToggle() == sf5){
            sciFiLvl = 1.0;
        }
        if(ssG.getSelectedToggle() == ss1){
            shortStoriesLvl = 0.0;
        }
        else if(ssG.getSelectedToggle() == ss2){
            shortStoriesLvl = 0.3;
        }
        else if(ssG.getSelectedToggle() == ss3){
            shortStoriesLvl = 0.5;
        }
        else if(ssG.getSelectedToggle() == ss4){
            shortStoriesLvl = 0.7;
        }
        else if(ssG.getSelectedToggle() == ss5){
            shortStoriesLvl = 1.0;
        }
        if(hsG.getSelectedToggle() == hs1){
            historyLvl = 0.0;
        }
        else if(hsG.getSelectedToggle() == hs2){
            historyLvl = 0.3;
        }
        else if(hsG.getSelectedToggle() == hs3){
            historyLvl = 0.5;
        }
        else if(hsG.getSelectedToggle() == hs4){
            historyLvl = 0.7;
        }
        else if(hsG.getSelectedToggle() == hs5){
            historyLvl = 1.0;
        }
        if(yaG.getSelectedToggle() == ya1){
            youngAdultLvl = 0.0;
        }
        else if(yaG.getSelectedToggle() == ya2){
            youngAdultLvl = 0.3;
        }
        else if(yaG.getSelectedToggle() == ya3){
            youngAdultLvl = 0.5;
        }
        else if(yaG.getSelectedToggle() == ya4){
            youngAdultLvl = 0.7;
        }
        else if(yaG.getSelectedToggle() == ya5){
            youngAdultLvl = 1.0;
        }
        // </editor-fold>
        
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url, "root", "Rockgod101!");
            Statement stmt = null;
            String insert = "INSERT INTO capstone.users (username, password, userFirstName, userLastName, userAge, userGender, actionAdventureLvl, classicLvl, mysteryLvl, "
                    + "fantasyLvl, historicalFictionLvl, horrorLvl, thrillerLvl, romanceLvl, sciFiLvl, shortStoriesLvl, historyLvl, youngAdultLvl) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement insertPrep = conn.prepareStatement(insert);
            try {
                insertPrep.executeUpdate();
                conn.commit();
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
    }
    
    @FXML
    private void backButtonAction(ActionEvent event) throws Exception { //back button reinitializes and launches the main login screen
        Stage stage2 = (Stage) backBtn.getScene().getWindow();
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    //upon initialization, the datbase is access and queries are run
        // <editor-fold defaultstate="collapsed" desc="radio buttons">
        c1.setToggleGroup(cG);
        c2.setToggleGroup(cG);
        c3.setToggleGroup(cG);
        c4.setToggleGroup(cG);
        c5.setToggleGroup(cG);
        aa1.setToggleGroup(aaG);
        aa2.setToggleGroup(aaG);
        aa3.setToggleGroup(aaG);
        aa4.setToggleGroup(aaG);
        aa5.setToggleGroup(aaG);
        m1.setToggleGroup(mG);
        m2.setToggleGroup(mG);
        m3.setToggleGroup(mG);
        m4.setToggleGroup(mG);
        m5.setToggleGroup(mG);
        f1.setToggleGroup(fG);
        f2.setToggleGroup(fG);
        f3.setToggleGroup(fG);
        f4.setToggleGroup(fG);
        f5.setToggleGroup(fG);
        hf1.setToggleGroup(hfG);
        hf2.setToggleGroup(hfG);
        hf3.setToggleGroup(hfG);
        hf4.setToggleGroup(hfG);
        hf5.setToggleGroup(hfG);
        h1.setToggleGroup(hG);
        h2.setToggleGroup(hG);
        h3.setToggleGroup(hG);
        h4.setToggleGroup(hG);
        h5.setToggleGroup(hG);
        t1.setToggleGroup(tG);
        t2.setToggleGroup(tG);
        t3.setToggleGroup(tG);
        t4.setToggleGroup(tG);
        t5.setToggleGroup(tG);
        r1.setToggleGroup(rG);
        r2.setToggleGroup(rG);
        r3.setToggleGroup(rG);
        r4.setToggleGroup(rG);
        r5.setToggleGroup(rG);
        sf1.setToggleGroup(sfG);
        sf2.setToggleGroup(sfG);
        sf3.setToggleGroup(sfG);
        sf4.setToggleGroup(sfG);
        sf5.setToggleGroup(sfG);
        ss1.setToggleGroup(ssG);
        ss2.setToggleGroup(ssG);
        ss3.setToggleGroup(ssG);
        ss4.setToggleGroup(ssG);
        ss5.setToggleGroup(ssG);
        hs1.setToggleGroup(hsG);
        hs2.setToggleGroup(hsG);
        hs3.setToggleGroup(hsG);
        hs4.setToggleGroup(hsG);
        hs5.setToggleGroup(hsG);
        ya1.setToggleGroup(yaG);
        ya2.setToggleGroup(yaG);
        ya3.setToggleGroup(yaG);
        ya4.setToggleGroup(yaG);
        ya5.setToggleGroup(yaG);
        // </editor-fold>
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rockgod101!");
            Statement stmt = null;
            String query = "SELECT username, password FROM capstone.users"; //gets login data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    usernameList.add(rs.getString(1));  //adds usernames to username list
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
