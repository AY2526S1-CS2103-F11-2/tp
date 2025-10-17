package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Command to display all entries in the address book.
 */
public class ViewAllCommand extends Command {
    public static final String COMMAND_WORD = "viewall";

    public static final String SHOWING_VIEWALL_MESSAGE = "All entries are now being displayed.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        System.out.println("Number of persons in model: " + model.getFilteredPersonList().size());
        return new CommandResult(SHOWING_VIEWALL_MESSAGE);
    }
}
