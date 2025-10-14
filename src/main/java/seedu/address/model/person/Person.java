package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final TeleHandle teleHandle;

    // Data fields
    private final Rating rating;
    private final Instrument instrument;
    private final Comment comment;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, TeleHandle teleHandle, Instrument instrument, Comment comment,
                  Rating rating, Set<Tag> tags) {
        requireAllNonNull(name, teleHandle, instrument, rating, comment, tags);
        this.name = name;
        this.teleHandle = teleHandle;
        this.instrument = instrument;
        this.comment = comment;
        this.rating = rating;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public Rating getRating() {
        return rating;
    }

    public Comment getComment() {
        return comment;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && teleHandle.equals(otherPerson.teleHandle)
                && instrument.equals(otherPerson.instrument)
                && rating.equals(otherPerson.rating)
                && comment.equals(otherPerson.comment)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, teleHandle, instrument, rating, comment, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("telehandle", teleHandle)
                .add("instrument", instrument)
                .add("rating", rating)
                .add("comment", comment)
                .add("tags", tags)
                .toString();
    }

}
