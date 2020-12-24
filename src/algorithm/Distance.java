package algorithm;

import java.util.Map;

/**
 *
 * @author Tyler Goodfred
 */
public interface Distance {

    /**
     * Calculates the distance between two feature vectors.
     *
     * @param f1 The first set of features.
     * @param f2 The second set of features.
     * @return Calculated distance.
     * @throws IllegalArgumentException If the given feature vectors are invalid.
     */
    double calculate(Map<String, Double> f1, Map<String, Double> f2);  //--ORIGINAL CODE
    //am I going to need 12 sets of features? One for each genre?
    /*double calculate(Map<String, Double> f1, 
            Map<String, Double> f2,
            Map<String, Double> f3,
            Map<String, Double> f4,
            Map<String, Double> f5,
            Map<String, Double> f6,
            Map<String, Double> f7,
            Map<String, Double> f8,
            Map<String, Double> f9,
            Map<String, Double> f10,
            Map<String, Double> f11,
            Map<String, Double> f12);*/
}