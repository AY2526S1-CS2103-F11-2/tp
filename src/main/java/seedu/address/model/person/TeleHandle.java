package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TeleHandle {
    public static final String MESSAGE_CONSTRAINTS = "Telegram handles should start with '@' and contain 5–32 letters, digits, or underscores only.";

    public static final String VALIDATION_REGEX = "^@[A-Za-z0-9_]{5,32}$";

    public final String value;

    /**
     * Constructs an {@code telehandle}.
     *
     * @param telehandle A valid telegram handle.
     */
    public TeleHandle(String telehandle) {
        requireNonNull(telehandle);
        checkArgument(isValidTeleHandle(telehandle), MESSAGE_CONSTRAINTS);
        value = telehandle;
    }

    /**
     * Returns true if a given string is a valid Telegram handle.
     */
    public static boolean isValidTeleHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if both telehandles have the same value.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof TeleHandle
                && value.equals(((TeleHandle) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
