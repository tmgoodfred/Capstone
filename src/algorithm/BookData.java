package algorithm;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Tyler Goodfred
 */

/*
the big difference between this sample class and mine is that this was made to use
an API for LASTFM, so the data it's using is like artist, genres, etc. that it pulls
from the website. My data is all stored in a database, but the thing is, the actual data it's
manipulating is kinda similar.
*/
public class BookData {

    /**
     * The record description. For example, this can be the artist name for the famous musician
     * example.
     */
    private final String bookTitle;
    /**
     * Encapsulates all attributes and their corresponding values, i.e. features.
     */
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
