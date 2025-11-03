package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Views detailed information of a person identified using it's displayed index
 * from the address book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the detailed information of the person identified by the index number used "
            + "in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Viewing person: %1$s";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());

        // Create a detailed view message
        StringBuilder detailedView = new StringBuilder();
        detailedView.append("=== AUDITIONEE DETAILS ===\n");
        detailedView.append("Name: ").append(personToView.getName().fullName).append("\n");
        detailedView.append("Telegram Handle: ").append(personToView.getTeleHandle().telehandle).append("\n");
        detailedView.append("Instrument: ").append(personToView.getInstrument().instrumentName).append("\n");
        detailedView.append("Rating: ").append(personToView.getRating().rating).append("/10\n");
        detailedView.append("Comments: ").append(personToView.getComment().commentText).append("\n");

        if (!personToView.getTags().isEmpty()) {
            detailedView.append("Tags: ");
            personToView.getTags().stream()
                    .sorted((tag1, tag2) -> tag1.tagName.compareTo(tag2.tagName))
                    .forEach(tag -> detailedView.append("[").append(tag.tagName).append("] "));
            detailedView.append("\n");
        }

        detailedView.append("=========================");

        return new CommandResult(detailedView.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return targetIndex.equals(otherViewCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new seedu.address.commons.util.ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
