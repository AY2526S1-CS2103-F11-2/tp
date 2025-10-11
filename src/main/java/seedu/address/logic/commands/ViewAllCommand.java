package seedu.address.logic.commands;

import seedu.address.model.Model;

public class ViewAllCommand extends Command {
    public static final String COMMAND_WORD = "viewall";

    public static final String SHOWING_VIEWALL_MESSAGE = "All entries are now being displayed.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_VIEWALL_MESSAGE);
    }
}
