package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 *
 * @author Tyler Goodfred
 */
public class KMeans {

    private KMeans() {
        throw new IllegalAccessError("Don't Call");
    }

    private static final Random random = new Random();

    // @param bookInfo       The dataset.
    // @param k             Number of Clusters.
    //@param distance      To calculate the distance between two items.
    //@param maxIterations Upper bound for the number of iterations.
    public static Map<Centroid, List<BookData>> fit(List<BookData> bookInfo, int k, Distance distance, int maxIterations) {
        errorCheck(bookInfo, k, distance, maxIterations);

        List<Centroid> centroids = randomCentroids(bookInfo, k);
        Map<Centroid, List<BookData>> clusters = new HashMap<>();
        Map<Centroid, List<BookData>> lastState = new HashMap<>();
        
        for (int i = 0; i < maxIterations; i++) {       // iterate for a pre-defined number of times
            boolean isLastIteration = i == maxIterations - 1;

            for (BookData record : bookInfo) {              // in each iteration we should find the nearest centroid for each record
                Centroid centroid = nearestCentroid(record, centroids, distance);
                assignToCluster(clusters, record, centroid);
            }
            
            boolean shouldTerminate = isLastIteration || clusters.equals(lastState);  // if the assignment does not change, then the algorithm terminates
            lastState = clusters;
            if (shouldTerminate) {
                break;
            }
            centroids = relocateCentroids(clusters);        // at the end of each iteration we should relocate the centroids
            clusters = new HashMap<>();
        }
        return lastState;
    }

    private static List<Centroid> relocateCentroids(Map<Centroid, List<BookData>> clusters) {
        return clusters.entrySet().stream().map(e -> average(e.getKey(), e.getValue())).collect(toList());
    }

    private static Centroid average(Centroid centroid, List<BookData> records) {
        if (records == null || records.isEmpty()) {     // if this cluster is empty, then we shouldn't move the centroid
            return centroid;
        }

        Map<String, Double> average = centroid.getCoordinates();
        records.stream().flatMap(e -> e.getGenreRatings().keySet().stream()).forEach(k -> average.put(k, 0.0));

        for (BookData record : records) {
            record.getGenreRatings().forEach((k, v) -> average.compute(k, (k1, currentValue) -> v + currentValue));
        }
        average.forEach((k, v) -> average.put(k, v / records.size()));
        return new Centroid(average);
    }

    private static void assignToCluster(Map<Centroid, List<BookData>> clusters, BookData record, Centroid centroid) {
        clusters.compute(centroid, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(record);
            return list;
        });
    }

    private static Centroid nearestCentroid(BookData record, List<Centroid> centroids, Distance distance) {
        double minimumDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            double currentDistance = distance.calculate(record.getGenreRatings(), centroid.getCoordinates());

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = centroid;
            }
        }
      return nearest;
    }

    private static List<Centroid> randomCentroids(List<BookData> records, int k) {
        List<Centroid> centroids = new ArrayList<>();
        Map<String, Double> maxs = new HashMap<>();
        Map<String, Double> mins = new HashMap<>();

        for (BookData record : records) {
            record.getGenreRatings().forEach((key, value) -> {
                  // compares the value with the current max and choose the bigger value between them
                  maxs.compute(key, (k1, max) -> max == null || value > max ? value : max);

                  // compare the value with the current min and choose the smaller value between them
                  mins.compute(key, (k1, min) -> min == null || value < min ? value : min);
              });
        }

        Set<String> attributes = records.stream().flatMap(e -> e.getGenreRatings().keySet().stream()).collect(toSet());
        for (int i = 0; i < k; i++) {
            Map<String, Double> coordinates = new HashMap<>();
            for (String attribute : attributes) {
                double max = maxs.get(attribute);
                double min = mins.get(attribute);
                coordinates.put(attribute, random.nextDouble() * (max - min) + min);
            }
            centroids.add(new Centroid(coordinates));
        }
        return centroids;
    }

    private static void errorCheck(List<BookData> records, int k, Distance distance, int maxIterations) {
        if (records == null || records.isEmpty()) {
            throw new IllegalArgumentException("The dataset can't be empty");
        }
        if (k <= 1) {
            throw new IllegalArgumentException("It doesn't make sense to have less than or equal to 1 cluster");
        }
        if (distance == null) {
            throw new IllegalArgumentException("The distance calculator is required");
        }
        if (maxIterations <= 0) {   //checks if null
            throw new IllegalArgumentException("Max iterations should be a positive number");
        }
    }
}
