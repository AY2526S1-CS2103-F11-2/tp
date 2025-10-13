package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new TeleHandle("@AlexYeoh"), new Instrument("Guitar"),
                    new Comment("Average player"), new Rating("5"),
                    getTagSet("friends")),
            new Person(new Name("Jon Yeoh"), new TeleHandle("@JonYeoh"), new Instrument("Piano"),
                    new Comment("Excellent player, missing the aura"), new Rating("9"),
                    getTagSet("friends")),
            new Person(new Name("Charlotte Tan"), new TeleHandle("@CharliTan"), new Instrument("Saxophone"),
                    new Comment("Not a great player"), new Rating("3"),
                    getTagSet("friends")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
