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
    double calculate(Map<String, Double> f1, Map<String, Double> f2);
}