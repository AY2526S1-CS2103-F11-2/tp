package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Instrument {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String instrumentName;

    /**
     * Constructs an {@code Instrument}.
     * @param instrumentName A valid instrument name.
     */
    public Instrument(String instrumentName) {
        requireNonNull(instrumentName);
        checkArgument(isValidInstrumentName(instrumentName), MESSAGE_CONSTRAINTS);
        this.instrumentName = instrumentName;
    }

    /**
     * Returns true if a given instrumentName is a valid name.
     */
    public static boolean isValidInstrumentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return instrumentName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof Instrument
                && instrumentName.equals(((Instrument) other).instrumentName);
    }

    @Override
    public int hashCode() {
        return instrumentName.hashCode();
    }
}
