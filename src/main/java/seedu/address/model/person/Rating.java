package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a rating for a person.
 * Ensures the rating is a number between 0 and 10.
 */
public class Rating {
    public static final String MESSAGE_CONSTRAINTS =
            "Rating should only contain numbers on a scale of 0 to 10";
    public static final String VALIDATION_REGEX = "(?:[0-9]|10)$";
    public final String rating;

    /**
     * Constructs a {@code Phone}.
     *
     * @param rating A valid phone number.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        this.rating = rating;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return rating;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof Rating
                && rating.equals(((Rating) other).rating);
    }

    @Override
    public int hashCode() {
        return rating.hashCode();
    }

}
