package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a comment associated with a person.
 * Ensures the comment is not blank and can contain any characters.
 */
public class Comment {
    public static final String MESSAGE_CONSTRAINTS =
            "Comments can contain any characters and should not be blank";

    /*
     * Accepts any characters as long as there is at least one non-whitespace character.
     */
    public static final String VALIDATION_REGEX = ".*\\S.*";

    public final String commentText;

    /**
     * Constructs an {@code Instrument}.
     * @param commentText A valid instrument name.
     */
    public Comment(String commentText) {
        requireNonNull(commentText);
        checkArgument(isValidCommentText(commentText), MESSAGE_CONSTRAINTS);
        this.commentText = commentText;
    }

    /**
     * Returns true if a given instrumentName is a valid name.
     */
    public static boolean isValidCommentText(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return commentText;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof Instrument
                && commentText.equals(((Comment) other).commentText);
    }

    @Override
    public int hashCode() {
        return commentText.hashCode();
    }
}
