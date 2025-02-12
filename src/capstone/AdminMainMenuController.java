package capstone;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
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
    @FXML
    PieChart readPieChart;
    @FXML
    BarChart popularBooksBarChart;
    
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
    int aaTotalReads = 0;
    int cTotalReads = 0;
    int mTotalReads = 0;
    int fTotalReads = 0;
    int hfTotalReads = 0;
    int hTotalReads = 0;
    int tTotalReads = 0;
    int rTotalReads = 0;
    int sfTotalReads = 0;
    int ssTotalReads = 0;
    int hsTotalReads = 0;
    int yaTotalReads = 0;
    String genreToCheck, genreToCheck2;
    Double ratingToCheck;
    int readToAdd;
    
    List<String> bookTitleList = new ArrayList<>();
    List<Integer> bookReadsList = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = null;
        try {
            String url2 = "jdbc:mysql://72.190.54.247:3306/capstone?zeroDateTimeBehavior=CONVERT_TO_NULL";
            conn = DriverManager.getConnection(url2, "tyler", "Rootpass1!");
            Statement stmt = null;
            String query = "SELECT bookRating, mainGenre FROM capstone.books;";
            String query2 = "SELECT mainGenre, bookTotalReads FROM capstone.books;";
            String query3 = "SELECT bookTitle, bookTotalReads FROM capstone.books ORDER BY bookTotalReads DESC LIMIT 10;";
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);        //checks each book, what their genre is, and what the rating is to populate graph 1
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
                ResultSet rs2 = stmt.executeQuery(query2);      //checks the genre and how many reads each book has and totals them all up by genre
                while (rs2.next()){
                    genreToCheck2 = rs2.getString(1);
                    readToAdd = rs2.getInt(2);
                    if(genreToCheck2.equals("Action/Adventure")){
                        aaTotalReads = aaTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Classic")){
                        cTotalReads = cTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Mystery")){
                        mTotalReads = mTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Fantasy")){
                        fTotalReads = fTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Historical Fiction")){
                        hfTotalReads = hfTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Horror")){
                        hTotalReads = hTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Thriller")){
                        tTotalReads = tTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Romance")){
                        rTotalReads = rTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Sci-Fi")){
                        sfTotalReads = sfTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Short Story")){
                        ssTotalReads = ssTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("History")){
                        hsTotalReads = hsTotalReads+readToAdd;
                    }
                    else if(genreToCheck2.equals("Young Adult")){
                        yaTotalReads = yaTotalReads+readToAdd;
                    }
                }
                ResultSet rs3 = stmt.executeQuery(query3);      //simply retrieves the first 10 books when sorted by total reads, giving the 10 most read books
                while (rs3.next()){
                    bookTitleList.add(rs3.getString(1));
                    bookReadsList.add(rs3.getInt(2));
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
        
        int totalReads = aaTotalReads + cTotalReads + mTotalReads + fTotalReads + hfTotalReads + hTotalReads + tTotalReads + rTotalReads + sfTotalReads + ssTotalReads + hsTotalReads + yaTotalReads;
        
        PieChart.Data slice1 = new PieChart.Data("Action/Adventure", (Double.valueOf(aaTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice2 = new PieChart.Data("Classic", (Double.valueOf(cTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice3 = new PieChart.Data("Mystery", (Double.valueOf(mTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice4 = new PieChart.Data("Fantasy", (Double.valueOf(fTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice5 = new PieChart.Data("Historical Fiction", (Double.valueOf(hfTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice6 = new PieChart.Data("Horror", (Double.valueOf(hTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice7 = new PieChart.Data("Thriller", (Double.valueOf(tTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice8 = new PieChart.Data("Romance", (Double.valueOf(rTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice9 = new PieChart.Data("Sci-Fi", (Double.valueOf(sfTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice10 = new PieChart.Data("Short Stories", (Double.valueOf(ssTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice11 = new PieChart.Data("History", (Double.valueOf(hsTotalReads)/Double.valueOf(totalReads)));
        PieChart.Data slice12 = new PieChart.Data("Young Adult", (Double.valueOf(yaTotalReads)/Double.valueOf(totalReads)));
       
        readPieChart.getData().addAll(slice1,slice2,slice3,slice4,slice5,slice6,slice7,slice8,slice9,slice10,slice11,slice12);

        
        XYChart.Series seriesA = new XYChart.Series();
        seriesA.setName("Total Reads");
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(0), bookReadsList.get(0)));
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(1), bookReadsList.get(1)));
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(2), bookReadsList.get(2)));
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(3), bookReadsList.get(3)));
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(4), bookReadsList.get(4)));
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(5), bookReadsList.get(5)));  
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(6), bookReadsList.get(6)));  
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(7), bookReadsList.get(7)));  
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(8), bookReadsList.get(8)));  
        seriesA.getData().add(new XYChart.Data(bookTitleList.get(9), bookReadsList.get(9)));
        
        popularBooksBarChart.getData().add(seriesA);
    }    
    
}
