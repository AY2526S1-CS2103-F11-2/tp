package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String telehandle;
    private final String instrument;
    private final String comment;
    private final String rating;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("telehandle") String telehandle,
                             @JsonProperty("instrument") String instrument, @JsonProperty("comment") String comment,
                             @JsonProperty("rating") String rating, @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.telehandle = telehandle;
        this.instrument = instrument;
        this.comment = comment;
        this.rating = rating;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        telehandle = source.getTeleHandle().telehandle;
        instrument = source.getInstrument().instrumentName;
        comment = source.getComment().commentText;
        rating = source.getRating().rating;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (telehandle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TeleHandle.class.getSimpleName()));
        }
        if (!TeleHandle.isValidTeleHandle(telehandle)) {
            throw new IllegalValueException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        final TeleHandle modelTelehandle = new TeleHandle(telehandle);

        if (instrument == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Instrument.class.getSimpleName()));
        }
        if (!Instrument.isValidInstrumentName(instrument)) {
            throw new IllegalValueException(Instrument.MESSAGE_CONSTRAINTS);
        }
        final Instrument modelInstrument = new Instrument(instrument);

        if (comment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Comment.class.getSimpleName()));
        }
        if (!Comment.isValidCommentText(comment)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }
        final Comment modelComment = new Comment(comment);

        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelTelehandle, modelInstrument, modelComment, modelRating, modelTags);
    }

}
