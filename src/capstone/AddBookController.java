package capstone;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class AddBookController implements Initializable {
    
    @FXML
    TextField titleTxt;
    @FXML
    TextField authorTxt;
    @FXML
    TextField pageCountTxt;
    @FXML
    TextField difficultyTxt;
    @FXML
    TextField mainGenreTxt;
    @FXML
    TextField aaTxt;
    @FXML
    TextField cTxt;
    @FXML
    TextField mTxt;
    @FXML
    TextField fTxt;
    @FXML
    TextField hfTxt;
    @FXML
    TextField hTxt;
    @FXML
    TextField tTxt;
    @FXML
    TextField rTxt;
    @FXML
    TextField sfTxt;
    @FXML
    TextField ssTxt;
    @FXML
    TextField hsTxt;
    @FXML
    TextField yaTxt;
    
    @FXML
    ComboBox genreDropBox;
    
    @FXML
    TextArea descriptionTxt;
    
    @FXML
    Button bookCoverImageBtn;
    @FXML
    Button submitBtn;
    @FXML
    Button backBtn;
    
    String bookTitle="", bookAuthor="", bookDescription="", bookPageCount="", bookDifficultyLvl="", mainGenre="";
    List<String> bookTitleToComp = new ArrayList<>();
    FileInputStream bookCover;
    Double actionAdventureLvl = 0.0, classicLvl = 0.0, mysteryLvl = 0.0, fantasyLvl = 0.0, historicalFictionLvl = 0.0, horrorLvl= 0.0, thrillerLvl= 0.0,
            romanceLvl= 0.0, sciFiLvl= 0.0, shortStoriesLvl= 0.0, historyLvl= 0.0, youngAdultLvl= 0.0;
    int moveOn = 0;
    int moveOn2 = 0;
    int moveOn3 = 0;
    int moveOn4 = 0;
    int moveOn5 = 0;
    int moveOn6 = 0;
    int moveOn7 = 0;
    
    @FXML
    public void submitButtonAction(ActionEvent event) throws IOException{
        for(int i =0; i<bookTitleToComp.size();i++){
            if(titleTxt.getText().equals(bookTitleToComp.get(i))){    //checks to make sure the book doesn't already exist
                moveOn4 = 1;
                break;
            }
        }
        if(moveOn4 == 0)
        {
            bookTitle = titleTxt.getText();     //if book title does not already exist, set it to the variable to be entered
        }
        
        String genre = (String) genreDropBox.getValue();
        if((genre == "Genre" || genre == null)){ //this checks to make sure a gender is selected
            moveOn5 = 1;
        }
        else{
            mainGenre = genreDropBox.getValue().toString();
        }
        
        if (authorTxt.getText().equals("") || authorTxt.getText().equals(null) || authorTxt.getText().isEmpty())
        {
            moveOn6 = 1;
        }
        else
        {
            bookAuthor = authorTxt.getText();
        }
        
        if (descriptionTxt.getText().equals("") || descriptionTxt.getText().equals(null) || descriptionTxt.getText().isEmpty())
        {
            moveOn7 = 1;
        }
        else
        {
            bookDescription = descriptionTxt.getText();
        }
        
        //<editor-fold defaultstate="collapsed" desc="if statements for checking input">
        if(0>Integer.parseInt(pageCountTxt.getText())){ //checks if page count is smaller than 0, if so it triggers moveOn
            moveOn = 1;
        }
        else if(pageCountTxt.getText() == null){
            moveOn = 1;
        }
        else{
            bookPageCount = pageCountTxt.getText();
        }
        
        if(0>Integer.parseInt(difficultyTxt.getText())){    //checks if difficulty is between 1 and 10, if not it triggers moveOn2
            if(Integer.parseInt(difficultyTxt.getText())>10)
            {
                moveOn2 = 1;
            }
        }
        else if(difficultyTxt.getText() == null){
            moveOn2 = 1;
        }
        else{
            bookDifficultyLvl = difficultyTxt.getText();
        }
        if(!aaTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(aaTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(aaTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(aaTxt.getText().isEmpty()){
            actionAdventureLvl = 0.0;
        }
        else{
            actionAdventureLvl = Double.parseDouble(aaTxt.getText());
        }
        if(!cTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(cTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(cTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(cTxt.getText().isEmpty()){
            classicLvl = 0.0;
        }
        else{
            classicLvl = Double.parseDouble(cTxt.getText());
        }
        if(!mTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(mTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(mTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(mTxt.getText().isEmpty()){
            mysteryLvl = 0.0;
        }
        else{
            mysteryLvl = Double.parseDouble(mTxt.getText());
        }
        if(!fTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(fTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(fTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(fTxt.getText().isEmpty()){
            fantasyLvl = 0.0;
        }
        else{
            fantasyLvl = Double.parseDouble(fTxt.getText());
        }
        if(!hfTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(hfTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(hfTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(hfTxt.getText().isEmpty()){
            historicalFictionLvl = 0.0;
        }
        else{
            historicalFictionLvl = Double.parseDouble(hfTxt.getText());
        }
        if(!hTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(hTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(hTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(hTxt.getText().isEmpty()){
            horrorLvl = 0.0;
        }
        else{
            horrorLvl = Double.parseDouble(hTxt.getText());
        }
        if(!tTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(tTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(tTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(tTxt.getText().isEmpty()){
            thrillerLvl = 0.0;
        }
        else{
            thrillerLvl = Double.parseDouble(tTxt.getText());
        }
        if(!rTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(rTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(rTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(rTxt.getText().isEmpty()){
            romanceLvl = 0.0;
        }
        else{
            romanceLvl = Double.parseDouble(rTxt.getText());
        }
        if(!sfTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(sfTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(sfTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(sfTxt.getText().isEmpty()){
            sciFiLvl = 0.0;
        }
        else{
            sciFiLvl = Double.parseDouble(sfTxt.getText());
        }
        if(!ssTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(ssTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(ssTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(ssTxt.getText().isEmpty()){
            shortStoriesLvl = 0.0;
        }
        else{
            shortStoriesLvl = Double.parseDouble(ssTxt.getText());
        }
        if(!hsTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(hsTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(hsTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(hsTxt.getText().isEmpty()){
            historyLvl = 0.0;
        }
        else{
            historyLvl = Double.parseDouble(hsTxt.getText());
        }
        if(!yaTxt.getText().isEmpty()){
            if(0.0>Double.parseDouble(yaTxt.getText())){    //checks the number entered for the different levels of genre
                if(Double.parseDouble(yaTxt.getText())>1.0)
                {
                    moveOn3 = 1;
                }
            }
        }
        else if(yaTxt.getText().isEmpty()){
            youngAdultLvl = 0.0;
        }
        else{
            youngAdultLvl = Double.parseDouble(yaTxt.getText());
        }
        //</editor-fold>
        
        String insert = "INSERT INTO capstone.books (bookTitle, bookAuthor, bookCover, bookDescription, bookPageCount, bookDifficultyLvl, mainGenre, actionAdventureLvl, classicLvl, mysteryLvl, "    //statement for inserting into the database
                        + "fantasyLvl, historicalFictionLvl, horrorLvl, thrillerLvl, romanceLvl, sciFiLvl, shortStoriesLvl, historyLvl, youngAdultLvl) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if(moveOn == 0 && moveOn2 == 0 && moveOn3 == 0 && moveOn4 == 0 && moveOn5 == 0 && moveOn6 == 0 && moveOn7 ==0){ //only moves on if the data is correctly entered
            Connection conn = null;
            try {
                String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
                Statement stmt = null;
                PreparedStatement insertPrep = conn.prepareStatement(insert);
                    try {
                        insertPrep.setString(1, bookTitle);
                        insertPrep.setString(2, bookAuthor);
                        insertPrep.setBinaryStream(3, bookCover);
                        insertPrep.setString(4, bookDescription);
                        insertPrep.setInt(5, Integer.parseInt(bookPageCount));
                        insertPrep.setInt(6, Integer.parseInt(bookDifficultyLvl));
                        insertPrep.setString(7, mainGenre);
                        insertPrep.setDouble(8, actionAdventureLvl);
                        insertPrep.setDouble(9, classicLvl);
                        insertPrep.setDouble(10, mysteryLvl);
                        insertPrep.setDouble(11, fantasyLvl);
                        insertPrep.setDouble(12, historicalFictionLvl);
                        insertPrep.setDouble(13, horrorLvl);
                        insertPrep.setDouble(14, thrillerLvl);
                        insertPrep.setDouble(15, romanceLvl);
                        insertPrep.setDouble(16, sciFiLvl);
                        insertPrep.setDouble(17, shortStoriesLvl);
                        insertPrep.setDouble(18, historyLvl);
                        insertPrep.setDouble(19, youngAdultLvl);
                        insertPrep.executeUpdate(); //executes the insert
                        Stage stage2 = (Stage) submitBtn.getScene().getWindow();  //the following takes the user back to the login screen
                        stage2.close();
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
            } 
            finally {
              try {if (conn != null) {conn.close();}} 
              catch (SQLException ex) {System.out.println(ex.getMessage());}
            }
        }
        else if(moveOn == 1)    //only displays if the page count entry is invalid
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("The page count entered is invalid");

            alert.showAndWait();
            moveOn = 0;
        }
        else if(moveOn2 == 1 && moveOn == 0){   //only displays if the difficulty level is invalid
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("The difficulty level entered is invalid");

            alert.showAndWait();
            moveOn2 = 0;
        }
        else if(moveOn3 == 1 && moveOn == 0 && moveOn2 == 0){   //only displays if the genre levels are invalid
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("One or more genre levels are invalid");

            alert.showAndWait();
            moveOn3 = 0;
        }
        else if(moveOn4 == 1 && moveOn3 == 0 && moveOn == 0 && moveOn2 == 0){   //only displays if the title does not match
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("This book already exists!");

            alert.showAndWait();
            moveOn4 = 0;
        }
        else if(moveOn5 == 1 && moveOn4 == 0 && moveOn3 == 0 && moveOn == 0 && moveOn2 == 0){   //only displays if the title does not match
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a genre");

            alert.showAndWait();
            moveOn5 = 0;
        }
        else if(moveOn6 == 1 && moveOn5 == 0 && moveOn4 == 0 && moveOn3 == 0 && moveOn == 0 && moveOn2 == 0){   //only displays if there is no author entered
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please put an author");

            alert.showAndWait();
            moveOn6 = 0;
        }
        else if(moveOn7 == 1 && moveOn6 == 0 && moveOn5 == 0 && moveOn4 == 0 && moveOn3 == 0 && moveOn == 0 && moveOn2 == 0){   //only displays if there is no description does not match
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please put a description");

            alert.showAndWait();
            moveOn7 = 0;
        }
    }
    @FXML
    public void uploadImageButtonAction(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterJPEG
                = new FileChooser.ExtensionFilter("JPEG files (*.JPEG)", "*.JPEG");
        FileChooser.ExtensionFilter extFilterjpeg
                = new FileChooser.ExtensionFilter("jpeg files (*.jpeg)", "*.jpeg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterjfif
                = new FileChooser.ExtensionFilter("jfif files (*.jfif)", "*.jfif");
        FileChooser.ExtensionFilter extFilterJFIF
                = new FileChooser.ExtensionFilter("JFIF files (*.JFIF)", "*.JFIF");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterJPEG, extFilterjpeg, extFilterPNG, extFilterpng, extFilterJFIF, extFilterjfif);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        BufferedImage bufferedImage = ImageIO.read(file);
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        
        bookCover = new FileInputStream(file);
    }
    
    public void backButtonAction(ActionEvent event) throws IOException{
        Stage stage2 = (Stage) backBtn.getScene().getWindow();  //the following takes the user back to the login screen
        stage2.close();
        Parent root = FXMLLoader.load(getClass().getResource("ManagerMainMenu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
            Statement stmt = null;
            String query = "SELECT bookTitle FROM capstone.books"; //gets relevant book data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    bookTitleToComp.add(rs.getString(1));
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
        
        genreDropBox.getItems().addAll("Action/Adventure", "Classic", "Mystery", "Fantasy", "Historical Fiction", "Horror", "Thriler", "Romance", "Sci-Fi", "Short Story", "History", "Young Adult");    //fills the genre choice box with options
    }    
    
}
