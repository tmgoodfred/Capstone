package algorithm;

import java.util.Map;

/**
 *
 * @author Tyler Goodfred
 */
public interface Distance {
    double calculate(Map<String, Double> f1, Map<String, Double> f2);   //does the calculation based on the euclidean distance class
}