package algorithm;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Tyler Goodfred
 */
public class BookData {
    
    private final String bookTitle;
    private final Map<String, Double> genreRatings; //should be genre name and then the relative number, so Mystery, 0.6

    public BookData(String bookTitle, Map<String, Double> genreRatings) {
        this.bookTitle = bookTitle;
        this.genreRatings = genreRatings;
    }

    public BookData(Map<String, Double> genreRatings) {
        this("", genreRatings);
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Map<String, Double> getGenreRatings() {
        return genreRatings;
    }

    @Override
    public String toString() {
        String prefix = bookTitle == null || bookTitle
          .trim()
          .isEmpty() ? "Book Title" : bookTitle;

        return prefix + ": " + genreRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookData data = (BookData) o;
        return Objects.equals(getBookTitle(), data.getBookTitle()) && Objects.equals(getGenreRatings(), data.getGenreRatings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookTitle(), getGenreRatings());
    }
}
