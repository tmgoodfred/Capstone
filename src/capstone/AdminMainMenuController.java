package capstone;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Tyler Goodfred
 */
public class AdminMainMenuController implements Initializable {
    
    @FXML
    StackedBarChart genreChart;
    
    int aaGoodBookRating = 0;
    int cGoodBookRating = 0;
    int mGoodBookRating = 0;
    int fGoodBookRating = 0;
    int hfGoodBookRating = 0;
    int hGoodBookRating = 0;
    int tGoodBookRating = 0;
    int rGoodBookRating = 0;
    int sfGoodBookRating = 0;
    int ssGoodBookRating = 0;
    int hsGoodBookRating = 0;
    int yaGoodBookRating = 0;
    int aaBadBookRating = 0;
    int cBadBookRating = 0;
    int mBadBookRating = 0;
    int fBadBookRating = 0;
    int hfBadBookRating = 0;
    int hBadBookRating = 0;
    int tBadBookRating = 0;
    int rBadBookRating = 0;
    int sfBadBookRating = 0;
    int ssBadBookRating = 0;
    int hsBadBookRating = 0;
    int yaBadBookRating = 0;
    String genreToCheck;
    Double ratingToCheck;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
            Statement stmt = null;
            String query = "SELECT bookRating, mainGenre FROM capstone.books"; //gets login data from database
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    ratingToCheck = rs.getDouble(1);
                    genreToCheck = rs.getString(2);
                    if(genreToCheck.equals("Action/Adventure")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          aaBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) aaGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Classic")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          cBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) cGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Mystery")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          mBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) mGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Fantasy")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          fBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) fGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Historical Fiction")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          hfBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) hfGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Horror")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          hBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) hGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Thriller")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          tBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) tGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Romance")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          rBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) rGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Sci-Fi")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          sfBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) sfGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Short Story")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          ssBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) ssGoodBookRating++;
                    }
                    else if (genreToCheck.equals("History")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          hsBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) hsGoodBookRating++;
                    }
                    else if (genreToCheck.equals("Young Adult")){
                        if(ratingToCheck <= 1.5 && ratingToCheck > 0.1){
                          yaBadBookRating++;  
                        }
                        else if (ratingToCheck != 0.0 && ratingToCheck > 1.5) yaGoodBookRating++;
                    }
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
        
        //Defining the x axis               
        CategoryAxis yAxis = new CategoryAxis();    

        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
           ("Action/Adventure", "Classic", "Mystery", "Fantasy", "Historical Fiction", 
                   "Horror", "Thriller", "Romance", "Sci-Fi", "Short Stories", "History", "Young Adult"))); 
        yAxis.setLabel("Genres");  

        //Defining the y axis 
        NumberAxis xAxis = new NumberAxis(); 
        xAxis.setLabel("Books liked vs Books disliked");
        
        XYChart.Series<String, Number> series1 = new XYChart.Series<>(); 
        series1.setName("Liked"); 
        series1.getData().add(new XYChart.Data<>("Action/Adventure", aaGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Classic", cGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Mystery", mGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Fantasy", fGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Historical Fiction", hfGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Horror", hGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Thriller", tGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Romance", rGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Sci-Fi", sfGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Short Stories", ssGoodBookRating));
        series1.getData().add(new XYChart.Data<>("History", hsGoodBookRating));
        series1.getData().add(new XYChart.Data<>("Young Adult", yaGoodBookRating));
        
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();  
        series2.setName("Disliked"); 
        series2.getData().add(new XYChart.Data<>("Action/Adventure", aaBadBookRating));
        series2.getData().add(new XYChart.Data<>("Classic", cBadBookRating));
        series2.getData().add(new XYChart.Data<>("Mystery", mBadBookRating));
        series2.getData().add(new XYChart.Data<>("Fantasy", fBadBookRating));
        series2.getData().add(new XYChart.Data<>("Historical Fiction", hfBadBookRating));
        series2.getData().add(new XYChart.Data<>("Horror", hBadBookRating));
        series2.getData().add(new XYChart.Data<>("Thriller", tBadBookRating));
        series2.getData().add(new XYChart.Data<>("Romance", rBadBookRating));
        series2.getData().add(new XYChart.Data<>("Sci-Fi", sfBadBookRating));
        series2.getData().add(new XYChart.Data<>("Short Stories", ssBadBookRating));
        series2.getData().add(new XYChart.Data<>("History", hsBadBookRating));
        series2.getData().add(new XYChart.Data<>("Young Adult", yaBadBookRating));
 
        genreChart.getData().addAll(series1, series2); 

    }    
    
}
