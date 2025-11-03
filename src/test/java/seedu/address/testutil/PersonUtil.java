package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEHANDLE;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_TELEHANDLE + person.getTeleHandle().telehandle + " ");
        sb.append(PREFIX_INSTRUMENT + person.getInstrument().instrumentName + " ");
        sb.append(PREFIX_COMMENT + person.getComment().commentText + " ");
        sb.append(PREFIX_RATING + person.getRating().rating + " ");
        person.getTags().stream().forEach(
                s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given
     * {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getTeleHandle()
                .ifPresent(teleHandle -> sb.append(PREFIX_TELEHANDLE).append(teleHandle.telehandle).append(" "));
        descriptor.getInstrument()
                .ifPresent(instrument -> sb.append(PREFIX_INSTRUMENT).append(instrument.instrumentName).append(" "));
        descriptor.getComment().ifPresent(comment -> sb.append(PREFIX_COMMENT).append(comment.commentText).append(" "));
        descriptor.getRating().ifPresent(rating -> sb.append(PREFIX_RATING).append(rating.rating).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
