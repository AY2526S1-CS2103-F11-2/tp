package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Comment;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

  public static final String DEFAULT_NAME = "Amy Bee";
  public static final String DEFAULT_TELEHANDLE = "@AmyBee";
  public static final String DEFAULT_INSTRUMENT = "Piano";
  public static final String DEFAULT_COMMENT = "Good player";
  public static final String DEFAULT_RATING = "7";

  private Name name;
  private TeleHandle teleHandle;
  private Instrument instrument;
  private Comment comment;
  private Rating rating;
  private Set<Tag> tags;

  /**
   * Creates a {@code PersonBuilder} with the default details.
   */
  public PersonBuilder() {
    name = new Name(DEFAULT_NAME);
    teleHandle = new TeleHandle(DEFAULT_TELEHANDLE);
    instrument = new Instrument(DEFAULT_INSTRUMENT);
    comment = new Comment(DEFAULT_COMMENT);
    rating = new Rating(DEFAULT_RATING);
    tags = new HashSet<>();
  }

  /**
   * Initializes the PersonBuilder with the data of {@code personToCopy}.
   */
  public PersonBuilder(Person personToCopy) {
    name = personToCopy.getName();
    teleHandle = personToCopy.getTeleHandle();
    instrument = personToCopy.getInstrument();
    comment = personToCopy.getComment();
    rating = personToCopy.getRating();
    tags = new HashSet<>(personToCopy.getTags());
  }

  /**
   * Sets the {@code Name} of the {@code Person} that we are building.
   */
  public PersonBuilder withName(String name) {
    this.name = new Name(name);
    return this;
  }

  /**
   * Sets the {@code TeleHandle} of the {@code Person} that we are building.
   */
  public PersonBuilder withTeleHandle(String teleHandle) {
    this.teleHandle = new TeleHandle(teleHandle);
    return this;
  }

  /**
   * Sets the {@code Instrument} of the {@code Person} that we are building.
   */
  public PersonBuilder withInstrument(String instrument) {
    this.instrument = new Instrument(instrument);
    return this;
  }

  /**
   * Sets the {@code Comment} of the {@code Person} that we are building.
   */
  public PersonBuilder withComment(String comment) {
    this.comment = new Comment(comment);
    return this;
  }

  /**
   * Sets the {@code Rating} of the {@code Person} that we are building.
   */
  public PersonBuilder withRating(String rating) {
    this.rating = new Rating(rating);
    return this;
  }

  /**
   * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
   * {@code Person} that we are building.
   */
  public PersonBuilder withTags(String... tags) {
    this.tags = SampleDataUtil.getTagSet(tags);
    return this;
  }

  public Person build() {
    return new Person(name, teleHandle, instrument, comment, rating, tags);
  }

}
