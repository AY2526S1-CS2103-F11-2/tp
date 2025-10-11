package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class ViewAllCommand extends Command {
    public static final String COMMAND_WORD = "viewall";

    public static final String SHOWING_VIEWALL_MESSAGE = "All entries are now being displayed.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(SHOWING_VIEWALL_MESSAGE);
    }
}
