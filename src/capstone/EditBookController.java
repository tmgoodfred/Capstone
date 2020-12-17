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
public class EditBookController implements Initializable {
    
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
    TextArea descriptionTxt;
    
    @FXML
    Button bookCoverImageBtn;
    @FXML
    Button submitBtn;
    @FXML
    Button backBtn;
    
    int bookIDtoComp;
    String bookTitletoComp;
    
    String bookTitle="", bookAuthor="", bookDescription="", bookPageCount="", bookDifficultyLvl="", mainGenre="";
    List<String> bookTitleToComp = new ArrayList<>();
    FileInputStream bookCover;
    Double actionAdventureLvl = 0.0, classicLvl = 0.0, mysteryLvl = 0.0, fantasyLvl = 0.0, historicalFictionLvl = 0.0, horrorLvl= 0.0, thrillerLvl= 0.0,
            romanceLvl= 0.0, sciFiLvl= 0.0, shortStoriesLvl= 0.0, historyLvl= 0.0, youngAdultLvl= 0.0;
    int moveOn = 0;
    int moveOn2 = 0;
    int moveOn3 = 0;
    int moveOn4 = 0;
    int uploadCheck = 0;
    
    @FXML
    public void submitButtonAction(ActionEvent event) throws IOException{
    for(int i=0; i<bookTitleToComp.size();i++){
            if(titleTxt.getText().equals(bookTitleToComp.get(i))){    //checks to make sure the book doesn't already exist except for the current book
                if(!bookTitleToComp.get(i).equals(bookTitletoComp)){
                    bookTitle = titleTxt.getText();
                }
                moveOn4 = 1;
                break;
            }
        }
        if(moveOn4 == 0)
        {
            bookTitle = titleTxt.getText();
        }
        
        bookAuthor = authorTxt.getText();
        bookDescription = descriptionTxt.getText();
        mainGenre = mainGenreTxt.getText();
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
        
        if(uploadCheck == 1){   //will execute if a new book cover is uploaded
            if(moveOn == 0 && moveOn2 == 0 && moveOn3 == 0 && moveOn4 == 0){
                Connection conn = null;
                try {
                    String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                    conn = DriverManager.getConnection(url2, "root", "Rootpass1");
                    Statement stmt = null;
                    PreparedStatement insert = conn.prepareStatement("UPDATE capstone.books SET bookTitle = ?, bookAuthor = ?, bookCover = ?, bookDescription = ?, bookPageCount = ?, bookDifficultyLvl = ?, mainGenre = ?, actionAdventureLvl = ?, classicLvl = ?, mysteryLvl = ?, "    //statement for inserting into the database
                            + "fantasyLvl = ?, historicalFictionLvl = ?, horrorLvl = ?, thrillerLvl = ?, romanceLvl = ?, sciFiLvl = ?, shortStoriesLvl = ?, historyLvl = ?, youngAdultLvl = ? "
                            + "WHERE bookID = "+bookIDtoComp+";");
                        try {
                            insert.setString(1, bookTitle);
                            insert.setString(2, bookAuthor);
                            insert.setBinaryStream(3, bookCover);
                            insert.setString(4, bookDescription);
                            insert.setInt(5, Integer.parseInt(bookPageCount));
                            insert.setInt(6, Integer.parseInt(bookDifficultyLvl));
                            insert.setString(7, mainGenre);
                            insert.setDouble(8, actionAdventureLvl);
                            insert.setDouble(9, classicLvl);
                            insert.setDouble(10, mysteryLvl);
                            insert.setDouble(11, fantasyLvl);
                            insert.setDouble(12, historicalFictionLvl);
                            insert.setDouble(13, horrorLvl);
                            insert.setDouble(14, thrillerLvl);
                            insert.setDouble(15, romanceLvl);
                            insert.setDouble(16, sciFiLvl);
                            insert.setDouble(17, shortStoriesLvl);
                            insert.setDouble(18, historyLvl);
                            insert.setDouble(19, youngAdultLvl);
                            insert.executeUpdate(); //executes the insert
                            insert.close();
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
        }
        else if(uploadCheck == 0){  //will execute if no new book cover is uploaded
            if(moveOn == 0 && moveOn2 == 0 && moveOn3 == 0 && moveOn4 == 0){
                Connection conn = null;
                try {
                    String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
                    conn = DriverManager.getConnection(url2, "root", "Rootpass1");
                    Statement stmt = null;
                    PreparedStatement insert = conn.prepareStatement("UPDATE capstone.books SET bookTitle = ?, bookAuthor = ?, bookDescription = ?, bookPageCount = ?, bookDifficultyLvl = ?, mainGenre = ?, actionAdventureLvl = ?, classicLvl = ?, mysteryLvl = ?, "    //statement for inserting into the database
                            + "fantasyLvl = ?, historicalFictionLvl = ?, horrorLvl = ?, thrillerLvl = ?, romanceLvl = ?, sciFiLvl = ?, shortStoriesLvl = ?, historyLvl = ?, youngAdultLvl = ? "
                            + "WHERE bookID = "+bookIDtoComp+";");
                        try {
                            insert.setString(1, bookTitle);
                            insert.setString(2, bookAuthor);
                            insert.setString(3, bookDescription);
                            insert.setInt(4, Integer.parseInt(bookPageCount));
                            insert.setInt(5, Integer.parseInt(bookDifficultyLvl));
                            insert.setString(6, mainGenre);
                            insert.setDouble(7, actionAdventureLvl);
                            insert.setDouble(8, classicLvl);
                            insert.setDouble(9, mysteryLvl);
                            insert.setDouble(10, fantasyLvl);
                            insert.setDouble(11, historicalFictionLvl);
                            insert.setDouble(12, horrorLvl);
                            insert.setDouble(13, thrillerLvl);
                            insert.setDouble(14, romanceLvl);
                            insert.setDouble(15, sciFiLvl);
                            insert.setDouble(16, shortStoriesLvl);
                            insert.setDouble(17, historyLvl);
                            insert.setDouble(18, youngAdultLvl);
                            insert.executeUpdate(); //executes the insert
                            insert.close();
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
                alert.setContentText("This book already exists! (and isn't the one you're currently editing!)");

                alert.showAndWait();
                moveOn4 = 0;
            }
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
        uploadCheck = 1;
    }
    
    @FXML
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
        bookIDtoComp = ManagerMainMenuController.bookIDtoShare;
        bookTitletoComp = ManagerMainMenuController.bookNametoShare;
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://localhost:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "root", "Rootpass1");
            Statement stmt = null;
            String query = "SELECT bookTitle,bookAuthor,bookDescription,bookPageCount,bookDifficultyLvl,mainGenre,"
                    + "actionAdventureLvl,classicLvl,mysteryLvl,fantasyLvl,historicalFictionLvl,horrorLvl,"
                    + "thrillerLvl,romanceLvl,sciFiLvl,shortStoriesLvl,historyLvl,youngAdultLvl FROM capstone.books WHERE bookID = "+bookIDtoComp+";"; //gets relevant book data from database
            String query1 = "SELECT bookTitle FROM capstone.books";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    titleTxt.setText(rs.getString(1));
                    authorTxt.setText(rs.getString(2));
                    descriptionTxt.setText(rs.getString(3));
                    Integer page_count = rs.getInt(4);
                    pageCountTxt.setText(page_count.toString());
                    Integer difficulty = rs.getInt(5);
                    difficultyTxt.setText(difficulty.toString());
                    mainGenreTxt.setText(rs.getString(6));
                    Double aa = rs.getDouble(7);
                    String aaS = String.valueOf(aa);
                    Double c = rs.getDouble(7);
                    String cS = String.valueOf(c);
                    Double m = rs.getDouble(7);
                    String mS = String.valueOf(m);
                    Double f = rs.getDouble(7);
                    String fS = String.valueOf(f);
                    Double hf = rs.getDouble(7);
                    String hfS = String.valueOf(hf);
                    Double h = rs.getDouble(7);
                    String hS = String.valueOf(h);
                    Double t = rs.getDouble(7);
                    String tS = String.valueOf(t);
                    Double r = rs.getDouble(7);
                    String rS = String.valueOf(r);
                    Double sf = rs.getDouble(7);
                    String sfS = String.valueOf(sf);
                    Double ss = rs.getDouble(7);
                    String ssS = String.valueOf(ss);
                    Double hs = rs.getDouble(7);
                    String hsS = String.valueOf(hs);
                    Double ya = rs.getDouble(7);
                    String yaS = String.valueOf(ya);
                    aaTxt.setText(aaS);
                    cTxt.setText(cS);
                    mTxt.setText(mS);
                    fTxt.setText(fS);
                    hfTxt.setText(hfS);
                    hTxt.setText(hS);
                    tTxt.setText(tS);
                    rTxt.setText(rS);
                    sfTxt.setText(sfS);
                    ssTxt.setText(ssS);
                    hsTxt.setText(hsS);
                    yaTxt.setText(yaS);
                }
                ResultSet rs2 = stmt.executeQuery(query1);
                while (rs2.next()){
                    bookTitleToComp.add(rs2.getString(1));
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
